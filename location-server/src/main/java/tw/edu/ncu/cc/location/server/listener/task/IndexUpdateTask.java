package tw.edu.ncu.cc.location.server.listener.task;

import org.apache.lucene.index.IndexWriter;
import org.glassfish.hk2.api.ServiceHandle;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.StatelessSession;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.db.HibernateUtil;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;
import tw.edu.ncu.cc.location.server.db.model.WordPersistModel;
import tw.edu.ncu.cc.location.server.db.model.impl.WordPersistModelImpl;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

public class IndexUpdateTask implements Runnable {

    private HibernateUtil hibernateUtil;
    private ServiceHandle<IndexWriter> writerHandler;

    private WordPersistModelImpl wordPersistModel = new WordPersistModelImpl();

    public IndexUpdateTask( HibernateUtil hibernateUtil, ServiceHandle<IndexWriter> writerHandler ) {
        this.hibernateUtil = hibernateUtil;
        this.writerHandler = writerHandler;
    }

    @Override
    public void run() {
        try {
            initAllModel();
            clearIndexes( wordPersistModel );
            indexPlaces ( wordPersistModel, hibernateUtil.currentStatelessSession() );
            indexPeople ( wordPersistModel, hibernateUtil.currentStatelessSession() );
            indexUnits  ( wordPersistModel, hibernateUtil.currentStatelessSession() );
        }finally {
            destroyAllModel();
        }
    }

    private void initAllModel() {
        wordPersistModel.setIndexWriter( writerHandler.getService() );
    }

    private void destroyAllModel() {
        writerHandler.destroy();
        hibernateUtil.closeSession();
    }

    public static void clearIndexes( WordPersistModel wordPersistModel ) {
        wordPersistModel.clearAllWords();
    }

    public static void indexPlaces( WordPersistModel wordPersistModel, StatelessSession session ) {
        ScrollableResults results = getScrollResult( PlaceEntity.class.getSimpleName(), session );
        while ( results.next() ) {
            PlaceEntity place = ( PlaceEntity ) results.get()[0];
            LuceneWord word = new LuceneWord();
            word.setIndex( place.getChineseName() + " " + place.getEnglishName() );
            word.setWord ( place.getChineseName() );
            word.setType ( WordType.PLACE );
            wordPersistModel.persistWords( word );
        }
    }

    public static void indexPeople( WordPersistModel wordPersistModel, StatelessSession session ) {
        ScrollableResults results = getScrollResult( PersonEntity.class.getSimpleName(), session );
        while ( results.next() ) {
            PersonEntity person = ( PersonEntity ) results.get()[0];
            LuceneWord word = new LuceneWord();
            word.setIndex( person.getChineseName() + " " + person.getEnglishName() );
            word.setWord ( person.getChineseName() );
            word.setType ( WordType.PERSON );
            wordPersistModel.persistWords( word );
        }
    }

    public static void indexUnits( WordPersistModel wordPersistModel, StatelessSession session ) {
        ScrollableResults results = getScrollResult( UnitEntity.class.getSimpleName(), session );
        while ( results.next() ) {
            UnitEntity unit = ( UnitEntity ) results.get()[0];
            LuceneWord word = new LuceneWord();
            word.setIndex( unit.getFullName() + " " + unit.getEnglishName() );
            word.setWord( unit.getFullName() );
            word.setType( WordType.UNIT );
            wordPersistModel.persistWords( word );
        }
    }

    private static ScrollableResults getScrollResult( String name, StatelessSession session ) {
        return session
                .createQuery( " from " + name )
                .setReadOnly( true )
                .setFetchSize( 100 )
                .scroll( ScrollMode.FORWARD_ONLY );
    }

}

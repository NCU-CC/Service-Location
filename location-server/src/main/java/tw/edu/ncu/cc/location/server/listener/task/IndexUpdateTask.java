package tw.edu.ncu.cc.location.server.listener.task;

import org.apache.lucene.index.IndexWriter;
import org.glassfish.hk2.api.ServiceHandle;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.db.HibernateUtil;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;
import tw.edu.ncu.cc.location.server.db.model.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.UnitModel;
import tw.edu.ncu.cc.location.server.db.model.WordPersistModel;
import tw.edu.ncu.cc.location.server.db.model.impl.PersonModelImpl;
import tw.edu.ncu.cc.location.server.db.model.impl.PlaceModelImpl;
import tw.edu.ncu.cc.location.server.db.model.impl.UnitModelImpl;
import tw.edu.ncu.cc.location.server.db.model.impl.WordPersistModelImpl;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

public class IndexUpdateTask implements Runnable {

    private HibernateUtil hibernateUtil;
    private ServiceHandle<IndexWriter> writerHandler;

    private PlaceModelImpl  placeModel  = new PlaceModelImpl();
    private PersonModelImpl personModel = new PersonModelImpl();
    private UnitModelImpl   unitModel   = new UnitModelImpl();
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
            indexPlaces ( wordPersistModel, placeModel );
            indexPeople ( wordPersistModel, personModel );
            indexUnits  ( wordPersistModel, unitModel );
        }finally {
            destroyAllModel();
        }
    }

    private void initAllModel() {
        placeModel.setSession( hibernateUtil.currentSession() );
        personModel.setSession( hibernateUtil.currentSession() );
        unitModel.setSession( hibernateUtil.currentSession() );
        wordPersistModel.setIndexWriter( writerHandler.getService() );
    }

    private void destroyAllModel() {
        writerHandler.destroy();
        hibernateUtil.closeSession();
    }

    public static void clearIndexes( WordPersistModel wordPersistModel ) {
        wordPersistModel.clearAllWords();
    }

    public static void indexPlaces( WordPersistModel wordPersistModel, PlaceModel placeModel ) {
        for( PlaceEntity place : placeModel.getAllPlaces() ) {
            LuceneWord word = new LuceneWord();
            word.setIndex( place.getChineseName() + " " + place.getEnglishName() );
            word.setWord ( place.getChineseName() );
            word.setType ( WordType.PLACE );
            wordPersistModel.persistWords( word );
        }
    }

    public static void indexPeople( WordPersistModel wordPersistModel, PersonModel personModel ) {
        for( PersonEntity person : personModel.getAllPeople() ) {
            LuceneWord word = new LuceneWord();
            word.setIndex( person.getChineseName() + " " + person.getEnglishName() );
            word.setWord ( person.getChineseName() );
            word.setType ( WordType.PERSON );
            wordPersistModel.persistWords( word );
        }
    }

    public static void indexUnits( WordPersistModel wordPersistModel, UnitModel unitModel ) {
        for( UnitEntity unit : unitModel.getAllUnits() ) {
            LuceneWord word = new LuceneWord();
            word.setIndex( unit.getFullName() + " " + unit.getEnglishName() );
            word.setWord( unit.getFullName() );
            word.setType( WordType.UNIT );
            wordPersistModel.persistWords( word );
        }
    }

}

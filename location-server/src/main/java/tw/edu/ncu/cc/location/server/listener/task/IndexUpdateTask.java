package tw.edu.ncu.cc.location.server.listener.task;

import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.db.data.PersonEntity;
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity;
import tw.edu.ncu.cc.location.server.db.data.UnitEntity;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.WordPersistModel;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

public class IndexUpdateTask implements Runnable {

    private ServiceLocator locator;

    private ServiceHandle<PlaceModel>  placeModelHandle;
    private ServiceHandle<PersonModel> personModelHandle;
    private ServiceHandle<UnitModel>   unitModelHandle;
    private ServiceHandle<WordPersistModel>   wordModelHandle;

    public IndexUpdateTask( ServiceLocator locator ) {
        this.locator = locator;
    }

    @Override
    public void run() {
        WordPersistModel wordPersistModel = initAllModel();
        clearIndexes( wordPersistModel );
        indexPlaces ( wordPersistModel, placeModelHandle.getService() );
        indexPeople ( wordPersistModel, personModelHandle.getService() );
        indexUnits  ( wordPersistModel, unitModelHandle.getService() );
        destroyAllModel();
    }

    private WordPersistModel initAllModel() {
        placeModelHandle  = locator.getServiceHandle( PlaceModel.class );
        personModelHandle = locator.getServiceHandle( PersonModel.class );
        unitModelHandle   = locator.getServiceHandle( UnitModel.class );
        wordModelHandle   = locator.getServiceHandle( WordPersistModel.class );
        return wordModelHandle.getService();
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

    private void destroyAllModel() {
        placeModelHandle.destroy();
        personModelHandle.destroy();
        unitModelHandle.destroy();
        wordModelHandle.destroy();
    }

}

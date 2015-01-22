package tw.edu.ncu.cc.location.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.data.LuceneWord;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.service.PersonService;
import tw.edu.ncu.cc.location.server.service.PlaceService;
import tw.edu.ncu.cc.location.server.service.UnitService;
import tw.edu.ncu.cc.location.server.service.WordService;
import tw.edu.ncu.cc.location.server.service.impl.EntityManagerContainer;

import java.util.List;

@Component
public class IndexUpdateTask extends EntityManagerContainer {

    private WordService wordService;
    private UnitService unitService;
    private PlaceService placeService;
    private PersonService personService;

    @Autowired
    public void setUnitService( UnitService unitService ) {
        this.unitService = unitService;
    }

    @Autowired
    public void setPlaceService( PlaceService placeService ) {
        this.placeService = placeService;
    }

    @Autowired
    public void setPersonService( PersonService personService ) {
        this.personService = personService;
    }

    @Autowired
    public void setWordService( WordService wordService ) {
        this.wordService = wordService;
    }

    @Scheduled( initialDelay = 0, fixedDelay = 1000 * 60 * 60 * 24 )
    public void run() {
        indexPlaces ();
        indexPeople ();
        indexUnits();
    }

    private void clearIndexes() {
        wordService.clearAllWords();
    }

    private void indexPlaces() {
        List< PlaceEntity > places;
        for ( int offset = 0;( places = placeService.getPlaces( offset, 100 ) ).size() > 0 ; offset += 100 ) {
            for ( PlaceEntity place : places ) {
                LuceneWord word = new LuceneWord();
                word.setIndex( place.getChineseName() + " " + place.getEnglishName() );
                word.setWord ( place.getChineseName() );
                word.setType ( WordType.PLACE );
                wordService.persistWords( word );
            }
            if( places.size() != 100 ) {
                break;
            }
        }
    }

    private void indexPeople() {
        List< PersonEntity > people;
        for ( int offset = 0;( people = personService.getPeople( offset, 100 ) ).size() > 0 ; offset += 100 ) {
            for ( PersonEntity person : people ) {
                LuceneWord word = new LuceneWord();
                word.setIndex( person.getChineseName() + " " + person.getEnglishName() );
                word.setWord ( person.getChineseName() );
                word.setType ( WordType.PERSON );
                wordService.persistWords( word );
            }
            if( people.size() != 100 ) {
                break;
            }
        }
    }

    private void indexUnits() {
        List< UnitEntity > units;
        for ( int offset = 0;( units = unitService.getUnits( offset, 100 ) ).size() > 0 ; offset += 100 ) {
            for ( UnitEntity unit : units ) {
                LuceneWord word = new LuceneWord();
                word.setIndex( unit.getFullName() + " " + unit.getEnglishName() );
                word.setWord( unit.getFullName() );
                word.setType( WordType.UNIT );
                wordService.persistWords( word );
            }
            if( units.size() != 100 ) {
                break;
            }
        }
    }

}

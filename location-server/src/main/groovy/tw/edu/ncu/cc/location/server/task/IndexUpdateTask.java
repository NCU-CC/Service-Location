package tw.edu.ncu.cc.location.server.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.entity.PersonEntity;
import tw.edu.ncu.cc.location.server.entity.PlaceEntity;
import tw.edu.ncu.cc.location.server.entity.UnitEntity;
import tw.edu.ncu.cc.location.server.repository.PersonRepository;
import tw.edu.ncu.cc.location.server.repository.PlaceRepository;
import tw.edu.ncu.cc.location.server.repository.UnitRepository;
import tw.edu.ncu.cc.location.server.service.WordService;

import java.util.List;

@Component
public class IndexUpdateTask {

    @Autowired
    private WordService wordService;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Scheduled( cron = "0 0 3 ? * *" )
    public void run() {
        clearIndexes();
        indexPlaces();
        indexPeople();
        indexUnits();
    }

    private void clearIndexes() {
        wordService.clearAllWords();
    }

    private void indexPlaces() {
        for ( PlaceEntity place : placeRepository.findAllShowablePlace() ) {
            Word word = new Word();
            word.setIndex( place.getChineseName() + appendPrefixIfNotNull( " | ", place.getEnglishName() ) );
            word.setWord ( place.getChineseName() );
            word.setType ( WordType.PLACE );
            wordService.persistWords( word );
        }
    }

    private void indexPeople() {
        for ( PersonEntity person : personRepository.findAll() ) {
            Word word = new Word();
            word.setIndex( person.getChineseName() + appendPrefixIfNotNull( " | ", person.getEnglishName() ) );
            word.setWord ( person.getChineseName() );
            word.setType ( WordType.PERSON );
            wordService.persistWords( word );
        }
    }

    private void indexUnits() {
        for ( UnitEntity unit : unitRepository.findAll() ) {
            Word word = new Word();
            word.setIndex( unit.getFullName() + appendPrefixIfNotNull( " | ", unit.getEnglishName() ) );
            word.setWord ( unit.getFullName() );
            word.setType ( WordType.UNIT );
            wordService.persistWords( word );
        }
    }

    private static String appendPrefixIfNotNull( String prefix, String s ) {
        return s == null ? "" : prefix + s;
    }

}

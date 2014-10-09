package tw.edu.ncu.cc.location.client.converter;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordWrapper;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.person.PersonWrapper;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceWrapper;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.unit.UnitWrapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ResponseConverter {

    public static Set<Place> convert( PlaceWrapper placeWrapper ) {
        if( placeWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            return new HashSet<>( Arrays.asList( placeWrapper.getResult() ) );
        }
    }

    public static Set<Unit> convert( UnitWrapper unitWrapper ) {
        if( unitWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            return new HashSet<>( Arrays.asList( unitWrapper.getResult() ) );
        }
    }

    public static Set<Person> convert( PersonWrapper personWrapper ) {
        if( personWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            return new HashSet<>( Arrays.asList( personWrapper.getResult() ) );
        }
    }

    public static Set<Word> convert( WordWrapper wordWrapper ) {
        if( wordWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            return new HashSet<>( Arrays.asList( wordWrapper.getResult() ) );
        }
    }

}

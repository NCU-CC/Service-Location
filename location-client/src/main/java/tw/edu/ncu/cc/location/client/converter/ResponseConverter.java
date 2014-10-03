package tw.edu.ncu.cc.location.client.converter;

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

}

package tw.edu.ncu.cc.location.client.converter;

import tw.edu.ncu.cc.location.client.data.Place;
import tw.edu.ncu.cc.location.client.data.Unit;
import tw.edu.ncu.cc.location.data.person.PersonUnitWrapper;
import tw.edu.ncu.cc.location.data.place.*;
import tw.edu.ncu.cc.location.data.unit.UnitBase;
import tw.edu.ncu.cc.location.data.unit.UnitBaseWrapper;
import tw.edu.ncu.cc.location.data.unit.UnitNoID;

import java.util.HashSet;
import java.util.Set;

public class ResponseConverter {

    public static Set<Place> convert( PlaceNoNameWrapper placeNoNameWrapper ) {
        if( placeNoNameWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            Set<Place> places = new HashSet<>();
            for( PlaceNoName placeNoName : placeNoNameWrapper.getResult() ) {
                places.add( new Place(
                        null,
                        PlaceType.fromValue( placeNoName.getType() ),
                        placeNoName.getLat(),
                        placeNoName.getLng(),
                        placeNoName.getPicName()
                ));
            }
            return places;
        }
    }

    public static Set<Place> convert( PlaceNoTypeWrapper placeNoTypeWrapper ) {
        if( placeNoTypeWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            Set<Place> places = new HashSet<>();
            for( PlaceNoType placeNoType : placeNoTypeWrapper.getResult() ) {
                places.add( new Place(
                        placeNoType.getName(),
                        null,
                        placeNoType.getLat(),
                        placeNoType.getLng(),
                        placeNoType.getPicName()
                ) );
            }
            return places;
        }
    }

    public static Set<Unit> convert( PersonUnitWrapper personUnitWrapper ) {
        if( personUnitWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            Set<Unit> units = new HashSet<>();
            for( UnitNoID unitNoID : personUnitWrapper.getResult() ) {
                units.add( new Unit(
                        unitNoID.getName(),
                        unitNoID.getUrl(),
                        unitNoID.getLat(),
                        unitNoID.getLng()
                ) );
            }
            return units;
        }
    }

    public static Set<Unit> convert( UnitBaseWrapper unitBaseWrapper ) {
        if( unitBaseWrapper.getResult() == null ) {
            return new HashSet<>();
        } else {
            Set<Unit> units = new HashSet<>();
            for( UnitBase unitBase : unitBaseWrapper.getResult() ) {
                units.add( new Unit(
                        null,
                        unitBase.getUrl(),
                        unitBase.getLat(),
                        unitBase.getLng()
                ) );
            }
            return units;
        }
    }

}

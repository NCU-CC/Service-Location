package tw.edu.ncu.cc.location.client.data

import spock.lang.Specification
import tw.edu.ncu.cc.location.data.place.PlaceType


class PlaceTest extends Specification {

    private Place place

    def setup() {
        place = new Place()
    }

    def "it has property of type in PlaceType"() {
        when:
            place.setType( PlaceType.EMERGENCY_TEL )
        then:
            place.getType() == PlaceType.EMERGENCY_TEL
    }

    def "it has property of pictureName in String"() {
        when:
            place.setPictureName( "123.png" )
        then:
            place.getPictureName() == "123.png"
    }

    def "it has properies same as Location"() {
        expect:
            place instanceof Location
    }

    def "it can init by other constructor"() {
        when:
            def newPlace = new Place( "name", PlaceType.SCENE, 1.0, 2.0, "pic.png" )
        then:
            newPlace.getName() == "name"
            newPlace.getType() == PlaceType.SCENE
            newPlace.getLatitude()  == 1.0
            newPlace.getLongitude() == 2.0
            newPlace.getPictureName() == "pic.png"
    }

}

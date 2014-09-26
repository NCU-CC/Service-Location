package tw.edu.ncu.cc.location.server.db.data

import spock.lang.Specification


class PlaceTest extends Specification {

    private Place place

    def setup() {
        place = new Place()
    }

    def "it has property of id in Integer"() {
        when:
            place.setId( 10 )
        then:
            place.getId() == 10
    }

    def "it has property of type in PlaceType"() {
        when:
            place.setType( PlaceType.SCENE )
        then:
            place.getType() == PlaceType.SCENE
    }

    def "it has property of name in String"() {
        when:
            place.setName( "jason" )
        then:
            place.getName() == "jason"
    }

    def "it has property of longitude in Double"() {
        when:
            place.setLongitude( 10.5 )
        then:
            place.getLongitude() == 10.5
    }

    def "it has property of latitude in Double"() {
        when:
            place.setLatitude( 20.3 )
        then:
            place.getLatitude() == 20.3
    }

    def "it has property of picture name in String"() {
        when:
            place.setPictureName( "123.jpg" )
        then:
            place.getPictureName() == "123.jpg"
    }

    def "it can init by other constructor1"() {
        when:
            Place newPlace = new Place( PlaceType.SCENE, 1, 2 )
        then:
            newPlace.getType() == PlaceType.SCENE
            newPlace.getLongitude() == 1
            newPlace.getLatitude()  == 2
    }

    def "it can init by other constructor2"() {
        when:
            Place newPlace = new Place( PlaceType.SCENE, 1, 2, "tree" )
        then:
            newPlace.getType() == PlaceType.SCENE
            newPlace.getName() == "tree"
            newPlace.getLongitude() == 1
            newPlace.getLatitude()  == 2
    }
}

package tw.edu.ncu.cc.location.db.data

import spock.lang.Specification


class LocationTest extends Specification {

    private Location location

    def setup() {
        location = new Location()
    }

    def "it has property of id in Integer"() {
        when:
            location.setId( 10 )
        then:
            location.getId() == 10
    }

    def "it has property of type in LocationType"() {
        when:
            location.setType( LocationType.SCENE )
        then:
            location.getType() == LocationType.SCENE
    }

    def "it has property of name in String"() {
        when:
            location.setName( "jason" )
        then:
            location.getName() == "jason"
    }

    def "it has property of longitude in Double"() {
        when:
            location.setLongitude( 10.5 )
        then:
            location.getLongitude() == 10.5
    }

    def "it has property of latitude in Double"() {
        when:
            location.setLatitude( 20.3 )
        then:
            location.getLatitude() == 20.3
    }

    def "it has property of picture name in String"() {
        when:
            location.setPictureName( "123.jpg" )
        then:
            location.getPictureName() == "123.jpg"
    }

    def "it can init by other constructor1"() {
        when:
            Location newLocation = new Location( LocationType.SCENE, 1, 2 )
        then:
            newLocation.getType() == LocationType.SCENE
            newLocation.getLongitude() == 1
            newLocation.getLatitude()  == 2
    }

    def "it can init by other constructor2"() {
        when:
            Location newLocation = new Location( LocationType.SCENE, 1, 2, "tree" )
        then:
            newLocation.getType() == LocationType.SCENE
            newLocation.getName() == "tree"
            newLocation.getLongitude() == 1
            newLocation.getLatitude()  == 2
    }
}

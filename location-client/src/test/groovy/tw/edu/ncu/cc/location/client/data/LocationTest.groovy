package tw.edu.ncu.cc.location.client.data

import spock.lang.Specification


class LocationTest extends Specification {

    private Location location

    def setup() {
        location = new Location()
    }

    def "it has property of name in String"() {
        when:
            location.setName( "jason" )
        then:
            location.getName() == "jason"
    }

    def "it has property of latitude in Double"() {
        when:
            location.setLatitude( 1.5 )
        then:
            location.getLatitude() == 1.5
    }

    def "it has propety of longitude in Double"() {
        when:
            location.setLongitude( 1.8 )
        then:
            location.getLongitude() == 1.8
    }
}

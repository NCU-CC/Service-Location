package tw.edu.ncu.cc.location.client.data

import spock.lang.Specification


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
}

package tw.edu.ncu.cc.location.client.data

import spock.lang.Specification
import tw.edu.ncu.cc.location.data.place.PlaceType


class PlaceTypeTest extends Specification {

    def "it has many values"() {
        expect:
            PlaceType.SCENE != null
            PlaceType.RESTAURANT != null
            PlaceType.EMERGENCY_TEL != null
            PlaceType.WHEELCHAIR_RAMP != null
            PlaceType.OTHER_EMERGENCY != null
            PlaceType.DISABLED_CAR_PARKING != null
            PlaceType.DISABLED_MOTO_PARKING != null
    }

    def "it can convert to String"() {
        given:
            PlaceType.SCENE.value() == "SCENE"
    }

    def "it can convert from String"() {
        given:
            PlaceType.fromValue( "SCENE" ) == PlaceType.SCENE
    }

}

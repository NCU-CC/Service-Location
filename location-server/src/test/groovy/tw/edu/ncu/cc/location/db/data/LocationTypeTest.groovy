package tw.edu.ncu.cc.location.db.data

import spock.lang.Specification


class LocationTypeTest extends Specification {

    def "all value"() {
        expect:
            LocationType.SCENE != null
            LocationType.RESTAURANT != null
            LocationType.EMERGENCY_TEL != null
            LocationType.WHEELCHAIR_RAMP != null
            LocationType.OTHER_EMERGENCY != null
            LocationType.DISABLED_CAR_PARKING != null
            LocationType.DISABLED_MOTO_PARKING != null
    }

    def "it can convert to String"() {
        given:
            LocationType.SCENE.value() == "SCENE"
    }

    def "it can convert from String"() {
        given:
            LocationType.fromValue( "SCENE" ) == LocationType.SCENE
    }

}

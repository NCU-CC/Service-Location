package tw.edu.ncu.cc.location.db.data

import spock.lang.Specification


class UnitTypeTest extends Specification {

    def "all value demo"() {
        expect:
            UnitType.TEACHING   != null
            UnitType.ADMINISTRATIVE != null
    }

    def "it can change value to string"() {
        expect:
            UnitType.TEACHING.value() == "TEACHING"
    }

    def "it can convert string to type"() {
        expect:
            UnitType.fromValue( "TEACHING" ) == UnitType.TEACHING
    }

    def "it will thrown exception when convert failed"() {
        when:
            UnitType.fromValue( "VALUE_NOT_EXIT" )
        then:
            thrown( IllegalArgumentException )
    }

}

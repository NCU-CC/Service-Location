package tw.edu.ncu.cc.location.db.data

import spock.lang.Specification


class UnitTest extends Specification {

    private Unit unit

    def setup() {
        unit = new Unit()
    }

    def "has property of id in Integer"() {
        when:
            unit.setId( 10 )
        then:
            unit.getId() == 10
    }

    def "has property of name in String"() {
        when:
            unit.setName( "ABC" )
        then:
            unit.getName() == "ABC"
    }

    def "has property of longitude in Double"() {
        when:
            unit.setLongitude( 122.5 )
        then:
            unit.getLongitude() == 122.5
    }

    def "has property of latitude in Double"() {
        when:
            unit.setLatitude( 23.5 )
        then:
            unit.getLatitude() == 23.5
    }

    def "has property of telephone in String"() {
        when:
            unit.setTelephone( "035684275#255" )
        then:
            unit.getTelephone() == "035684275#255"
    }

    def "has property of url in String"() {
        when:
            unit.setUrl( "http://www.gamer.com.tw" )
        then:
            unit.getUrl() == "http://www.gamer.com.tw"
    }

    def "has property of type in UnitType(enum)"() {
        when:
            unit.setType( UnitType.SCENE )
        then:
            unit.getType() == UnitType.SCENE
    }

    def "can init by other constructor"() {
        when:
            Unit newUnit = new Unit( "name", UnitType.SCENE, 1, 2 );
        then:
            newUnit.getName() == "name"
            newUnit.getType() == UnitType.SCENE
            newUnit.getLongitude() == 1
            newUnit.getLatitude()  == 2
    }

}

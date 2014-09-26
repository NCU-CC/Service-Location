package tw.edu.ncu.cc.location.client.data

import spock.lang.Specification


class UnitTest extends Specification {

    private Unit unit

    def setup() {
        unit = new Unit()
    }

    def "it has property of url in String"() {
        when:
            unit.setUrl( "http://www.gamer.com.tw" )
        then:
            unit.getUrl() == "http://www.gamer.com.tw"
    }

    def "it has properties same as Location"() {
        expect:
            unit instanceof Location
    }

}

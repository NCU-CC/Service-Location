package tw.edu.ncu.cc.location.client.jersey

import spock.lang.Specification
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig


class NCUSynLocationClientTest extends Specification {


    def "it can init only by location config"() {
        given:
            def config = Mock( LocationConfig ) {
                getServerAddress() >> "http://example.com"
            }
        when:
            new NCUSynLocationClient( config )
        then:
            notThrown( Exception )
    }

    def "it will thrown exception when the config path is not correct"() {
        given:
            def config = Mock( LocationConfig )
        when:
            new NCUSynLocationClient( config )
        then:
            thrown( NullPointerException )
    }

}

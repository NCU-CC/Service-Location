package tw.edu.ncu.cc.location.client.tool

import spock.lang.Specification
import tw.edu.ncu.cc.location.client.tool.config.NCULocationConfig


class NCULocationConfigTest extends Specification {

    private NCULocationConfig locationConfig

    def setup() {
        locationConfig = new NCULocationConfig()
    }

    def "it has property of server address in String"() {
        when:
            locationConfig.setServerAddress( "255.255.255.0" )
        then:
            locationConfig.getServerAddress() == "255.255.255.0"
    }

    def "it can configure by reading setting file in resource path"() {
        when:
            locationConfig.configure( "config1.properties" )
        then:
            locationConfig.getServerAddress() == "localhost"
    }

    def "it can configure by reading setting file in resource path 2"() {
        when:
            locationConfig.configure( "config2.properties" )
        then:
            locationConfig.getServerAddress() == null
    }

    def "it will throw exception when configuration file not exist"(){
        when:
            locationConfig.configure( "fileNotExist" )
        then:
            thrown( NullPointerException )
    }

}

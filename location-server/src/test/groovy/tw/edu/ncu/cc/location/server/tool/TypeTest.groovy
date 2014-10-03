package tw.edu.ncu.cc.location.server.tool

import spock.lang.Specification
import tw.edu.ncu.cc.location.server.tool.convert.Converter


class TypeTest extends Specification {

    def "it can convert class to other type"() {
        expect:
            Type.convert( null, Mock( Converter ) ) == null
    }

}

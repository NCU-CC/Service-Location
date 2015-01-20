package tw.edu.ncu.cc.location.server.tool

import spock.lang.Specification
import tw.edu.ncu.cc.location.server.converter.Converter
import tw.edu.ncu.cc.location.server.converter.Type


class TypeTest extends Specification {

    class testConverter implements Converter<Integer,String> {
        @Override
        String convertFrom( Integer instance ) {
            return String.valueOf( instance )
        }
    }

    def "it can convert from one class to another class by convertor"() {
        given:
            def converter = new testConverter()
        when:
            def result = Type.convert( 10, converter )
        then:
            result == "10"
    }

    def "it will return null if instance is null"() {
        expect:
            Type.convert( null, Mock( Converter ) ) == null
    }

    def "it can convert a collection into array from one type to another"() {
        given:
            def converter  = new testConverter()
            def collection = [ 1, 2, 3 ]
        when:
            def result = Type.convert( collection, String.class, converter )
        then:
            result == [ "1", "2", "3" ]
    }

    def "it will return null if collection is null or size is 0"() {
        expect:
            Type.convert( [],   String.class, new testConverter() ) == null
            Type.convert( null, String.class, new testConverter() ) == null
    }

}

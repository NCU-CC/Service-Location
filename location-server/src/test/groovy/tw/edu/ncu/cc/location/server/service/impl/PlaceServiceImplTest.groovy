package tw.edu.ncu.cc.location.server.service.impl

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification
import tw.edu.ncu.cc.location.data.place.PlaceType


class PlaceServiceImplTest extends SpringSpecification {

    @Autowired
    private PlaceServiceImpl placeService

    def "it can get places by chinese name"() {
        when:
            def places = placeService.getPlaces( "CPLACE1" )
        then:
            places.get( 0 ).englishName == "EPLACE1"
    }

    def "it can get places by place type"() {
        when:
            def places = placeService.getPlaces( PlaceType.WHEELCHAIR_RAMP )
        then:
            places.get( 0 ).englishName == "EPLACE1"
            places.get( 1 ).englishName == "EPLACE2"
    }

    def "it can get places to be indexed by pagination"() {
        given:
            def page = 0
            def per = 1
        when:
            def places = placeService.getPlacesToBeIndexed( page, per )
        then:
            places.get( 0 ).englishName == "EPLACE1"
    }

    def "it can get units in unit with specified chinese name 1"() {
        when:
            def units = placeService.getUnits( "CPLACE1" )
        then:
            units.get( 0 ).chineseName == "CUNIT1"
            units.get( 0 ).englishName == null
    }

    def "it can get units in unit with specified chinese name 2"() {
        when:
            def units = placeService.getUnits( "CPLACE2" )
        then:
            units.get( 0 ).chineseName == "CUNIT2"
            units.get( 0 ).englishName == "EUNIT2"
    }

}

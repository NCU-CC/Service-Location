package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification
import tw.edu.ncu.cc.location.data.place.PlaceType

class PlaceServiceImplTest extends SpringSpecification {

    @Autowired
    private PlaceServiceImpl placeService

    //RESTAURANT SPORT_RECREATION ADMINISTRATION RESEARCH DORMITORY OTHER
    def "it can get places of specified type by chinese name 1"() {
        when:
            def places = placeService.getPlacesByChineseName( "CPLACE3" )
        then:
            places.get( 0 ).englishName == "EPLACE3"
    }

    def "it can get places of specified type by chinese name 2"() {
        when:
            def places = placeService.getPlacesByChineseName( "CPLACE1" )
        then:
            places.size() == 0
    }

    def "it can get places by place type"() {
        when:
            def places = placeService.getPlacesByPlaceType( PlaceType.WHEELCHAIR_RAMP )
        then:
            places.get( 0 ).englishName == "EPLACE1"
            places.get( 1 ).englishName == "EPLACE2"
    }

}

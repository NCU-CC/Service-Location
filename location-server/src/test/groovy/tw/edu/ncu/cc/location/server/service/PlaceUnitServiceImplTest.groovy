package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification

class PlaceUnitServiceImplTest extends SpringSpecification {

    @Autowired
    private PlaceUnitService placeUnitService

    def "it can get all placeUnits"() {
        when:
            def placeUnits = placeUnitService.getAllPlaceUnits()
        then:
            placeUnits.size() >= 2
    }

}

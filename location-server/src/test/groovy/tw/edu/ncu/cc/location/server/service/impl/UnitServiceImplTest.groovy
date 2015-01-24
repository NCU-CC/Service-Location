package tw.edu.ncu.cc.location.server.service.impl

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification


class UnitServiceImplTest extends SpringSpecification {

    @Autowired
    private UnitServiceImpl unitService

    def "it can get units by full name"() {
        when:
            def units = unitService.getUnits( "FUNIT2" )
        then:
            units.get( 0 ).englishName == "EUNIT2"
    }

    def "it can get units to be indexed by pagination"() {
        given:
            def page = 0
            def per = 1
        when:
            def units = unitService.getUnitsToBeIndexed( page, per )
        then:
            units.get( 0 ).englishName == "EUNIT1"
    }

}

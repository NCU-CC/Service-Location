package tw.edu.ncu.cc.location.server.service

import org.springframework.beans.factory.annotation.Autowired
import specification.SpringSpecification

class UnitServiceImplTest extends SpringSpecification {

    @Autowired
    private UnitServiceImpl unitService

    def "it can get units by full name"() {
        when:
            def units = unitService.getUnitsByFullName( "FUNIT2" )
        then:
            units.get( 0 ).englishName == "EUNIT2"
    }

    def "it can get units in unit with specified chinese name 1"() {
        when:
            def units = unitService.getUnitsByPlaceChineseName( "CPLACE1" )
        then:
            units.get( 0 ).chineseName == "CUNIT1"
            units.get( 0 ).englishName == null
    }

    def "it can get units in unit with specified chinese name 2"() {
        when:
            def units = unitService.getUnitsByPlaceChineseName( "CPLACE2" )
        then:
            units.get( 0 ).chineseName == "CUNIT2"
            units.get( 0 ).englishName == "EUNIT2"
    }

}

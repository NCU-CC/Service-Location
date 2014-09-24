package tw.edu.ncu.cc.location.db.model

import org.hibernate.PropertyValueException
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Unit
import tw.edu.ncu.cc.location.db.data.UnitType
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


class UnitModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private UnitModelImpl unitModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilFactory().provide()
    }

    def setup() {
        unitModel = new UnitModelImpl()
        unitModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }

    def "persisted data must have some values exist"() {
        given:
            Unit unit = new Unit()
            unit.setName( "unit" )
            unit.setType( UnitType.TEACHING )
            unit.setLatitude ( 50 )
            unit.setLongitude( 50 )
        when:
            unitModel.persistUnits( unit )
        then:
            notThrown( PropertyValueException )
    }

    def "thrown exception when some fields is null"() {
        given:
            Unit unit = new Unit()
        when:
            unitModel.persistUnits( unit )
        then:
            thrown( PropertyValueException )
    }

    def "it can get unit by id"() {
        given:
            Unit unit = new Unit( "none", UnitType.TEACHING, 1, 1 )
        when:
            unitModel.persistUnits( unit )
        then:
            unitModel.getUnit( 2 ) == unit
    }

    def "it can get unit by name"() {
        given:
            Unit unit = new Unit( "SCHOOL", UnitType.TEACHING, 1, 1 )
        when:
            unitModel.persistUnits( unit )
        then:
            unitModel.getUnit( "SCHOOL" ) == unit
    }

    def "it can get units by type"() {
        given:
            Unit unit1 = new Unit( "none", UnitType.TEACHING, 1, 1 )
            Unit unit2 = new Unit( "none", UnitType.TEACHING, 1, 1 )
            Unit unit3 = new Unit( "none", UnitType.ADMINISTRATIVE, 1, 1 )
        when:
            unitModel.persistUnits( unit1, unit2, unit3 )
        and:
            def units = unitModel.getUnits( UnitType.TEACHING )
        then:
            units.contains( unit1 )
            units.contains( unit2 )
            ! units.contains( unit3 )
    }


}

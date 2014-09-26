package tw.edu.ncu.cc.location.db.model

import org.hibernate.PropertyValueException
import spock.lang.Shared
import spock.lang.Specification
import tool.HibernateUtilTestFactory
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Unit

class UnitModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private UnitModelImpl unitModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilTestFactory().provide()
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
            unit.setLatitude ( 50 )
            unit.setLongitude( 50 )
        when:
            unitModel.persistUnits( unit )
        then:
            notThrown( PropertyValueException )
    }

    def "it will thrown exception for some fields is null when persisting"() {
        given:
            Unit unit = new Unit()
        when:
            unitModel.persistUnits( unit )
        then:
            thrown( PropertyValueException )
    }

    def "it can get unit by id"() {
        given:
            Unit unit = new Unit( "none", 1, 1 )
        when:
            unitModel.persistUnits( unit )
        then:
            unitModel.getUnit( 2 ) == unit
    }

    def "it can get unit by name"() {
        given:
            Unit unit = new Unit( "SCHOOL", 1, 1 )
        when:
            unitModel.persistUnits( unit )
        then:
            unitModel.getUnits( "SCHOOL" ).contains( unit )
    }

}

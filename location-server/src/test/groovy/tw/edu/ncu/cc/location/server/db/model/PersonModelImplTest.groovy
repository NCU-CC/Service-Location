package tw.edu.ncu.cc.location.server.db.model

import spock.lang.Shared
import spock.lang.Specification
import tool.HibernateUtilTestFactory
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.PersonEntity
import tw.edu.ncu.cc.location.server.db.data.UnitEntity


class PersonModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private PersonModelImpl personModel
    private UnitModelImpl   unitModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilTestFactory().provide()
    }

    def setup() {
        personModel = new PersonModelImpl()
        personModel.setSession( hibernateUtil.currentSession() )
        unitModel   = new UnitModelImpl()
        unitModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }

    def "it can persist and get person by id"(){
        given:
            def unit1 = new UnitEntity( "ucode1", "unit1", "sname1", "fname1" )
            def unit2 = new UnitEntity( "ucode2", "unit2", "sname2", "fname2" )
            unitModel.persistUnits( unit1, unit2 )
        and:
            PersonEntity person = new PersonEntity( "code1", "john", "title1", unit1, unit2 )
        when:
            personModel.persistPersons( person )
        then:
            personModel.getPerson( 1 ) == person
    }

    def "it can get person by person name"() {
        given:
            def unit1 = new UnitEntity( "ucode3", "unit3", "sname3", "fname3" )
            def unit2 = new UnitEntity( "ucode4", "unit4", "sname4", "fname4" )
            unitModel.persistUnits( unit1, unit2 )
        and:
            PersonEntity person = new PersonEntity( "code2", "jason", "title2", unit1, unit2 )
        when:
            personModel.persistPersons( person )
        then:
            personModel.getPerson( "jason" ) == person
    }

}

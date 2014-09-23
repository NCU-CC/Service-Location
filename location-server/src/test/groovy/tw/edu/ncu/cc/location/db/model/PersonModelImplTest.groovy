package tw.edu.ncu.cc.location.db.model

import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Person
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory


class PersonModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private PersonModelImpl personModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilFactory().provide()
    }

    def setup() {
        personModel = new PersonModelImpl()
        personModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }


    def "it can persist and get person by id"(){
        given:
            Person person = new Person( "none" )
        when:
            personModel.persistPersons( person )
        then:
            personModel.getPerson( 1 ) == person
    }

    def "it can get person by person name"() {
        given:
            Person person = new Person( "jason" )
        when:
            personModel.persistPersons( person )
        then:
            personModel.getPerson( "jason" ) == person
    }

}

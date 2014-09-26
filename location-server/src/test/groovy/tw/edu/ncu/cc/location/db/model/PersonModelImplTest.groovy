package tw.edu.ncu.cc.location.db.model

import org.hibernate.PropertyValueException
import spock.lang.Shared
import spock.lang.Specification
import tool.HibernateUtilTestFactory
import tw.edu.ncu.cc.location.db.HibernateUtil
import tw.edu.ncu.cc.location.db.data.Person

class PersonModelImplTest extends Specification {

    private @Shared HibernateUtil hibernateUtil
    private PersonModelImpl personModel

    def setupSpec() {
        hibernateUtil = new HibernateUtilTestFactory().provide()
    }

    def setup() {
        personModel = new PersonModelImpl()
        personModel.setSession( hibernateUtil.currentSession() )
    }

    def cleanup() {
        hibernateUtil.closeSession()
    }

    def "persisted data must have some values exist"() {
        given:
            Person person = new Person()
            person.setName( "name" )
        when:
            personModel.persistPersons( person )
        then:
            notThrown( PropertyValueException )
    }

    def "thrown exception when some fields is null"() {
        given:
            Person person = new Person()
        when:
            personModel.persistPersons( person )
        then:
            thrown( PropertyValueException )
    }

    def "it can persist and get person by id"(){
        given:
            Person person = new Person( "none" )
        when:
            personModel.persistPersons( person )
        then:
            personModel.getPerson( 2 ) == person
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

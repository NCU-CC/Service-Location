package tw.edu.ncu.cc.location.server.db.model

import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.server.db.data.PersonEntity
import tw.edu.ncu.cc.location.server.db.data.UnitEntity
import tw.edu.ncu.cc.location.server.db.model.impl.PersonModelImpl
import tw.edu.ncu.cc.location.server.db.model.impl.UnitModelImpl
import tw.edu.ncu.cc.location.server.resource.SessionResource
import tw.edu.ncu.cc.location.server.resource.UnitSessionResource

class PersonModelImplTest extends Specification {

    @Shared  @ClassRule
    SessionResource sessionResource = new UnitSessionResource()

    PersonModelImpl personModel
    UnitModelImpl   unitModel

    def setup() {
        personModel = new PersonModelImpl()
        personModel.setSession( sessionResource.getSession() )
        unitModel   = new UnitModelImpl()
        unitModel.setSession( sessionResource.getSession() )
    }

    def "it can persist and get person by id"(){
        given:
            def units = twoUnitsInDataBase()
        and:
            def person = new PersonEntity( "code1", "name1", "title1", units[0], units[1] )
        when:
            personModel.persistPeople( person )
        then:
            personModel.getPerson( 1 ) == person
    }

    def "it can get person by person name"() {
        given:
            def units  = twoUnitsInDataBase()
        and:
            def person = new PersonEntity( "code2", "name2", "title2", units[0], units[1] )
        when:
            personModel.persistPeople( person )
        then:
            personModel.getPerson( "name2" ) == person
    }

    private UnitEntity[] twoUnitsInDataBase() {
        def id = new Random().nextInt( 1000 )
        def unit1 = new UnitEntity( "${id}", "${id}", "${id}", "${id}" )
        def unit2 = new UnitEntity( "${id+1}", "${id+1}", "${id+1}", "${id+1}" )
        unitModel.persistUnits( unit1, unit2 )
        return [ unit1, unit2 ];
    }

}

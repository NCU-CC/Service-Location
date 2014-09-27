package tw.edu.ncu.cc.location.server.db.data

import spock.lang.Specification


class PersonTest extends Specification {

    private Person person

    def setup() {
        person = new Person()
    }

    def "has property of id in Integer"() {
        when:
            person.setId( 10 )
        then:
            person.getId() == 10
    }

    def "has property of units in Set<Unit>"() {
        given:
            Set<Unit> units = new HashSet<>()
        when:
            person.setUnits( units )
        then:
            person.getUnits() == units
    }

    def "has property of name in String"() {
        when:
            person.setName( "Hello" )
        then:
            person.getName() == "Hello"
    }

    def "can init by other constructor"() {
        when:
            Person newPerson = new Person( "name" )
        then:
            newPerson.getName() == "name"
    }

    def "can init by other contructor 2"() {
        given:
            Unit unit = new Unit()
        when:
            Person newPerson = new Person( "name", unit )
        then:
            newPerson.getName() == "name"
            newPerson.getUnits().contains( unit )
    }


}

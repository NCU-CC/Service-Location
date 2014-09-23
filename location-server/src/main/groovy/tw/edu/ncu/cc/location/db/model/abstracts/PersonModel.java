package tw.edu.ncu.cc.location.db.model.abstracts;

import tw.edu.ncu.cc.location.db.data.Person;

public interface PersonModel {
    public void   persistPersons( Person...persons );
    public Person getPerson( Integer id );
    public Person getPerson( String name );
}

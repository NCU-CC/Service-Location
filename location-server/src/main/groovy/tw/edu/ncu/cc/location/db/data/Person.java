package tw.edu.ncu.cc.location.db.data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id @GeneratedValue
    private Integer id;

    @ManyToMany( fetch = FetchType.LAZY )
    private Set<Unit> units;

    @Column( length = 80, nullable = false )
    private String name;

    public Person() {}
    public Person( String name ) {
        this.name = name;
    }

    public Person( String name, Unit...unitArr ) {
        this.name = name;
        if( unitArr != null ) {
            units = new HashSet<>();
            units.addAll( Arrays.asList( unitArr ) );
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits( Set<Unit> units ) {
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}

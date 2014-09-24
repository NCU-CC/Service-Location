package tw.edu.ncu.cc.location.response.unit;

import tw.edu.ncu.cc.location.db.data.Unit;

@SuppressWarnings( "unused" )
public class UnitNoID extends UnitBase {

    private String name;

    public UnitNoID( Unit unit ) {
        super( unit );
        name = unit.getName();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}

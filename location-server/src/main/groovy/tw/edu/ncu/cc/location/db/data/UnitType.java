package tw.edu.ncu.cc.location.db.data;

public enum  UnitType {

    ADMINISTRATIVE, TEACHING;

    public String value() {
        return name();
    }

    public static UnitType fromValue( String string ) {
        return valueOf( string );
    }

}

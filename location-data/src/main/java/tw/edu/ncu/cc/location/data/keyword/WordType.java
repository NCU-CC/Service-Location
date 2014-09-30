package tw.edu.ncu.cc.location.data.keyword;

@SuppressWarnings( "unused" )
public enum WordType {

    PERSON, PLACE, UNIT;

    public String value() {
        return name();
    }

    public static WordType fromValue( String string ) {
        return valueOf( string );
    }

}

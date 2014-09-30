package tw.edu.ncu.cc.location.data.keyword;

@SuppressWarnings( "unused" )
public enum KeywordType {
    PERSON, PLACE, UNIT;

    public String value() {
        return name();
    }

    public static KeywordType fromValue( String string ) {
        return valueOf( string );
    }

}

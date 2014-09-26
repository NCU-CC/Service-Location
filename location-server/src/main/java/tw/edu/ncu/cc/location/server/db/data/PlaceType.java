package tw.edu.ncu.cc.location.server.db.data;

public enum PlaceType {

    WHEELCHAIR_RAMP, DISABLED_CAR_PARKING, DISABLED_MOTO_PARKING, EMERGENCY_TEL,
    RESTAURANT, SCENE, OTHER_EMERGENCY;

    public String value() {
        return name();
    }

    public static PlaceType fromValue( String string ) {
        return valueOf( string );
    }

}

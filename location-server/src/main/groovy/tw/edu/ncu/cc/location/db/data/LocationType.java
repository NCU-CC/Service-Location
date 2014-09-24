package tw.edu.ncu.cc.location.db.data;

public enum LocationType {

    WHEELCHAIR_RAMP, DISABLED_CAR_PARKING, DISABLED_MOTO_PARKING, EMERGENCY_TEL,
    RESTAURANT, SCENE, OTHER_EMERGENCY;

    public String value() {
        return name();
    }

    public static LocationType fromValue( String string ) {
        return valueOf( string );
    }

}

package tw.edu.ncu.cc.location.client.tool;

import tw.edu.ncu.cc.location.client.tool.response.ResponseListener;
import tw.edu.ncu.cc.location.data.building.Building;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;

public interface AsynLocationClient {

    void getPlaces( String cname, ResponseListener< Place > responseListener );
    void getPlaces( PlaceType placeType , ResponseListener< Place > responseListener );

    void getBuildings( ResponseListener< Building > responseListener );
    void getBuildingUnits( String buildingChineseName, ResponseListener< Unit > responseListener );

    void getPeople( String cname, ResponseListener< Person > responseListener );
    void getUnits ( String fname, ResponseListener< Unit > responseListener );

    void getWords ( String keyword, ResponseListener< Word > responseListener );

}

## Location-Client-Android
location data consumer on android volley

### Build a **LocationConfig**
- build by yourself
```Java
LocationConfig config = new NCULocationConfig();
config.setServerAddress( "http://127.0.0.1:8080/location-Service/api/v2" )
```
- build by properties file in resource path
```Java
LocationConfig config = new NCULocationConfig().configure( "settings.properties" );
```
```Java
#properties in config file
location.server_address = http://127.0.0.1:8080/location-Service/api/v2
```

### Build a **LocationClient** using **LocationConfig**
```Java
LocationClient client = new LocationClient( config, androidContext )
```

### Build a **ResponseListener**
```Java
class VolleyResponseListener implements ResponseListener<T> {
        @Override
        void onResponse( List<T> responses ) {
            //do something on reponses
        }
        @Override
        void onError( Throwable throwable ) {
            //you can cast it to VolleyError
        }
}
```

### Call methods on **LocationClient** to fetch data from server
- ``` client.getPlaces( "chineseName", new VolleyResponseListener<Place>() ) ```
- ``` client.getPlaces( PlaceType.SCENE, new VolleyResponseListener<Place>() ) ```
- ``` client.getPlaceUnits( "chineseName", new VolleyResponseListener<Unit>() ) ```
- ``` client.getUnits( "fullName", new VolleyResponseListener<Unit>() ) ```
- ``` client.getPeople( "chineseName", new VolleyResponseListener<Person>() ) ```
- ``` client.getWords( "keyword", new VolleyResponseListener<Word>() ) ```

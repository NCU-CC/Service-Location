## Location-Client-Volley
location data consumer on android volley

### Build a *LocationConfig*
- build by yourself
```
LocationConfig config = new NCULocationConfig();
config.setServerAddress( "http://127.0.0.1:8080/location-Service" )
```
- build by properties file in resource path
```
LocationConfig config = new NCULocationConfig().configure( "settings.properties" );
```
```
#properties in config file
location.server_address = http://127.0.0.1:8080/location-Service
```

### Build a *LocationClient* using *LocationConfig*
```
AsynLocationClient client = new NCULocationClient( config, androidContext )
```

### Build a *ResponseListener*
```
class VolleyResponseListener implements ResponseListener<T> {
        @Override
        void onResponse( Set<T> responses ) {
            //do something on reponses
        }
        @Override
        void onError( Throwable throwable ) {
            //you can cast it to VolleyError
        }
}
```

### Call methods on *AsynLocationClient* to fetch data from server
- ``` client.getPlaces( "chineseName", new VolleyResponseListener<Place>() ) ```
- ``` client.getPlaces( PlaceType.SCENE, new VolleyResponseListener<Place>() ) ```
- ``` client.getUnits( "fullName", new VolleyResponseListener<Unit>() ) ```
- ``` client.getPeople( "chineseName", new VolleyResponseListener<Person>() ) ```
- ``` client.getWords( "keyword", new VolleyResponseListener<Word>() ) ```

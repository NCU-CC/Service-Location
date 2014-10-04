## Location-Client
location data consumer for java

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
LocationClient client = new NCULocationClient( config )
```

### Call methods on *LocationClient* to fetch data from server
- ``` Set<Place>  places =  client.getPlaces( "chineseName" ) ```
- ``` Set<Place>  places =  client.getPlaces( PlaceType.SCENE ) ```
- ``` Set<Unit>   units  =  client.getUnits( "fullName" ) ```
- ``` Set<Person> people =  client.getPeople( "chineseName" ) ```

package tw.edu.ncu.cc.location.client.android;


import android.content.Context;
import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import tw.edu.ncu.cc.location.client.tool.AsynLocationClient;
import tw.edu.ncu.cc.location.client.tool.config.LocationConfig;
import tw.edu.ncu.cc.location.client.tool.response.ResponseListener;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;

import java.util.Arrays;

@SuppressWarnings( "unused" )
public class LocationClient implements AsynLocationClient {

    private RequestQueue queue;
    private String baseURL;

    public LocationClient( LocationConfig config, Context context ) {
        this.baseURL = config.getServerAddress();
        this.queue = Volley.newRequestQueue( context );
    }

    public RequestQueue getQueue() {
        return queue;
    }

    @Override
    public void getPlaces( String placeName, ResponseListener< Place > responseListener ) {
        sendRequest(
                "/v3/places?name=" + Uri.encode( placeName ), responseListener
        );
    }

    @Override
    public void getPlaces( PlaceType placeType, ResponseListener< Place > responseListener ) {
        sendRequest(
                "/v3/places?type=" + Uri.encode( placeType.value() ), responseListener
        );
    }

    @Override
    public void getPlaceUnits( String placeName, ResponseListener< Unit > responseListener ) {
        sendRequest(
                "/v3/units?placeName=" + Uri.encode( placeName ) , responseListener
        );
    }

    @Override
    public void getPeople( String peopleName, ResponseListener< Person > responseListener ) {
        sendRequest(
                "/v3/people?name=" + Uri.encode( peopleName ), responseListener
        );
    }

    @Override
    public void getUnits( String unitName, ResponseListener< Unit > responseListener ) {
        sendRequest(
                "/v3/units?name=" + Uri.encode( unitName ), responseListener
        );
    }

    @Override
    public void getWords( String keyword, ResponseListener< Word > responseListener ) {
        sendRequest(
                "/v3/search?q=" + Uri.encode( keyword ), responseListener
        );
    }

    private < T > void sendRequest( String path, final ResponseListener< T > responseListener ) {
        queue.add( new StringRequest( Request.Method.GET, baseURL + path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse( String response ) {
                        T[] result = new Gson().fromJson( response, new TypeToken< T[] >(){}.getType() );
                        responseListener.onResponse( Arrays.asList( result ) );
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse( VolleyError error ) {
                        responseListener.onError( error );
                    }
                }
        ) );
    }
	
}

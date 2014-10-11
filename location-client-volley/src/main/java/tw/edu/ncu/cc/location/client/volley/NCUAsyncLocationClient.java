package tw.edu.ncu.cc.location.client.volley;


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
import tw.edu.ncu.cc.location.client.tool.converter.ResponseConverter;
import tw.edu.ncu.cc.location.client.tool.response.ResponseListener;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.person.Person;
import tw.edu.ncu.cc.location.data.place.Place;
import tw.edu.ncu.cc.location.data.place.PlaceType;
import tw.edu.ncu.cc.location.data.unit.Unit;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;

import java.lang.reflect.Type;

@SuppressWarnings( "unused" )
public class NCUAsyncLocationClient implements AsynLocationClient {

    private RequestQueue queue;
    private String baseURL;

    public NCUAsyncLocationClient( LocationConfig config, Context context ) {
        this.baseURL = config.getServerAddress();
        this.queue = Volley.newRequestQueue( context );
    }

    @Override
    public void getPlaces( String placeName, ResponseListener<Place> responseListener ) {
        sendRequest( "/place/name/" + placeName , responseListener );
    }

    @Override
    public void getPlaces( PlaceType placeType, ResponseListener<Place> responseListener ) {
        sendRequest( "/place/type/" + placeType , responseListener );
    }

    @Override
    public void getPeople( String peopleName, ResponseListener<Person> responseListener ) {
        sendRequest( "/person/name/" + peopleName , responseListener );
    }

    @Override
    public void getUnits( String unitName, ResponseListener<Unit> responseListener ) {
        sendRequest( "/unit/name/" + unitName , responseListener );
    }

    @Override
    public void getWords( String keyword, ResponseListener<Word> responseListener ) {
        sendRequest( "/keyword/" + keyword , responseListener );
    }

    private <T> void sendRequest( String path, final ResponseListener<T> responseListener ) {
        queue.add( new StringRequest( Request.Method.GET, Uri.encode( baseURL + path ),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse( String response ) {
                        Type typeOfResult = new TypeToken< ResultWrapper<T> >(){}.getType();
                        ResultWrapper<T> wrapper = new Gson().fromJson( response, typeOfResult );
                        responseListener.onResponse( ResponseConverter.convert( wrapper ) );
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

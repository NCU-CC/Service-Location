package tw.edu.ncu.cc.location.client.android

import com.android.volley.VolleyError
import spock.lang.Specification
import tw.edu.ncu.cc.location.client.tool.response.ResponseListener


class VolleyErrorTest extends Specification {

    class VolleyResponseListener implements ResponseListener< String > {
        @Override
        void onResponse( List< String > responses ) {

        }
        @Override
        void onError( Throwable throwable ) {
            VolleyError error = (VolleyError) throwable
            assert  error != null
        }
    }

    def "it can get volley error from throwable"() {
        given:
            def listener = new VolleyResponseListener()
        when:
            listener.onError( new VolleyError() )
        then:
            notThrown( Exception )
    }
}

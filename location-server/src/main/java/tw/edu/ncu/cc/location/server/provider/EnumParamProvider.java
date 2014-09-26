package tw.edu.ncu.cc.location.server.provider;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class EnumParamProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter( final Class<T> rawType, Type genericType, Annotation[] annotations ) {

        try {

            final Method fromValueMethod = rawType.getMethod( "fromValue", String.class );

            return new ParamConverter<T>() {

                @Override
                public T fromString( final String value ) {
                    try {
                        return rawType.cast( fromValueMethod.invoke( null, value ) );
                    } catch ( Exception e ) {
                        throw new IllegalArgumentException( value + " cannot be converted to " + rawType);
                    }
                }

                @Override
                public String toString( final T value ) {
                    return value.toString();
                }

            };
        } catch (Exception e) {
            return null;
        }
    }

}

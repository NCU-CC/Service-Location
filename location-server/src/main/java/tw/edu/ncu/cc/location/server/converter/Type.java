package tw.edu.ncu.cc.location.server.converter;

import java.lang.reflect.Array;
import java.util.Collection;

public class Type {

    public static <F,T> T convert( F instance, Converter< F, T > converter ) {

        if( instance == null ) {
            return null;
        } else {
            return converter.convertFrom( instance );
        }
    }

    @SuppressWarnings( "unchecked" )
    public static <F,T> T[] convert( Collection<F> set, Class<T> tClass, Converter< F, T > converter ) {
        if( set == null || set.size() == 0 ) {
            return null;
        }else {
            T[] array = ( T[] ) Array.newInstance( tClass, set.size() );
            int index = 0;
            for( F f : set ) {
                array[ index ++ ] = convert( f, converter );
            }
            return array;
        }
    }

}

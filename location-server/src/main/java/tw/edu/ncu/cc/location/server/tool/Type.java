package tw.edu.ncu.cc.location.server.tool;

import tw.edu.ncu.cc.location.server.tool.convert.Converter;

public class Type {

    public static <F,T> T convert( F instance, Converter< F, T > converter ) {

        if( instance == null ) {
            return null;
        } else {
            return converter.convertFrom( instance );
        }
    }

}

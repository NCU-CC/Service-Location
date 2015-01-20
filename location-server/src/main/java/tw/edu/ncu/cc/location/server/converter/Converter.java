package tw.edu.ncu.cc.location.server.converter;

public interface Converter<F,T> {
    public T convertFrom( F instance );
}

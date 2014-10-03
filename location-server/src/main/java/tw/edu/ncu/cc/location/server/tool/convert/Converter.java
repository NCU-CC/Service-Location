package tw.edu.ncu.cc.location.server.tool.convert;

public interface Converter<F,T> {
    public T convertFrom( F instance );
}

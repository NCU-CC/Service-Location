package tw.edu.ncu.cc.location.server.data;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordType;

public class LuceneWord extends Word {

    private String index;

    public LuceneWord() { }

    public LuceneWord( String index, String word, WordType type ) {
        setIndex( index );
        setWord( word );
        setType( type );
    }

    public String getIndex() {
        return index;
    }

    public void setIndex( String index ) {
        this.index = index;
    }

}

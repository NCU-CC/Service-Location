package tw.edu.ncu.cc.location.server.helper.data

import tw.edu.ncu.cc.location.data.keyword.Word
import tw.edu.ncu.cc.location.data.keyword.WordType

public class LuceneWord extends Word {

    def String index;

    public LuceneWord() { }

    public LuceneWord( String index, String word, WordType type ) {
        setIndex( index );
        setWord( word );
        setType( type );
    }

}

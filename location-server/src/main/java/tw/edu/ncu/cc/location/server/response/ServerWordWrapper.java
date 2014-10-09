package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordWrapper;

import java.util.Set;

public class ServerWordWrapper extends WordWrapper {

    public ServerWordWrapper( Set<Word> words ) {
        setResult( words.toArray( new Word[ words.size() ] ) );
    }
}

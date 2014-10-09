package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;

import java.util.Set;

public class ServerWordWrapper extends ResultWrapper<Word> {

    public ServerWordWrapper( Set<Word> words ) {
        setResult( words.toArray( new Word[ words.size() ] ) );
    }
}

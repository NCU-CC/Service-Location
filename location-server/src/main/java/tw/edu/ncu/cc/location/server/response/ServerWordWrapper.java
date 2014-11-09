package tw.edu.ncu.cc.location.server.response;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.wrapper.ResultWrapper;

import java.util.List;

public class ServerWordWrapper extends ResultWrapper<Word> {

    public ServerWordWrapper( List<Word> words ) {
        setResult( words.toArray( new Word[ words.size() ] ) );
    }
}

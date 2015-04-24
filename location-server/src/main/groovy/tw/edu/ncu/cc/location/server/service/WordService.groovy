package tw.edu.ncu.cc.location.server.service

import tw.edu.ncu.cc.location.data.keyword.Word

public interface WordService {
    public List< Word > getWordsByKeyword( String keyword, int size );
    public void persistWords( Word... words );
    public void clearAllWords();
}

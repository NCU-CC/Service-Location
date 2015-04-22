package tw.edu.ncu.cc.location.server.service

import tw.edu.ncu.cc.location.data.keyword.Word

public interface WordService {
    public List< Word > getWords( String keyword );
    public void persistWords( Word... words );
    public void clearAllWords();
}

package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.server.data.LuceneWord;

import java.util.List;

public interface WordService {
    public List< Word > getWords( String keyword );
    public void persistWords( LuceneWord... words );
    public void clearAllWords();
}

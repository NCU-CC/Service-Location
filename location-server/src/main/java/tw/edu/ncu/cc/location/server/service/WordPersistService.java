package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

public interface WordPersistService {
    public void persistWords( LuceneWord... words );
    public void clearAllWords();
}

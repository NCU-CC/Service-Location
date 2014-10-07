package tw.edu.ncu.cc.location.server.db.model.abstracts;

import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

import java.util.Set;

public interface WordModel {
    public void persistWords( LuceneWord... words );
    public Set<Word> getWords( String keyword );
}

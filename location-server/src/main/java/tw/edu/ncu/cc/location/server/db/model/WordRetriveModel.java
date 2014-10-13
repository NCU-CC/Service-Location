package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.data.keyword.Word;

import java.util.Set;

public interface WordRetriveModel {
    public Set<Word> getWords( String keyword );
}

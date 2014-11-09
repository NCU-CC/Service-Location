package tw.edu.ncu.cc.location.server.db.model;

import tw.edu.ncu.cc.location.data.keyword.Word;

import java.util.List;

public interface WordRetriveModel {
    public List<Word> getWords( String keyword );
}

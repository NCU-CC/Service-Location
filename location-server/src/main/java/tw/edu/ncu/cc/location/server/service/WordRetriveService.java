package tw.edu.ncu.cc.location.server.service;

import tw.edu.ncu.cc.location.data.keyword.Word;

import java.util.List;

public interface WordRetriveService {
    public List<Word> getWords( String keyword );
}

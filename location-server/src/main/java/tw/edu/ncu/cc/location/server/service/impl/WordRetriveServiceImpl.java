package tw.edu.ncu.cc.location.server.service.impl;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.service.WordRetriveService;
import tw.edu.ncu.cc.location.server.service.tool.LuceneReadTool;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WordRetriveServiceImpl extends LuceneReadTool implements WordRetriveService {

    @Override
    public List<Word> getWords( String keyword ) {
        try {
            List<Word> words = new LinkedList<>();
            for( Document document : searchDocuments( "index", keyword, 3 ) ) {
                Word word = new Word();
                word.setWord( document.get( "word" ) );
                word.setType( WordType.valueOf( document.get( "type" ) ) );
                words.add( word );
            }
            return words;
        } catch ( IOException | ParseException e ) {
            throw new RuntimeException( "read keyword error", e );
        }
    }

}

package tw.edu.ncu.cc.location.server.db.model;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.queryparser.classic.ParseException;
import tw.edu.ncu.cc.location.data.keyword.Word;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.db.model.abstracts.WordModel;
import tw.edu.ncu.cc.location.server.db.model.base.LuceneAccessTool;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordModelImpl extends LuceneAccessTool implements WordModel {

    @Override
    public void persistWords( LuceneWord... words ) {
        try {
            Document[] documents = new Document[ words.length ];
            for( int i = 0; i < words.length; i ++ ) {
                documents[ i ] = new Document();
                documents[ i ].add( new TextField( "index", words[ i ].getIndex(), Field.Store.NO ) );
                documents[ i ].add( new TextField( "word",  words[ i ].getWord(),  Field.Store.YES ) );
                documents[ i ].add( new TextField( "type",  words[ i ].getType().name(), Field.Store.YES ) );
            }
            persistDocuments( "word", documents );
        } catch ( IOException e ) {
           throw new RuntimeException( "persist error", e );
        }
    }

    @Override
    public Set<Word> getWords( String keyword ) {
        try {
            Set<Word> words = new HashSet<>();
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

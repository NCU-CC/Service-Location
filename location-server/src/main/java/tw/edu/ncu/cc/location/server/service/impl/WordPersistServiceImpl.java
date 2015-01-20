package tw.edu.ncu.cc.location.server.service.impl;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;
import tw.edu.ncu.cc.location.server.service.WordPersistService;
import tw.edu.ncu.cc.location.server.service.tool.LuceneWriteTool;

import java.io.IOException;

public class WordPersistServiceImpl extends LuceneWriteTool implements WordPersistService {

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
    public void clearAllWords() {
        try {
            deleteAll();
        } catch ( IOException e ) {
            throw new RuntimeException( "cannot delete all indexes", e );
        }
    }

}

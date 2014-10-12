package tw.edu.ncu.cc.location.server.listener;

import org.apache.lucene.index.IndexWriter;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import tw.edu.ncu.cc.location.data.keyword.WordType;
import tw.edu.ncu.cc.location.server.db.HibernateUtil;
import tw.edu.ncu.cc.location.server.db.model.WordPersistModelImpl;
import tw.edu.ncu.cc.location.server.listener.task.IndexUpdateTask;
import tw.edu.ncu.cc.location.server.lucene.LuceneWord;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LuceneInitListener implements ApplicationEventListener {

    @Context
    private ServiceLocator locator;
    @Inject
    private HibernateUtil hibernateUtil;

    private ScheduledExecutorService scheduler;

    @Override
    public void onEvent( ApplicationEvent event ) {
        if( event.getType() == ApplicationEvent.Type.INITIALIZATION_START ) {
            onStart();
        } else if ( event.getType() == ApplicationEvent.Type.DESTROY_FINISHED ) {
            onDestroy();
        }
    }

    private void onStart() {
        initLuceneWorkSpace();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( runAndGetTask(), 24, 24, TimeUnit.HOURS );
    }

    private void initLuceneWorkSpace() {
        String testFieldIndex = "_test";
        String testFieldValue = "_lucene_test";

        ServiceHandle<IndexWriter> writerHandle = locator.getServiceHandle( IndexWriter.class );

        try {
            WordPersistModelImpl wordModel = new WordPersistModelImpl();
            wordModel.setIndexWriter( writerHandle.getService() );
            wordModel.persistWords(
                    new LuceneWord( testFieldIndex, testFieldValue, WordType.PLACE )
            );
        }finally {
            writerHandle.destroy();
        }
    }

    private Runnable runAndGetTask() {
        Runnable indexTask = new IndexUpdateTask( hibernateUtil, locator.getServiceHandle( IndexWriter.class ) );
        indexTask.run();
        return indexTask;
    }

    private void onDestroy() {
        scheduler.shutdownNow();
    }

    @Override
    public RequestEventListener onRequest( RequestEvent requestEvent ) {
        return null;
    }

}

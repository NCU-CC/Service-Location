package tw.edu.ncu.cc.location.server.listener;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.listener.task.DataUpdateTask;

import javax.inject.Inject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerInitListener implements ApplicationEventListener {

    @Inject
    PlaceModel placeModel;

    private ScheduledExecutorService scheduler;

    @Override
    public void onEvent( ApplicationEvent event ) {
        if( event.getType() == ApplicationEvent.Type.INITIALIZATION_FINISHED ) {
            onStart();
        } else if ( event.getType() == ApplicationEvent.Type.DESTROY_FINISHED ) {
            onDestroy();
        }
    }

    private void onStart() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( new DataUpdateTask(), 0, 3, TimeUnit.DAYS );
    }

    private void onDestroy() {
        scheduler.shutdownNow();
    }

    @Override
    public RequestEventListener onRequest( RequestEvent requestEvent ) {
        return null;
    }

}

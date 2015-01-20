package tw.edu.ncu.cc.location.server;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import tw.edu.ncu.cc.location.server.factory.*;
import tw.edu.ncu.cc.location.server.listener.LuceneInitListener;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;
import tw.edu.ncu.cc.location.server.service.*;
import tw.edu.ncu.cc.location.server.service.impl.*;
import tw.edu.ncu.cc.location.server.service.tool.HibernateUtil;

import javax.inject.Singleton;

public class LocationApplication extends ResourceConfig {

    public LocationApplication() {

        packages( "tw.edu.ncu.cc" );

        register( JacksonFeature.class );
        register( LuceneInitListener.class );

        register( new AbstractBinder() {
            @Override
            protected void configure() {

                bind( PersonServiceImpl.class ).to( PersonService.class );
                bind( UnitServiceImpl.class ).to( UnitService.class );
                bind( PlaceServiceImpl.class ).to( PlaceService.class );

                bind( WordPersistServiceImpl.class ).to( WordPersistService.class );
                bind( WordRetriveServiceImpl.class ).to( WordRetriveService.class );

                bindFactory( LuceneConfigFactory.class ).to( LuceneConfig.class ).in( Singleton.class );

                bindFactory( IndexWriterFactory.class ).to( IndexWriter.class );
                bindFactory( IndexSearcherFactory.class ).to( IndexSearcher.class );

                bindFactory( HibernateUtilFactory.class ).to( HibernateUtil.class ).in( Singleton.class );
                bindFactory( HibernateSessionFactory.class ).to( Session.class ).in( RequestScoped.class );

            }
        } );

    }
}

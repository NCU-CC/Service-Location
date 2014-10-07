package tw.edu.ncu.cc.location.server;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import tw.edu.ncu.cc.location.server.db.HibernateUtil;
import tw.edu.ncu.cc.location.server.db.model.PersonModelImpl;
import tw.edu.ncu.cc.location.server.db.model.PlaceModelImpl;
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl;
import tw.edu.ncu.cc.location.server.db.model.WordModelImpl;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.WordModel;
import tw.edu.ncu.cc.location.server.factory.*;
import tw.edu.ncu.cc.location.server.lucene.LuceneConfig;

import javax.inject.Singleton;

public class LocationApplication extends ResourceConfig {

    public LocationApplication() {

        packages( "tw.edu.ncu.cc" );

        register( JacksonFeature.class );
//        register( SchedulerInitListener.class );

        register( new AbstractBinder() {
            @Override
            protected void configure() {

                bind( PersonModelImpl.class ).to( PersonModel.class );
                bind( UnitModelImpl.class ).to( UnitModel.class );
                bind( PlaceModelImpl.class ).to( PlaceModel.class );
                bind( WordModelImpl.class ).to( WordModel.class );

                bindFactory( LuceneConfigFactory.class ).to( LuceneConfig.class ).in( Singleton.class );

                bindFactory( IndexWriterFactory.class ).to( IndexWriter.class );
                bindFactory( IndexSearcherFactory.class ).to( IndexSearcher.class );

                bindFactory( HibernateUtilFactory.class ).to( HibernateUtil.class ).in( Singleton.class );
                bindFactory( HibernateSessionFactory.class ).to( Session.class );

            }
        } );

    }
}

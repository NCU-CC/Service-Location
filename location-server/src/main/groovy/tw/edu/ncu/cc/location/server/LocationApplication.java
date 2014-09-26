package tw.edu.ncu.cc.location.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import tw.edu.ncu.cc.location.server.db.HibernateUtil;
import tw.edu.ncu.cc.location.server.db.model.PersonModelImpl;
import tw.edu.ncu.cc.location.server.db.model.PlaceModelImpl;
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel;
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.server.factory.HibernateSessionFactory;
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory;
import tw.edu.ncu.cc.location.server.provider.EnumParamProvider;

import javax.inject.Singleton;

public class LocationApplication extends ResourceConfig {

    public LocationApplication() {

        packages( "tw.edu.ncu.cc" );

        register( JacksonFeature.class );
        register( EnumParamProvider.class );
//        register( SchedulerInitListener.class );

        register( new AbstractBinder() {
            @Override
            protected void configure() {

                bind( PersonModelImpl.class ).to( PersonModel.class );
                bind( UnitModelImpl.class ).to( UnitModel.class );
                bind( PlaceModelImpl.class ).to( PlaceModel.class );

                bindFactory( HibernateSessionFactory.class ).to( Session.class );
                bindFactory( HibernateUtilFactory.class ).to( HibernateUtil.class ).in( Singleton.class );

            }
        } );

    }
}

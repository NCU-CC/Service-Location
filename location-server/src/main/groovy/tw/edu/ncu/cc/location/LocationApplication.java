package tw.edu.ncu.cc.location;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import tw.edu.ncu.cc.location.db.HibernateUtil;
import tw.edu.ncu.cc.location.db.model.LocationModelImpl;
import tw.edu.ncu.cc.location.db.model.PersonModelImpl;
import tw.edu.ncu.cc.location.db.model.UnitModelImpl;
import tw.edu.ncu.cc.location.db.model.abstracts.LocationModel;
import tw.edu.ncu.cc.location.db.model.abstracts.PersonModel;
import tw.edu.ncu.cc.location.db.model.abstracts.UnitModel;
import tw.edu.ncu.cc.location.factory.HibernateSessionFactory;
import tw.edu.ncu.cc.location.factory.HibernateUtilFactory;
import tw.edu.ncu.cc.location.provider.EnumParamProvider;

import javax.inject.Singleton;

public class LocationApplication extends ResourceConfig {

    public LocationApplication() {

        packages( "tw.edu.ncu.cc" );

        register( JacksonFeature.class );
        register( EnumParamProvider.class );

        register( new AbstractBinder() {
            @Override
            protected void configure() {

                bind( PersonModelImpl.class ).to( PersonModel.class );
                bind( UnitModelImpl.class ).to( UnitModel.class );
                bind( LocationModelImpl.class ).to( LocationModel.class );

                bindFactory( HibernateSessionFactory.class ).to( Session.class );
                bindFactory( HibernateUtilFactory.class ).to( HibernateUtil.class ).in( Singleton.class );

            }
        } );

    }
}

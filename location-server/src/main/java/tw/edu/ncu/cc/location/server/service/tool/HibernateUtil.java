package tw.edu.ncu.cc.location.server.service.tool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

public class HibernateUtil {

    private SessionFactory sessionFactory;

    private final ThreadLocal<Session> localStateSession = new ThreadLocal<>();
    private final ThreadLocal<StatelessSession> localStatelessSession = new ThreadLocal<>();

    public HibernateUtil( SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
    }

    public Session currentSession() {
        Session session = localStateSession.get();
        if( session == null || ! session.isOpen() ) {
            session = sessionFactory.openSession();
            localStateSession.set( session );
        }
        return session;
    }

    public StatelessSession currentStatelessSession() {
        StatelessSession session = localStatelessSession.get();
        if( session == null ) {
            session = sessionFactory.openStatelessSession();
            localStatelessSession.set( session );
        }
        return session;
    }

    public void closeSession() {
        closeStateSession();
        closeStatelessSession();
    }

    private void closeStateSession() {
        Session session = localStateSession.get();
        if ( session != null ){
            session.close();
            localStateSession.remove();
        }
    }

    private void closeStatelessSession() {
        StatelessSession session = localStatelessSession.get();
        if ( session != null ){
            session.close();
            localStatelessSession.remove();
        }
    }


}
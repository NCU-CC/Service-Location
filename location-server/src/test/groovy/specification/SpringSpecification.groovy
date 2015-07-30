package specification

import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import tw.edu.ncu.cc.location.server.Application

import java.text.SimpleDateFormat

@ActiveProfiles( "test" )
@WebIntegrationTest
@ContextConfiguration ( loader = SpringApplicationContextLoader.class, classes = Application.class )
public abstract class SpringSpecification extends Specification {

    protected static Date date( String dateString ) {
        return new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( dateString );
    }

}

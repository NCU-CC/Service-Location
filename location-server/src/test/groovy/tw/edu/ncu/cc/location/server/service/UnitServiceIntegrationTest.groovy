package tw.edu.ncu.cc.location.server.service

import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.GeometryFactory
import groovy.json.JsonSlurper
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.server.db.data.UnitEntity
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.server.resource.HttpResource
import tw.edu.ncu.cc.location.server.resource.PersistSessionResource
import tw.edu.ncu.cc.location.server.resource.SessionResource

import static HttpResource.requestJSON
import static HttpResource.requestString

class UnitServiceIntegrationTest extends Specification {

    @Shared @ClassRule
    HttpResource httpResource = new HttpResource()

    @Shared @ClassRule
    SessionResource sessionResource = new PersistSessionResource()

    def setupSpec() {
        GeometryFactory geometryFactory = new GeometryFactory()

        UnitModelImpl unitModel     = new UnitModelImpl()
        unitModel.setSession( sessionResource.getSession() )
        unitModel.persistUnits(
                new UnitEntity( "code1", "cname1", "sname1", "fname1" ),
                new UnitEntity( "code2", "cname2", "sname2", "fname2" ),
                new UnitEntity( "code4", "cname4", "sname4", "fname4" ),
                new UnitEntity( "code5", "cname4", "sname4", "fname4",
                        geometryFactory.createPoint( new Coordinate( 1.0, 1.0 ) )
                )
        )
    }

    def "server can return all units by name 1"() {
        when:
            def response = requestJSON( "/unit/name/fname1" )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "unitCode"   : "code1",
                        "chineseName": "cname1",
                        "englishName":  null,
                        "shortName"  : "sname1",
                        "fullName"   : "fname1",
                        "url" : null,
                        "location" : null
                    }
                    '''
            ) )
    }

    def "server can return all units by name 2"() {
        when:
            def response = requestJSON( "/unit/name/fname4" )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "unitCode"   : "code4",
                        "chineseName": "cname4",
                        "englishName":  null,
                        "shortName"  : "sname4",
                        "fullName"   : "fname4",
                        "url" : null,
                        "location" : null
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "unitCode"   : "code5",
                        "chineseName": "cname4",
                        "englishName":  null,
                        "shortName"  : "sname4",
                        "fullName"   : "fname4",
                        "url" : null,
                        "location" : {
                            "lat" : 1.0,
                            "lng" : 1.0
                        }
                    }
                    '''
            ) )
    }

    def "server can return all units by name 3"() {
        when:
            def response = requestString( "/unit/name/NameNotExist" )
        then:
            response == '{"result":null}'
    }


}

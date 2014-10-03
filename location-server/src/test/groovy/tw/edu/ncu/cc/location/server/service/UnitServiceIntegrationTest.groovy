package tw.edu.ncu.cc.location.server.service

import com.jayway.restassured.RestAssured
import com.vividsolutions.jts.geom.Coordinate
import com.vividsolutions.jts.geom.GeometryFactory
import groovy.json.JsonSlurper
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.UnitEntity
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory


class UnitServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initData();
    }

    private static void initData() {
        GeometryFactory geometryFactory = new GeometryFactory()
        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()
        UnitModelImpl unitModel     = new UnitModelImpl()
        unitModel.setSession( hibernateUtil.currentSession() )
        unitModel.persistUnits(
                new UnitEntity( "code1", "cname1", "sname1", "fname1" ),
                new UnitEntity( "code2", "cname2", "sname2", "fname2" ),
                new UnitEntity( "code4", "cname4", "sname4", "fname4" ),
                new UnitEntity( "code5", "cname4", "sname4", "fname4",
                        geometryFactory.createPoint( new Coordinate(1.0,1.0) )
                )
        )
        hibernateUtil.closeSession()
    }

    def "server can return all units by name 1"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/unit/name/cname1" ).asString()
            )
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
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/unit/name/cname4" ).asString()
            )
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
            def response = RestAssured.get( "/unit/name/NameNotExist" ).asString()
        then:
            response == '{"result":null}'
    }


}

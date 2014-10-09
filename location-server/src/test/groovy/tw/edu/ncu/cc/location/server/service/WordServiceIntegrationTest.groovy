package tw.edu.ncu.cc.location.server.service

import com.jayway.restassured.RestAssured
import groovy.json.JsonSlurper
import org.apache.lucene.index.IndexWriter
import org.hibernate.Session
import spock.lang.Specification
import tool.RestAssuredTestConfiguer
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.HibernateUtil
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity
import tw.edu.ncu.cc.location.server.db.model.PersonModelImpl
import tw.edu.ncu.cc.location.server.db.model.PlaceModelImpl
import tw.edu.ncu.cc.location.server.db.model.UnitModelImpl
import tw.edu.ncu.cc.location.server.db.model.WordPersistModelImpl
import tw.edu.ncu.cc.location.server.db.model.abstracts.PersonModel
import tw.edu.ncu.cc.location.server.db.model.abstracts.PlaceModel
import tw.edu.ncu.cc.location.server.db.model.abstracts.UnitModel
import tw.edu.ncu.cc.location.server.db.model.abstracts.WordPersistModel
import tw.edu.ncu.cc.location.server.factory.HibernateUtilFactory
import tw.edu.ncu.cc.location.server.factory.IndexWriterFactory
import tw.edu.ncu.cc.location.server.factory.LuceneConfigFactory
import tw.edu.ncu.cc.location.server.listener.task.IndexUpdateTask


class WordServiceIntegrationTest extends Specification {

    def setupSpec() {
        RestAssuredTestConfiguer.configure()
        initTestData();
    }

    private static void initTestData() {

        HibernateUtil hibernateUtil = new HibernateUtilFactory().provide()
        IndexWriterFactory factory  = new IndexWriterFactory( new LuceneConfigFactory().provide() )
        IndexWriter writer = factory.provide();

        PlaceModel placeModel   = initPlaceModelAndData( hibernateUtil.currentSession() )
        PersonModel personModel = initPersonModel( hibernateUtil.currentSession() )
        UnitModel unitModel     = initUnitModel( hibernateUtil.currentSession() )
        WordPersistModel wordModel = initWordModel( writer )

        initWordIndex( placeModel, personModel, unitModel, wordModel )

        factory.dispose( writer )
        hibernateUtil.closeSession()
    }

    private static PlaceModel initPlaceModelAndData( Session session ) {
        PlaceModel placeModel = new PlaceModelImpl()
        placeModel.setSession( session )
        placeModel.persistPlace(
                new PlaceEntity( PlaceType.SCENE, "國泰樹-第一棵" ),
                new PlaceEntity( PlaceType.SCENE, "國泰樹-第二棵" ),
                new PlaceEntity( PlaceType.EMERGENCY_TEL, "緊急電話" )
        )
        return placeModel
    }

    private static PersonModel initPersonModel( Session session ) {
        PersonModel personModel = new PersonModelImpl()
        personModel.setSession( session )
        return personModel
    }

    private static UnitModel initUnitModel( Session session ) {
        UnitModel unitModel = new UnitModelImpl()
        unitModel.setSession( session )
        return unitModel
    }

    private static WordPersistModel initWordModel( IndexWriter writer ) {
        WordPersistModel wordModel = new WordPersistModelImpl()
        wordModel.setIndexWriter( writer )
        return wordModel;
    }

    private static void initWordIndex( PlaceModel placeModel, PersonModel personModel,
                                       UnitModel unitModel, WordPersistModel wordPersistModel ) {
        IndexUpdateTask.clearIndexes( wordPersistModel );
        IndexUpdateTask.indexPlaces( wordPersistModel, placeModel );
        IndexUpdateTask.indexPeople( wordPersistModel, personModel );
        IndexUpdateTask.indexUnits( wordPersistModel, unitModel );
    }

    def "server can return origin word of given keyword 1"() {
        when:
            def msg = RestAssured.get( "/keyword/國泰樹" ).asString()
            def response = new JsonSlurper().parseText(
                    msg
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "word" : "國泰樹-第一棵",
                        "type" : "PLACE"
                    }
                    '''
            ) )
        and:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "word" : "國泰樹-第二棵",
                        "type" : "PLACE"
                    }
                    '''
            ) )
    }

    def "server can return origin word of given keyword 2"() {
        when:
            def response = new JsonSlurper().parseText(
                    RestAssured.get( "/keyword/緊急" ).asString()
            )
        then:
            response.result.contains( new JsonSlurper().parseText(
                    '''
                    {
                        "word" : "緊急電話",
                        "type" : "PLACE"
                    }
                    '''
            ) )
    }

}

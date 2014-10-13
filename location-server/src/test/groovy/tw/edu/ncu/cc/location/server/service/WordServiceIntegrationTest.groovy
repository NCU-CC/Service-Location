package tw.edu.ncu.cc.location.server.service

import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification
import tw.edu.ncu.cc.location.data.place.PlaceType
import tw.edu.ncu.cc.location.server.db.data.PlaceEntity
import tw.edu.ncu.cc.location.server.db.model.PlaceModel
import tw.edu.ncu.cc.location.server.db.model.WordPersistModel
import tw.edu.ncu.cc.location.server.db.model.impl.PlaceModelImpl
import tw.edu.ncu.cc.location.server.db.model.impl.WordPersistModelImpl
import tw.edu.ncu.cc.location.server.listener.task.IndexUpdateTask
import tw.edu.ncu.cc.location.server.resource.HttpResource
import tw.edu.ncu.cc.location.server.resource.IndexResource
import tw.edu.ncu.cc.location.server.resource.PersistSessionResource
import tw.edu.ncu.cc.location.server.resource.SessionResource

import static HttpResource.JSON
import static tw.edu.ncu.cc.location.server.resource.HttpResource.requestJSON

class WordServiceIntegrationTest extends Specification {

    @Shared @ClassRule
    HttpResource httpResource = new HttpResource()

    @Shared @ClassRule
    SessionResource sessionResource = new PersistSessionResource()

    @Shared @ClassRule
    IndexResource indexResource = new IndexResource()

    def setupSpec() {
        PlaceModel placeModel = new PlaceModelImpl()
        placeModel.setSession( sessionResource.getSession() )
        placeModel.persistPlace(
                new PlaceEntity( PlaceType.SCENE, "國泰樹-第一棵" ),
                new PlaceEntity( PlaceType.SCENE, "國泰樹-第二棵" ),
                new PlaceEntity( PlaceType.EMERGENCY_TEL, "緊急電話" )
        )

        WordPersistModel wordPersistModel = new WordPersistModelImpl()
        wordPersistModel.setIndexWriter( indexResource.getIndexWriter() )

        IndexUpdateTask.indexPlaces ( wordPersistModel, placeModel );
    }

    def "server can return origin word of given keyword 1"() {
        when:
            def response = requestJSON( "/keyword/國泰樹" )
        then:
            response.result.contains( JSON(
                    '''
                    {
                        "word" : "國泰樹-第一棵",
                        "type" : "PLACE"
                    }
                    '''
            ) )
        and:
            response.result.contains( JSON(
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
            def response = requestJSON( "/keyword/緊急" )
        then:
            response.result.contains( JSON(
                    '''
                    {
                        "word" : "緊急電話",
                        "type" : "PLACE"
                    }
                    '''
            ) )
    }

}

package tw.edu.ncu.cc.location.server.lucene

import spock.lang.Specification


class LuceneConfigTest extends Specification {

    private LuceneConfig luceneConfig

    def setup() {
        luceneConfig = new LuceneConfig()
    }

    def "it has property of indexFilePath in String"() {
        when:
            luceneConfig.setIndexFilePath( "path" )
        then:
            luceneConfig.getIndexFilePath() == "path"
    }

    def "it can construct by other way"() {
        when:
            def config = new LuceneConfig( "path" )
        then:
            config.getIndexFilePath() == "path"
    }

}

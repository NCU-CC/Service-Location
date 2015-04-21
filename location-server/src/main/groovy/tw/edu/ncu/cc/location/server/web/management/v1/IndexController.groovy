package tw.edu.ncu.cc.location.server.web.management.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import tw.edu.ncu.cc.location.server.task.IndexUpdateTask

@RestController
@RequestMapping( value = "management/v1", method = RequestMethod.GET )
public class IndexController {

    @Autowired
    def IndexUpdateTask indexUpdateTask

    @RequestMapping( value = "reindex" )
    public ResponseEntity reindex() {
        indexUpdateTask.run()
        new ResponseEntity<>( "success", HttpStatus.OK )
    }

}

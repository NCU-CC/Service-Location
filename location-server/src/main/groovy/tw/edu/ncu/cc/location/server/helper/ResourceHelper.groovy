package tw.edu.ncu.cc.location.server.helper

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpServerErrorException


class ResourceHelper {

    static def nullNotFound( resource, String message = "requested resource is not found" ) {
        if( resource == null ) {
            throw new HttpServerErrorException( HttpStatus.NOT_FOUND, message )
        }
        return resource
    }

}

package tw.edu.ncu.cc.location.server.exception.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
public class ApplicationExceptionHandler {

    private Logger logger = LoggerFactory.getLogger( this.getClass() )

    @ExceptionHandler( MissingServletRequestParameterException )
    public ResponseEntity missingParameter( MissingServletRequestParameterException e ) {
        new ResponseEntity<>(
                "missing parameters:" + e.parameterName, HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler( TypeMismatchException )
    public ResponseEntity badArgument( TypeMismatchException e ) {
        new ResponseEntity<>(
                "bad argument:" + e.getValue() , HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity unhandledException( Exception e ) {
        logger.error( "UNEXPECTED ERROR", e )
        new ResponseEntity<>(
                e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}

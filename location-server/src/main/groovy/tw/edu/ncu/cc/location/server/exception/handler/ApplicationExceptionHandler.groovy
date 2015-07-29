package tw.edu.ncu.cc.location.server.exception.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpServerErrorException
import tw.edu.ncu.cc.oauth.data.v1.message.ErrorObject

@ControllerAdvice
public class ApplicationExceptionHandler {

    private Logger logger = LoggerFactory.getLogger( this.getClass() )

    @ExceptionHandler( HttpServerErrorException )
    def ResponseEntity httpServerError( HttpServerErrorException e ) {
        new ResponseEntity<>(
                new ErrorObject( e.message ), e.statusCode
        )
    }

    @ExceptionHandler( MissingServletRequestParameterException )
    def ResponseEntity invalidParams( MissingServletRequestParameterException e ) {
        new ResponseEntity<>(
                new ErrorObject(
                        "missing parameter:" + e.parameterName
                ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler( [ HttpMessageNotReadableException, HttpMediaTypeNotSupportedException ] )
    def ResponseEntity invalidRequestBodyFormat() {
        new ResponseEntity<>(
                new ErrorObject(
                        "expect request in json format"
                ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler( HttpRequestMethodNotSupportedException )
    def ResponseEntity invalidMethod( HttpRequestMethodNotSupportedException e ) {
        new ResponseEntity<>(
                new ErrorObject(
                        ( "method not supported:" + e.method + ", expect:" + Arrays.toString( e.getSupportedMethods() ) ) as String
                ), HttpStatus.METHOD_NOT_ALLOWED
        )
    }

    @ExceptionHandler( TypeMismatchException )
    public ResponseEntity badArgument( TypeMismatchException e ) {
        new ResponseEntity<>(
                new ErrorObject(
                        "bad argument:" + e.getValue()
                ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity unhandledException( Exception e ) {
        logger.error( "UNEXPECTED ERROR", e )
        new ResponseEntity<>(
                new ErrorObject(
                        e.message
                ), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}

package helper

import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.request.RequestPostProcessor


class IpMockMvcRequestProcessor implements RequestPostProcessor {

    static IpMockMvcRequestProcessor remoteAddress( String remoteIp ) {
        new IpMockMvcRequestProcessor( remoteIp )
    }

    private String remoteAddress

    IpMockMvcRequestProcessor( String remoteAddress ) {
        this.remoteAddress = remoteAddress
    }

    @Override
    MockHttpServletRequest postProcessRequest( MockHttpServletRequest request ) {
        request.setRemoteAddr( remoteAddress )
        request
    }

}

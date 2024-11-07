package spring.template.mediasocial.configuration.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Configuration
public class CustomRequestLoggingFilter extends CommonsRequestLoggingFilter {
    public CustomRequestLoggingFilter() {
        super.setIncludeClientInfo(true);
        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(10000);
    }
    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        filterChain.doFilter(wrappedRequest, response);
        logRequestBody(wrappedRequest);
    }

    private void logRequestBody(ContentCachingRequestWrapper request) {
        String requestBody = new String(request.getContentAsByteArray());
        System.out.println("=========== Request endpoint ====================");
        System.out.println("URL         : " + request.getRequestURL());
//        System.out.println("Header User ID :" + (request.getHeader(ConstantVariable.ID_USER) != null ? request.getHeader(ConstantVariable.ID_USER) : "No User ID"));
        System.out.println("Method      : " + request.getMethod());
        System.out.println("Client IP   : " + request.getRemoteAddr());
        System.out.println("Query       : " + (request.getQueryString() != null ? request.getQueryString() : "No query parameters"));
        System.out.println("Request Body: " + requestBody);
        System.out.println("============ END Request endpoint ================");
    }
}

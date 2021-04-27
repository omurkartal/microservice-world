package edu.omur.msworld.corelib.core;

import edu.omur.msworld.corelib.model.Constants;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateClientInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(Constants.KEY_CONSUMER_SERVICE, ThreadContext.get(Constants.KEY_APPLICATION_NAME));
        headers.add(Constants.KEY_UUID, ThreadContext.get(Constants.KEY_UUID));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}

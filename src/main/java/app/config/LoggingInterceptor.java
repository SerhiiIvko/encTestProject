package app.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private final Logger LOGGER = Logger.getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("===========================request begin================================================");
            LOGGER.debug("Method      : {}" + request.getMethod());
            LOGGER.debug("Request body: {}" + new String(body, StandardCharsets.UTF_8));
            LOGGER.debug("==========================request end================================================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("============================response begin==========================================");
            LOGGER.debug("Response body: {}" + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            LOGGER.debug("=======================response end=================================================");
        }
    }

    @Bean
    public RestTemplate createRestTemplate(LoggingInterceptor loggingInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(loggingInterceptor));
        return restTemplate;
    }
}


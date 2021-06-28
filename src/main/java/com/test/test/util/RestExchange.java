package com.test.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestExchange {
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RestExchange.class);

    public RestExchange() {
    }

    public <T> ResponseEntity<T> exchange(String url, Object payload, HttpMethod method, Class<T> clazz) {
        return this.exchange(url, payload, method, (HttpHeaders)null, clazz, (Consumer)null);
    }

    public <T> ResponseEntity<T> exchange(String url, Object payload, HttpMethod method, HttpHeaders httpHeaders, Class<T> clazz) {
        return this.exchange(url, payload, method, httpHeaders, clazz, (Consumer)null);
    }

    public <T> ResponseEntity<T> exchange(String url, Object payload, HttpMethod method, Class<T> clazz, Consumer<RestPayload> consumer) {
        return this.exchange(url, payload, method, (HttpHeaders)null, clazz, consumer);
    }

    public <T> ResponseEntity<T> exchange(String url, Object payload, HttpMethod httpMethod, HttpHeaders httpHeaders, Class<T> clazz, Consumer<RestPayload> consumer) {
        HttpEntity<?> entity = this.generateHttpEntity(payload, httpHeaders);
        String httpHeadersInStr = JacksonUtils.getPlainJson(entity.getHeaders());
        String payloadRq = JacksonUtils.getPlainJson(payload);
        String payloadRs = null;
        HttpStatus httpStatusRs = null;

        ResponseEntity exchange;
        try {
            this.info(url, payload, httpMethod, httpHeadersInStr);
            exchange = this.restTemplate.exchange(url, httpMethod, entity, clazz, new Object[0]);
            String restExchangeRs = JacksonUtils.getPlainJson(exchange);
            logger.info("<RestExchange_Response> {}", restExchangeRs);
            if (consumer != null) {
                payloadRs = JacksonUtils.getPlainJson(exchange.getBody());
                httpStatusRs = exchange.getStatusCode();
            }
        } catch (HttpStatusCodeException var21) {
            HttpStatusCodeException ex = var21;
            payloadRs = var21.getResponseBodyAsString();
            logger.error(payloadRs, var21);

            try {
                httpStatusRs = ex.getStatusCode();
            } catch (Exception var20) {
                httpStatusRs = HttpStatus.INTERNAL_SERVER_ERROR;
                logger.error("RestExchange can`t get status code");
            }

            logger.error("<RestExchange_HttpStatusCodeException> response -> {}, httpStatus {}", payloadRs, httpStatusRs);
            T desserialize = this.desserialize(clazz, payloadRs);
            exchange = new ResponseEntity(desserialize, httpStatusRs);
        } catch (Exception var22) {
            logger.error("Exception exchange", var22);
            exchange = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (consumer != null) {
                this.acceptRqConsumer(url, httpMethod, consumer, httpHeadersInStr, payloadRq, payloadRs, httpStatusRs);
            }

        }

        return exchange;
    }

    private void acceptRqConsumer(String url, HttpMethod httpMethodRq, Consumer<RestPayload> consumerRq, String httpHeadersInStr, String payloadRq, String payloadRs, HttpStatus httpStatusRs) {
        RestPayload requestPayload = new RestPayload();
        requestPayload.setHttpMethodRq(httpMethodRq);
        requestPayload.setUrlRq(url);
        requestPayload.setBodyRq(payloadRq);
        requestPayload.setHttpHeadersRq(httpHeadersInStr);
        requestPayload.setBodyRs(payloadRs);
        requestPayload.setHttpStatusRs(httpStatusRs);
        CompletableFuture.runAsync(() -> {
            consumerRq.accept(requestPayload);
        });
    }

    private void info(String url, Object payload, HttpMethod method, String httpHeadersInStr) {
        String payloadInStr = JacksonUtils.getPlainJson(payload);
        logger.info("<RestExchange_Request>{}, method: {}, with payload: {}, headers {}", new Object[]{url, method, payloadInStr, httpHeadersInStr});
    }

    private <T> T desserialize(Class<T> valueType, String strKey) {
        T valueKey;
        try {
            ObjectMapper mapper = new ObjectMapper();
            valueKey = mapper.readValue(strKey, valueType);
        } catch (IOException var5) {
            logger.error("Couldnt desserializate ", var5);
            valueKey = null;
        }

        return valueKey;
    }

    private HttpEntity<?> generateHttpEntity(Object payload, HttpHeaders headers) {
        if (headers == null) {
            headers = new HttpHeaders();
        }

        if (!headers.containsKey("Content-Type")) {
            headers.set("Content-Type", "application/json;charset=UTF-8");
        }

        return payload != null ? new HttpEntity(payload, headers) : new HttpEntity(headers);
    }
}
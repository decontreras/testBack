package com.test.test.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class RestPayload {
    private String bodyRq;
    private String urlRq;
    private HttpMethod httpMethodRq;
    private String httpHeadersRq;
    private String bodyRs;
    private HttpStatus httpStatusRs;

    public RestPayload() {
    }

    public String getBodyRq() {
        return this.bodyRq;
    }

    public void setBodyRq(String payloadRq) {
        this.bodyRq = payloadRq;
    }

    public String getUrlRq() {
        return this.urlRq;
    }

    public void setUrlRq(String url) {
        this.urlRq = url;
    }

    public HttpMethod getHttpMethodRq() {
        return this.httpMethodRq;
    }

    public void setHttpMethodRq(HttpMethod httpMethod) {
        this.httpMethodRq = httpMethod;
    }

    public String getHttpHeadersRq() {
        return this.httpHeadersRq;
    }

    public void setHttpHeadersRq(String httpHeaders) {
        this.httpHeadersRq = httpHeaders;
    }

    public String getBodyRs() {
        return this.bodyRs;
    }

    public void setBodyRs(String payloadRs) {
        this.bodyRs = payloadRs;
    }

    public HttpStatus getHttpStatusRs() {
        return this.httpStatusRs;
    }

    public void setHttpStatusRs(HttpStatus httpStatusRs) {
        this.httpStatusRs = httpStatusRs;
    }
}

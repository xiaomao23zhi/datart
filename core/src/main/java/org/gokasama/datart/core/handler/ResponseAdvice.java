package org.gokasama.datart.core.handler;

import org.gokasama.datart.core.annotation.IgnorReponseAdvice;
import org.gokasama.datart.core.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ka wujia@chinamobile.com
 */
@RestControllerAdvice("org.gokasama.datart")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        return !methodParameter.getDeclaringClass().isAnnotationPresent(IgnorReponseAdvice.class) &&
                !methodParameter.getMethod().isAnnotationPresent(IgnorReponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body instanceof ApiResponse) {
            return body;
        }

//        if (body instanceof String) {
//            return JSON.toJSON(ApiResponse.success(body)).toString();
//        }

        return ApiResponse.success(body);
    }

}

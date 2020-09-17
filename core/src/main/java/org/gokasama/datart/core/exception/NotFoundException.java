package org.gokasama.datart.core.exception;

import org.gokasama.datart.core.enums.ApiExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ka wujia@chinamobile.com
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {

    private static final long serialVersionUID = 5365536761315116334L;

    public NotFoundException(String message) {
        super(ApiExceptionCode.NOT_FOUND.getCode(), ApiExceptionCode.NOT_FOUND.getError(), message);
    }
}

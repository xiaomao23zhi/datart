package org.gokasama.datart.core.exception;

import org.gokasama.datart.core.enums.ApiExceptionCode;

/**
 * @author ka wujia@chinamobile.com
 */
public class RedisLockException extends ApiException {

    private static final long serialVersionUID = -3238705073358646169L;

    public RedisLockException(String message) {
        super(ApiExceptionCode.ERROR.getCode(), ApiExceptionCode.ERROR.getError(), message);
    }
}

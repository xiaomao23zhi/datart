package org.gokasama.datart.manager.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ka wujia@chinamobile.com
 */
@Getter
@Setter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 7220250546252067098L;

    private int code;
    private String error;

    public ApiException(Integer code, String error, String message) {
        super(message);
        this.code = code;
        this.error = error;
    }
}

package org.gokasama.datart.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ka wujia@chinamobile.com
 */
@Getter
@AllArgsConstructor
public enum ApiExceptionCode {
    OK(200, "SUCCESS"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    ERROR(500, "ERROR"),
    ;

    private int code;
    private String error;
}
    
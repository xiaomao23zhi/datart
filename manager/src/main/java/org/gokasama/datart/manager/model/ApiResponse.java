package org.gokasama.datart.manager.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gokasama.datart.manager.enums.ApiExceptionCode;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author ka wujia@chinamobile.com
 */
@ApiModel(value = "接口返回")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -1678404789428662364L;

    //
    private int status;

    //
    private String error;

    //
    private String message;

    //
    private Date timestamp;

    //
    private T body;

    /**
     * @param data data
     * @return T
     */
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status(ApiExceptionCode.OK.getCode())
                .error(ApiExceptionCode.OK.getError())
                .message("")
                .timestamp(Date.from(Instant.now()))
                .body(data)
                .build();
    }

    /**
     *
     * @param status status
     * @param error error
     * @param message message
     * @param data data
     * @param <T> T
     * @return T
     */
    public static <T> ApiResponse<T> fail(int status, String error, String message, T data) {
        return ApiResponse.<T>builder()
                .status(status)
                .error(error)
                .message(message)
                .timestamp(Date.from(Instant.now()))
                .body(data)
                .build();
    }
}

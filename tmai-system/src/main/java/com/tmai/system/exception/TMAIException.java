package com.tmai.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @Author Created by DK
 * @Date 2023-03-07 19:58
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class TMAIException extends RuntimeException {

    private  ErrorCode code;

    private String message;

    public TMAIException(ErrorCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    @AllArgsConstructor
    @Getter
    public enum ErrorCode{
        /**
         * 任务超过限制
         */
        TASK_LIMIT_EXCEEDED(10001),

        /**
         * 请求出错
         */
        REQUEST_ERROR(10002),
        ;
        private final int code;
    }
}

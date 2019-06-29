package com.cseu.server.gateway.base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: CesuException
 * @projectName cseu-parent
 * @description: TODO
 * @author: yalong.xue
 * @date 2019-06-2820:18
 */

@Data
public class CesuException extends Exception {

    private String message;

    private ErrorEnum errorEnum;

    public CesuException() {
        super();
    }

    public CesuException(String message) {
        super(message);
    }

    public CesuException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public static enum ErrorEnum {
        USER_NOT_EXISTS(1001, "该用户不存在");

        private int code;
        private String message;

        ErrorEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}

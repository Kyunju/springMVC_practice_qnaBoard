package com.codestates.qnaBoardProject.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    QNA_NOT_FOUND(404, "Qna not found"),
    UNAUTHORIZED_DELETE_QUESTION(403, "Forbidden: User is not authorized to delete this question"),
    UNAUTHORIZED_MODIFY_QUESTION(403, "Forbidden : User is not authorized to modify this question"),
    UNAUTHORIZED_GET_QUESTION(403, "Forbidden : User is not authorized to see this question, this is secret question"),
    DELETED_QUESTION(404, "Not Found : Deleted Question");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

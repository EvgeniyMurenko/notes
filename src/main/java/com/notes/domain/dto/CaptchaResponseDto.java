package com.notes.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {

    private boolean isSuccess;
    @JsonAlias("error-codes")
    private Set<String> errorCode;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Set<String> getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Set<String> errorCode) {
        this.errorCode = errorCode;
    }
}

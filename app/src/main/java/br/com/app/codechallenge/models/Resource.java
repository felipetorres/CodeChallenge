package br.com.app.codechallenge.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @Nullable private T data;
    @Nullable private String errorMessage;
    @NonNull private Status status;

    Resource() {
        this.status = Status.LOADING;
        this.data = null;
        this.errorMessage = null;
    }

    Resource<T> success(@NonNull T resource) {
        this.status = Status.SUCCESS;
        this.errorMessage = null;
        this.data = resource;
        return this;
    }

    Resource<T> failure() {
        this.status = Status.FAILURE;
        this.errorMessage = "Request failed";
        this.data = null;
        return this;
    }

    Resource<T> failure(int code) {
        this.status = Status.FAILURE;
        this.errorMessage = "Request failed with code: " + code;
        this.data = null;
        return this;
    }

    @NonNull
    public Status getStatus() { return status; }

    @Nullable
    public T getData() { return data; }

    @Nullable
    public String getErrorMessage() { return errorMessage; }

    public enum Status {
        LOADING, SUCCESS, FAILURE
    }
}

package br.com.app.codechallenge.models;

import androidx.lifecycle.MutableLiveData;

public class StateLiveData<T> extends MutableLiveData<Resource<T>> {

    public void setLoading() { setValue(new Resource<>()); }

    public void setSuccess(T data) { setValue(new Resource<T>().success(data)); }

    public void setFailure() { setValue(new Resource<T>().failure()); }

    public void setFailure(int code) { setValue(new Resource<T>().failure(code)); }
}

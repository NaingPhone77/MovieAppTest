package com.example.movieapptest.helper

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.movieapptest.helper.AsyncViewResource

public class AsyncResourceLiveData<T> : LiveData<AsyncViewResource<T>>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in AsyncViewResource<T>>) {

        if (hasActiveObservers()){
            Log.d("MainActivity", "Hello")
        }

        super.observe(owner, observer)
    }


    fun postLoading(){
        this.postValue(AsyncViewResource.Loading())
    }

    fun postSuccess(data : T){
        this.postValue(AsyncViewResource.Success(data))
    }

    fun postFail(exception : Throwable, error : String = ""){
        this.postValue(AsyncViewResource.Error(exception, error))
    }

}
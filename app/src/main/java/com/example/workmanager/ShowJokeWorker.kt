package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ShowJokeWorker constructor(val context: Context, parameters: WorkerParameters): Worker(context,parameters){
    override fun doWork(): Result {
        startWorkers()
        Log.i("mvm","do work")
        val api = GetRetrofit.getRetrofit().
            create(mJokeApiInterface::class.java)
        Log.i("mvm", api.getJokeInfo().execute().body()!!.type+api.getJokeInfo().execute().body()!!.setup)
        val output =Data.Builder()
            .putString(Constants.TYPE, api.getJokeInfo().execute().body()!!.type)
            .putString(Constants.SETUP, api.getJokeInfo().execute().body()!!.setup)
            .putString(Constants.PUNCHLINE, api.getJokeInfo().execute().body()!!.punchline)
            .build()
        return  Result.success(output)
    }
    private fun startWorkers() {
        val requestJokeInfo =
            OneTimeWorkRequest.Builder(ShowJokeWorker::class.java)
                .setInitialDelay(30, TimeUnit.SECONDS)
                .addTag(Constants.TAG_OUTPUT)
                .build()
        WorkManager.getInstance().enqueue(requestJokeInfo)
    }

}
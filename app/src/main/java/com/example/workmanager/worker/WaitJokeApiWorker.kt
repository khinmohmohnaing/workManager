package com.example.workmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmanager.Constants
import com.example.workmanager.view.activity.MainActivity

class WaitJokeApiWorker constructor(val context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        MainActivity.requestTime += 1
        Log.i("Finish", "wait 15 min")
        val output = Data.Builder()
            .putBoolean(Constants.FINISHTIME, true)
            .build()
        return Result.success(output)
    }

}
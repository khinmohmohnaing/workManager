package com.example.workmanager

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class ShowJokeViewModel : ViewModel(){
    var mWorkManager: WorkManager = WorkManager.getInstance()
    var mSavedWorkInfo: LiveData<List<WorkInfo>> = mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_OUTPUT)
    fun showJoke(){
    val periodicWork =
        OneTimeWorkRequest.Builder(ShowJokeWorker::class.java)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .addTag(Constants.TAG_OUTPUT)
            .build()
    WorkManager.getInstance().enqueue(periodicWork)
}
    internal fun getOutputWorkInfo(): LiveData<List<WorkInfo>> {
        return mSavedWorkInfo
    }
}
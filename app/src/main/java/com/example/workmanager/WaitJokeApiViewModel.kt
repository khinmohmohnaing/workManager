package com.example.workmanager

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import javax.xml.datatype.DatatypeConstants.MINUTES
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit


class WaitJokeApiViewModel : ViewModel() {

    var mWorkManager: WorkManager = WorkManager.getInstance()
    var mSavedWorkInfo: LiveData<List<WorkInfo>> =
        mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_OUTPUT_FOR_WAITJOKEAPI)

    fun waitJokeApi() {
        val workRequest =
            PeriodicWorkRequest.Builder(WaitJokeApiWorker::class.java, 15, TimeUnit.MINUTES)
                .addTag(Constants.TAG_OUTPUT_FOR_WAITJOKEAPI)
                .build()
        WorkManager.getInstance().enqueue(workRequest)
    }

    internal fun getOutPut(): LiveData<List<WorkInfo>> {
        return mSavedWorkInfo
    }
}
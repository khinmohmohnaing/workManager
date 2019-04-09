package com.example.workmanager.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.PeriodicWorkRequest
import com.example.workmanager.Constants
import com.example.workmanager.worker.WaitJokeApiWorker
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
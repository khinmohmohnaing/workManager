package com.example.workmanager.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.workmanager.Constants
import com.example.workmanager.worker.ShowJokeWorker

class ShowJokeViewModel : ViewModel(){
    var mWorkManager: WorkManager = WorkManager.getInstance()
    var mSavedWorkInfo: LiveData<List<WorkInfo>> = mWorkManager.getWorkInfosByTagLiveData(Constants.TAG_OUTPUT)
    fun showJoke(){
    val requestJokeInfo =
        OneTimeWorkRequest.Builder(ShowJokeWorker::class.java)
            .addTag(Constants.TAG_OUTPUT)
            .build()
    WorkManager.getInstance().enqueue(requestJokeInfo)
}
    internal fun getOutputWorkInfo(): LiveData<List<WorkInfo>> {
        return mSavedWorkInfo
    }
}
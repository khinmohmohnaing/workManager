package com.example.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.workmanager.worker.ShowJokeWorker
import java.util.concurrent.TimeUnit

class BootBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.i("mvm","do work in broadcast")
            startWorker()
        }
    }
    private fun startWorker() {
        val periodicWork =
            OneTimeWorkRequest.Builder(ShowJokeWorker::class.java)
                .setInitialDelay(2, TimeUnit.SECONDS)
                .build()
        WorkManager.getInstance().enqueue(periodicWork)
    }
}
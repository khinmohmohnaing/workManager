package com.example.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class BootBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Toast.makeText(context, "do work",
                Toast.LENGTH_LONG).show();
            startWorker()
        }
    }
    private fun startWorker() {
        val requestJokeInfo =
            OneTimeWorkRequest.Builder(ShowJokeWorker::class.java)
                .setInitialDelay(30, TimeUnit.SECONDS)
                .addTag(Constants.TAG_OUTPUT)
                .build()
        WorkManager.getInstance().enqueue(requestJokeInfo)
    }
}
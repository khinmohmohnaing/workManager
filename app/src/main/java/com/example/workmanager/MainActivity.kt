package com.example.workmanager

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mViewModel = ViewModelProviders.of(this).get(ShowJokeViewModel::class.java)
        mViewModel.getOutputWorkInfo().observe(this, Observer {
            if (it == null || it.isEmpty()) {
                return@Observer
            }
            val workInfo = it.get(0)
            val finished = workInfo.getState().isFinished()
            if (!finished) {
            } else {
                val outputData = workInfo.getOutputData()
                val type = outputData.getString(Constants.TYPE)
                val setup =outputData.getString(Constants.SETUP)
                val punchline = outputData.getString(Constants.PUNCHLINE)
                btnShowJoke.visibility = View.GONE
                layoutShowJoke.visibility=View.VISIBLE
                Log.i("FinishTime", type+ setup + punchline)
                txtType.text=type.toString()
                txtSetup.text=setup.toString()
                txtPunchline.text=punchline.toString()
            }
        })
        btnShowJoke.setOnClickListener {
            mViewModel.showJoke()
            Toast.makeText(this,"wait 30 seconds ",Toast.LENGTH_LONG).show()
            btnShowJoke.visibility = View.GONE
        }
    }
}

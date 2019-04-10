package com.example.workmanager.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.work.*
import com.example.workmanager.Constants
import com.example.workmanager.R
import com.example.workmanager.viewModel.ShowJokeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isShowToast = true
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
                val setup = outputData.getString(Constants.SETUP)
                val punchline = outputData.getString(Constants.PUNCHLINE)
                Log.i("FinishTime", type + setup + punchline)
                if (type != null && setup != null && punchline != null) {
                    messageLyaout.visibility=View.GONE
                    layoutShowJoke.visibility = View.VISIBLE
                    txtType.text = type.toString()
                    txtSetup.text = setup.toString()
                    txtPunchline.text = punchline.toString()

                } else {
                    layoutShowJoke.visibility = View.GONE
                    messageLyaout.visibility=View.VISIBLE
                }
            }
        })
        btnShowJoke.setOnClickListener {
            mViewModel.showJoke()
            Toast.makeText(this, "wait 15 seconds ", Toast.LENGTH_LONG).show()
            btnShowJoke.visibility = View.GONE
        }
    }
}
package com.example.activitiesandfragments.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.activitiesandfragments.Constants
import com.example.activitiesandfragments.Constants.Keys.RESULT_KEY_SECOND_ACTIVITY
import com.example.activitiesandfragments.R

class BrowserActivity : AppCompatActivity() {

    companion object {
        var pastActivity: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "on Create")
    }

    override fun finish() {
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND,"Finish")
        val data = Intent()
        data.putExtra(RESULT_KEY_SECOND_ACTIVITY, "Swinging on a star.")
        setResult(RESULT_OK, data)
        super.finish()
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "On Destroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Resumed")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Attached to window")
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Started")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Stopped")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Paused")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(Constants.Tags.TAG_ACTIVITY_SECOND, "Detached from window")
    }

}
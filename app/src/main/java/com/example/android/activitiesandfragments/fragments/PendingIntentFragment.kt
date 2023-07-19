package com.example.android.activitiesandfragments.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.activitiesandfragments.Constants
import com.example.android.activitiesandfragments.views.BrowserActivity
import com.example.android.databinding.FragmentPendingIntentBinding

class PendingIntentFragment : Fragment() {

    private lateinit var binding: FragmentPendingIntentBinding
    private lateinit var alarmManager: AlarmManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        setOnClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPendingIntentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun setOnClickListeners() {
        binding.btnAlarmManager.setOnClickListener {
            val pendingIntent =
                createPendingIntent(Intent(requireContext(), BrowserActivity::class.java))
            setTimerFor(3, pendingIntent)
        }

        binding.btnNotificationManager.setOnClickListener {

        }
    }

    private fun setTimerFor(seconds: Int, pendingIntent: PendingIntent) {
        val timeInMillis = seconds * 1000 + System.currentTimeMillis()
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
    }

    private fun createPendingIntent(intent: Intent): PendingIntent {
        return PendingIntent.getActivity(
            requireContext(),
            Constants.Codes.REQUEST_CODE_BROWSABLE_ACTIVITY,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}


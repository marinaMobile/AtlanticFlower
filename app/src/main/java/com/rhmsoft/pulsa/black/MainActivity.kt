package com.rhmsoft.pulsa.black

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import com.appsflyer.AppsFlyerLib
import com.orhanobut.hawk.Hawk
import com.rhmsoft.pulsa.R
import com.rhmsoft.pulsa.black.CNST.DEV
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val prefs = getSharedPreferences("ActivityPREF", MODE_PRIVATE)
        if (prefs.getBoolean("activity_exec", false)) {
            Intent(this, Filter::class.java).also { startActivity(it) }
            finish()
        } else {
            val exec = prefs.edit()
            exec.putBoolean("activity_exec", true)
            exec.apply()
        }

        Log.d("DevChecker", isDevMode(this).toString())
        Hawk.put(DEV, isDevMode(this).toString())

        viewModel.deePP(this)
        Handler().postDelayed({
            toTestGrounds()
        }, 2000)

    }


    private fun toTestGrounds() {
        Intent(this, Filter::class.java)
            .also { startActivity(it) }
        finish()
    }

    }

    private fun isDevMode(context: Context): Boolean {
        return run {
            Settings.Secure.getInt(context.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0
        }
    }

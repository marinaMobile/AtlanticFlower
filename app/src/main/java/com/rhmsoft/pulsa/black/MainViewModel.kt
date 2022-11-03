package com.rhmsoft.pulsa.black

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.appsflyer.AppsFlyerConversionListener
import com.facebook.applinks.AppLinkData
import com.orhanobut.hawk.Hawk
import com.rhmsoft.pulsa.black.CNST.D1
import javax.inject.Inject

class MainViewModel   @Inject constructor(): ViewModel()  {

    fun deePP(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                val params = appLinkData.targetUri.host

                Log.d("D11PL", "$params")
//                val conjoined = TextUtils.join("/", params)
//                Log.d("FB_TEST:", conjoined)

                Hawk.put(D1, params.toString())
                
            }
            if (appLinkData == null) {
                Log.d("FB_ERR:", "Params = null")
            }
        }
    }
}
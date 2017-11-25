package com.earncash.makemoney.earnfreemoney.core.advertisements

import android.content.Context
import com.earncash.makemoney.earnfreemoney.core.analytics.Analytics
import com.earncash.makemoney.earnfreemoney.core.managers.PreferencesManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

class AdmobInterstitial(preferencesManager: PreferencesManager,
        context: Context) {

    private var intertitialAd: InterstitialAd? = null

    init {
            intertitialAd = InterstitialAd(context)
            intertitialAd?.adUnitId = "ca-app-pub-7065666432812754/9507522925"
            loadAd()
    }

    private fun loadAd() {
        intertitialAd?.loadAd(AdRequest.Builder().build())
    }

    fun show(notloaded: () -> Unit) {
        if (intertitialAd != null) {
            if (intertitialAd?.isLoaded!!) {
                intertitialAd?.show()
                Analytics.report(Analytics.INTERSTITIAL, Analytics.ADMOB, Analytics.OPEN)
                loadAd()
            } else {
                notloaded()
                loadAd()
            }
        } else {
            notloaded()
            loadAd()
        }
    }
}

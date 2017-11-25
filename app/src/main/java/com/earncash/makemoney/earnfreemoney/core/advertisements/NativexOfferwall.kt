package com.earncash.makemoney.earnfreemoney.core.advertisements

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.earncash.makemoney.earnfreemoney.AppTools
import com.earncash.makemoney.earnfreemoney.core.analytics.Analytics
import com.earncash.makemoney.earnfreemoney.core.managers.CoinsManager
import com.earncash.makemoney.earnfreemoney.core.managers.PreferencesManager
import com.nativex.monetization.MonetizationManager
import com.nativex.monetization.enums.NativeXAdPlacement
import com.nativex.monetization.listeners.SessionListener
import java.util.*

class NativexOfferwall(private var preferencesManager: PreferencesManager) {

    private var timer = Timer()
    private var coinView: TextView? = null
    lateinit var coinManager: CoinsManager
    private var appCompatActivity: AppCompatActivity? = null

    constructor(coinManager: CoinsManager, preferencesManager: PreferencesManager) : this (preferencesManager) {
        this.coinManager = coinManager
    }

    private fun retry() {
        var repeater = Repeater()
        timer.schedule(repeater, 2000)
    }

    fun onResume(activity: Activity) {
        startSessia(activity)
    }

    private fun startSessia(activity: Activity) {
        MonetizationManager.createSession(activity.applicationContext,
                preferencesManager.get(PreferencesManager.NATIVEX_KEY, ""),
                AppTools.uniqueId(activity), SessionListener { a, b, s ->
            if (!a) {
                retry()
            } else {
                try {
                    timer.cancel()
                } catch (ex: Exception) {}
            }
        })
        MonetizationManager.setRewardListener({ data ->
            val totalRewardAmount = data.rewards.sumBy { it.amount.toInt() }
            coinManager.addCoins(totalRewardAmount.toFloat() * 0.01f)
            Analytics.report(Analytics.OFFER, Analytics.NATIVEX, Analytics.REWARD)
            coinView!!.text = coinManager.getCoins().toString()
        })
        MonetizationManager.fetchAd(activity, NativeXAdPlacement.Store_Open, null)
    }

    fun show(activity: Activity, coinView: TextView): Boolean {

        this.coinView = coinView
        this.appCompatActivity = activity as AppCompatActivity

        if (MonetizationManager.isAdReady(NativeXAdPlacement.Store_Open)) {
            MonetizationManager.showReadyAd(activity, NativeXAdPlacement.Store_Open, null)
            Analytics.report(Analytics.OFFER, Analytics.NATIVEX, Analytics.OPEN)
            return true
        }
        return false
    }


    private inner class Repeater : TimerTask() {
        override fun run() {
            try {
                appCompatActivity!!.runOnUiThread {
                    try {
                        startSessia(appCompatActivity!!)
                    } catch (ex: Exception) {
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}

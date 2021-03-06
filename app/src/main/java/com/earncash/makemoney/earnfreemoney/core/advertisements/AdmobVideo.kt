package com.earncash.makemoney.earnfreemoney.core.advertisements

import android.content.Context
import android.widget.TextView
import com.earncash.makemoney.earnfreemoney.core.analytics.Analytics
import com.earncash.makemoney.earnfreemoney.core.managers.CoinsManager
import com.earncash.makemoney.earnfreemoney.core.managers.PreferencesManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.earncash.makemoney.earnfreemoney.screens.BaseActivity

class AdmobVideo(private var coinsManager: CoinsManager,
        private var preferencesManager: PreferencesManager,
        context: Context) {

    private var rewardedVideo: RewardedVideoAd? = null

    private lateinit var coinsText: TextView

    init {

            rewardedVideo = MobileAds.getRewardedVideoAdInstance(context)
            rewardedVideo?.rewardedVideoAdListener = object : RewardedVideoAdListener {
                override fun onRewardedVideoAdClosed() {}
                override fun onRewardedVideoAdLeftApplication() {}
                override fun onRewardedVideoAdLoaded() {}
                override fun onRewardedVideoAdOpened() {}
                override fun onRewarded(rewardItem: RewardItem?) {
                    if (!preferencesManager.get(PreferencesManager.ADDITIONAL_LIFE, false)) {
                        coinsManager.addCoins(rewardItem?.amount?.toFloat()!! * 0.01f)
                        coinsText.text = BaseActivity.format(coinsManager.getCoins())
                        Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.REWARD)
                    } else {
                        preferencesManager.put(PreferencesManager.ADDITIONAL_LIFE, false)
                        preferencesManager.put(PreferencesManager.TICKETS_LIFES, 1)
                        Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.GAME_BOOST)
                    }
                }
                override fun onRewardedVideoStarted() {}
                override fun onRewardedVideoAdFailedToLoad(p0: Int) {}
            }
            loadVideo()
    }

    private fun loadVideo() {
        rewardedVideo?.loadAd("ca-app-pub-7065666432812754/5919631707",
                AdRequest.Builder().build())
    }

    fun show(coinsView: TextView) : Boolean {
        coinsText = coinsView
        if (rewardedVideo != null) {
            if (rewardedVideo?.isLoaded!!) {
                rewardedVideo?.show()
                Analytics.report(Analytics.VIDEO, Analytics.ADMOB, Analytics.OPEN)
                return true
            }
        }
        return false
    }
}

package com.earncash.makemoney.earnfreemoney.core.advertisements

import android.content.Context
import android.widget.TextView
import com.earncash.makemoney.earnfreemoney.core.analytics.Analytics
import com.earncash.makemoney.earnfreemoney.core.managers.CoinsManager
import com.earncash.makemoney.earnfreemoney.screens.BaseActivity
import mo.offers.lib.core.managers.OffersManager

class HouseOffers(private var context: Context,
                  private var currency: String,
                  private var coinManager: CoinsManager) {

    private var coinsText: TextView? = null

    private var manager: OffersManager = OffersManager(context, {}, "com.makefreemoney.earnfreecash.paypalvisacash", currency)

    fun init() {
        manager.attachRewardListener { rw ->
            coinManager.addCoins(rw.toFloat() * 0.01f)
            coinsText?.text = BaseActivity.format(coinManager.getCoins())
            Analytics.report(Analytics.OFFER, Analytics.MOOFFERS, Analytics.REWARD)
        }
    }

    fun show(coinsText: TextView) {
        this.coinsText = coinsText
        manager.show()
        Analytics.report(Analytics.OFFER, Analytics.MOOFFERS, Analytics.OPEN)
    }
 }

package com.earncash.makemoney.earnfreemoney.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.earncash.makemoney.earnfreemoney.R
import com.earncash.makemoney.earnfreemoney.core.MyApplication
import com.earncash.makemoney.earnfreemoney.core.managers.PreferencesManager
import kotlinx.android.synthetic.main.toolbar.*

class OffersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        bindCoinView()
        bind()

        preferencesManager.put(PreferencesManager.ADDITIONAL_LIFE, false)

        toolbarText.text = "Earn Money"

        initBanner()
    }

    @OnClick(R.id.addCoinsText)
    fun back(view: View) {
        startActivity(Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun onResume() {
        super.onResume()
        (application as MyApplication).advertisement?.fyber?.onResume(this)
    }

    @OnClick(R.id.offer_1,
            R.id.offer_2,
            R.id.offer_3,
            R.id.offer_4,
            R.id.offer_5,
            R.id.offer_6,
            R.id.offer_7,
            R.id.offer_8)
    fun offersClick(view: View) {
        animationsManager.btnClick(view, {}, {
            if (view.id == R.id.offer_1) {
                if ((application as MyApplication).advertisement?.house != null) {
                    (application as MyApplication).advertisement?.house?.show(coinsView)
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_2) {
                if ((application as MyApplication).advertisement?.fyber != null) {
                    (application as MyApplication).advertisement?.fyber?.show(this, coinsView)
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_3) {
                if ((application as MyApplication).advertisement?.offertoro != null) {
                    (application as MyApplication).advertisement?.offertoro?.show(this, coinsView)
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_4) {
                if ((application as MyApplication).advertisement?.unity != null) {
                    if (!(application as MyApplication).advertisement?.unity?.showVideo(this, coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_6) {
                if ((application as MyApplication).advertisement?.vungle != null) {
                    if (!(application as MyApplication).advertisement?.vungle?.showVideo(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_7) {
                if ((application as MyApplication).advertisement?.adcolony != null) {
                    if (!(application as MyApplication).advertisement?.adcolony?.showVideo(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.offer_5) {
                if ((application as MyApplication).advertisement?.admob != null) {
                    if (!(application as MyApplication).advertisement?.admob?.show(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show {  }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show {  }
                    })
                }
            } else if (view.id == R.id.offer_8) {
                if ((application as MyApplication).advertisement?.fyberVideo != null) {
                    if (!(application as MyApplication).advertisement?.fyberVideo?.show(this, coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            }
        })
    }
}

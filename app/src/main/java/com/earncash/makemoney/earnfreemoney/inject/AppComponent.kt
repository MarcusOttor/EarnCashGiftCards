package com.earncash.makemoney.earnfreemoney.inject

import com.earncash.makemoney.earnfreemoney.core.MyApplication
import com.earncash.makemoney.earnfreemoney.core.services.ClaimService
import com.earncash.makemoney.earnfreemoney.screens.BaseActivity
import com.earncash.makemoney.earnfreemoney.screens.dialogs.*
import dagger.Component

@Component(modules = arrayOf(AppModule::class, MainModule::class))
interface AppComponent {

    fun inject(screen: BaseActivity)
    fun inject(app: MyApplication)
    fun inject(dialog: LoginDialog)
    fun inject(dialog: SignupDialog)
    fun inject(dialog: PromocodeDialog)
    fun inject(dialog: RedeemDialog)
    fun inject(service: ClaimService)
    fun inject(dialog: HistoryDialog)
}

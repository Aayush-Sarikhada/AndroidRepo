package com.example.android.main.model

import androidx.appcompat.app.AppCompatActivity
import com.example.android.activitiesandfragments.views.IntentsActivity
import com.example.android.nftbazaar.views.AuthenticationActivity
import com.example.android.practical3.views.HomeWithNavGraphActivity
import com.example.android.recyclerViewPractice.views.ArithmeticRVActivity
import com.example.android.stockexchangeui.StockExchangeActivity
import com.example.android.webservices.views.SignUpActivity

data class ScreenType<out T>(
    val id: Int,
    val name: String,
    val startActivity: Class<out T>
) {
    companion object {
        fun getActivitiesDataList(): List<ScreenType<AppCompatActivity>> {
            return listOf(
                ScreenType(0, name = "Stock exchange UI", StockExchangeActivity::class.java),
                ScreenType(1, name = "NFT Bazaar", AuthenticationActivity::class.java),
                ScreenType(2, name = "Arithmetic Recycler View", ArithmeticRVActivity::class.java),
                ScreenType(
                    3,
                    name = "Intents with Activities and fragments",
                    IntentsActivity::class.java
                ),
                ScreenType(4, name = "Practical chapter 3", HomeWithNavGraphActivity::class.java),
                ScreenType(5, name = "Web Services", SignUpActivity::class.java)
            )
        }
    }
}

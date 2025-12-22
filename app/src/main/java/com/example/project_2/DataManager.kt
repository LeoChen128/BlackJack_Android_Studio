package com.example.project_2

import android.content.Context
import android.content.SharedPreferences

object DataManager {
    private const val PREFS_NAME = "BlackjackPrefs"
    private const val MONEY = "user_money"
    private const val DEFAULT_MONEY = 1000

    private var userMoney: Int = DEFAULT_MONEY
    private var currentWager: Int = 0


    fun initialize(context: Context) {
        val prefs = getPreferences(context)
        userMoney = prefs.getInt(MONEY, DEFAULT_MONEY)
    }

    fun getUserMoney(): Int = userMoney

    fun setUserMoney(amount: Int, context: Context) {
        userMoney = amount
        saveToPreferences(context)
    }

    fun addMoney(amount: Int, context: Context) {
        userMoney += amount
        saveToPreferences(context)
    }

    fun subtractMoney(amount: Int, context: Context) {
        userMoney -= amount
        saveToPreferences(context)
    }

    fun getCurrentWager(): Int = currentWager

    fun setCurrentWager(amount: Int) {
        currentWager = amount
    }

    fun canAffordWager(amount: Int): Boolean = amount <= userMoney

    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveToPreferences(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().putInt(MONEY, userMoney).apply()
    }

    fun resetMoney(context: Context) {
        setUserMoney(DEFAULT_MONEY, context)
    }
}
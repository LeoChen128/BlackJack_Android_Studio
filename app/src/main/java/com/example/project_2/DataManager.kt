package com.example.project_2

import android.content.Context
import android.content.SharedPreferences

/**
 * Singleton object for app-wide data management.
 * Handles user money persistence and current wager state.
 */
object DataManager {
    private const val PREFS_NAME = "BlackjackPrefs"
    private const val KEY_USER_MONEY = "user_money"
    private const val DEFAULT_MONEY = 1000

    private var userMoney: Int = DEFAULT_MONEY
    private var currentWager: Int = 0

    /**
     * Initialize DataManager by loading persisted data from SharedPreferences.
     * Should be called in MainActivity.onCreate()
     */
    fun initialize(context: Context) {
        val prefs = getPreferences(context)
        userMoney = prefs.getInt(KEY_USER_MONEY, DEFAULT_MONEY)
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

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    private fun saveToPreferences(context: Context) {
        val prefs = getPreferences(context)
        prefs.edit().putInt(KEY_USER_MONEY, userMoney).apply()
    }

    fun resetMoney(context: Context) {
        setUserMoney(DEFAULT_MONEY, context)
    }
}
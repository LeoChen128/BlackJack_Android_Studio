package com.example.project_2

import android.content.Context
import android.content.SharedPreferences

object DataManager {
    private const val prefsname = "BlackjackPrefs"
    private const val keyusermoney = "user_money"
    private const val defaultmoney = 1000

    private var usermoney: Int = defaultmoney
    private var currentwager: Int = 0

    fun initialize(context: Context) {
        val prefs = context.getSharedPreferences(prefsname, Context.MODE_PRIVATE)
        usermoney = prefs.getInt(keyusermoney, defaultmoney)
    }

    fun getusermoney(): Int {
        return usermoney
    }

    fun setusermoney(amount: Int, context: Context) {
        usermoney = amount
        val prefs = context.getSharedPreferences(prefsname, Context.MODE_PRIVATE)
        prefs.edit().putInt(keyusermoney, usermoney).apply()
    }

    fun addmoney(amount: Int, context: Context) {
        usermoney = usermoney + amount
        val prefs = context.getSharedPreferences(prefsname, Context.MODE_PRIVATE)
        prefs.edit().putInt(keyusermoney, usermoney).apply()
    }

    fun subtractmoney(amount: Int, context: Context) {
        usermoney = usermoney - amount
        val prefs = context.getSharedPreferences(prefsname, Context.MODE_PRIVATE)
        prefs.edit().putInt(keyusermoney, usermoney).apply()
    }

    fun getcurrentwager(): Int {
        return currentwager
    }

    fun setcurrentwager(amount: Int) {
        currentwager = amount
    }

    fun canaffordwager(amount: Int): Boolean {
        if (amount <= usermoney) {
            return true
        } else {
            return false
        }
    }

    fun resetmoney(context: Context) {
        usermoney = defaultmoney
        val prefs = context.getSharedPreferences(prefsname, Context.MODE_PRIVATE)
        prefs.edit().putInt(keyusermoney, usermoney).apply()
    }
}
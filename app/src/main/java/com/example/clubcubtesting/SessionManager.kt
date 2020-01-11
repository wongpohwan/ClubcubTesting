package com.example.clubcubtesting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

// testing only

public class SessionManager {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con:Context
    var PRIVATE_MODE: Int = 0

    constructor(con:Context) {
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {
        val PREF_NAME: String = "RememberMe"
        val IS_LOGIN: String = "isLoggedIn"
        val KEY_EMAIL: String = "email"
        val KEY_PASSWORD: String = "password"

    }

    fun createLoginSession(email:String, password:String) {
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_EMAIL,email)
        editor.putString(KEY_PASSWORD,password)
        editor.commit()
    }

    fun checkLogin() {
        if(!this.isLoggedIn()) {
            var intent:Intent = Intent(con, HomepageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            con.startActivity(intent)
        }
    }

    fun getUserDetails():HashMap<String,String> {
        var user: Map<String,String> = HashMap<String,String>()
        (user as HashMap).put(KEY_EMAIL, pref.getString(KEY_EMAIL,null)!!)
        (user as HashMap).put(KEY_PASSWORD, pref.getString(KEY_PASSWORD,null)!!)
        return user
    }

    fun isLoggedIn():Boolean {
        return pref.getBoolean(IS_LOGIN,false)
    }
}
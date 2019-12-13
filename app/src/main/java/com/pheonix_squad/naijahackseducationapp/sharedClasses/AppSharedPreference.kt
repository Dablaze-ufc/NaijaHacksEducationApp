package com.pheonix_squad.naijahackseducationapp.sharedClasses

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.gson.Gson
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppConstants.APP_SHARED_PREFERENCE_KEY
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppConstants.PHONE_AUTH_CREDENTIAL_KEY
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppConstants.TOKEN_KEY
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppConstants.VERIFICATION_ID_KEY

/**
 * Created by SegunFrancis
 */
class AppSharedPreference constructor(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(APP_SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)


    fun setCredential(credential: PhoneAuthCredential) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(credential)
        editor.putString(PHONE_AUTH_CREDENTIAL_KEY, json)
        editor.apply()
    }

    fun getCredential(): PhoneAuthCredential {
        val gson = Gson()
        val json = sharedPreferences.getString(PHONE_AUTH_CREDENTIAL_KEY, "")
        return gson.fromJson(json, PhoneAuthCredential::class.java)
    }

    fun setVerificationId(verificationId: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(VERIFICATION_ID_KEY, verificationId)
        editor.apply()
    }

    fun getVerificationId(): String? {
        return sharedPreferences.getString(VERIFICATION_ID_KEY, "")
    }

    fun setAuthToken(token: PhoneAuthProvider.ForceResendingToken) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(token)
        editor.putString(TOKEN_KEY, json)
        editor.apply()
    }

    fun getAuthToken() : PhoneAuthProvider.ForceResendingToken {
        val gson = Gson()
        val json = sharedPreferences.getString(TOKEN_KEY, "")
        return gson.fromJson(json, PhoneAuthProvider.ForceResendingToken::class.java)
    }
}
package com.abnuj.acharybaalkrishnagharelunuskhe

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "user")

class UserIntro : AppCompatActivity() {
    var userbtn: Button? = null
    var awesomeEditText: EditText? = null
    var sharedPreferences: SharedPreferences? = null
    var Userintroname: String? = null
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_intro)
    }
}
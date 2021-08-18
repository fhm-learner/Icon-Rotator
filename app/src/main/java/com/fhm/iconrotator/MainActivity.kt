package com.fhm.iconrotator

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    var lang:String = "en";
    private var myLocale: Locale? = null
    private var txtStateName:TextView? = null;
    private var txtDistrictName:TextView? = null;

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtStateName = findViewById<TextView>(R.id.txtStateName)
        txtDistrictName = findViewById<TextView>(R.id.txtDistrictName)
        val btnEnglish = findViewById<Button>(R.id.btnEnglish)
        val btnHindi = findViewById<Button>(R.id.btnHindi)

        btnEnglish ?.setOnClickListener {
            lang = "en"
            changeLocale(lang)
        }

        btnHindi ?.setOnClickListener {
            lang = "hi"
            changeLocale(lang)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun changeLocale(language : String){
        if (lang.equals("", ignoreCase = true)) return

        myLocale = Locale(lang) //Set Selected Locale
        Locale.setDefault(myLocale) //set new locale as default
        val config = Configuration() //get Configuration
        config.setLocale(myLocale) //set config locale as selected locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics) //Update the config
        updateTexts()
    }

    fun updateTexts(){
        txtStateName?.setText(R.string.state_name)
        txtDistrictName?.setText(R.string.district_name)
    }

}
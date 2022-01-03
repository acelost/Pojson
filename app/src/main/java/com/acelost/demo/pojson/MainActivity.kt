package com.acelost.demo.pojson

import android.app.Activity
import android.os.Bundle
import com.acelost.pojson.android.json.Pojson2AndroidJSON
import com.acelost.pojson.collection.Pojson2KotlinCollection
import com.acelost.pojson.gson.Pojson2Gson

class MainActivity : Activity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Pojson2Gson.create()
        Pojson2AndroidJSON.create()
        Pojson2KotlinCollection.create()
    }
}
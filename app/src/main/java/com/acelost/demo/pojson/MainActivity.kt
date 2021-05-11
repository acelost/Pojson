package com.acelost.demo.pojson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.acelost.pojson.android.json.Pojson2NativeJSON
import com.acelost.pojson.collection.Pojson2NativeCollection
import com.acelost.pojson.gson.Pojson2Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Pojson2Gson.create()
        Pojson2NativeJSON.create()
        Pojson2NativeCollection.create()
    }
}
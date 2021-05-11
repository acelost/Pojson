package com.acelost.pojson.gson

import com.acelost.pojson.Pojson
import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject

class Pojson2Gson {

    companion object {

        fun create(): Pojson<JsonObject, JsonArray> {
            return Pojson(
                objectFactory = GsonObjectFactory(),
                objectAdapter = GsonObjectAdapter(),
                arrayFactory = GsonArrayFactory(),
                arrayAdapter = GsonArrayAdapter()
            )
        }
    }
}

private class GsonObjectFactory : JsonObjectFactory<JsonObject> {

    override fun newInstance(): JsonObject {
        return JsonObject()
    }
}

private class GsonArrayFactory : JsonArrayFactory<JsonArray> {

    override fun newInstance(): JsonArray {
        return JsonArray()
    }
}

private class GsonObjectAdapter : JsonObjectAdapter<JsonObject, JsonArray> {

    override fun addStringProperty(target: JsonObject, key: String, value: String) {
        target.addProperty(key, value)
    }

    override fun addNumberProperty(target: JsonObject, key: String, value: Number) {
        target.addProperty(key, value)
    }

    override fun addBooleanProperty(target: JsonObject, key: String, value: Boolean) {
        target.addProperty(key, value)
    }

    override fun addObjectProperty(target: JsonObject, key: String, value: JsonObject) {
        target.add(key, value)
    }

    override fun addArrayProperty(target: JsonObject, key: String, value: JsonArray) {
        target.add(key, value)
    }

    override fun addNullProperty(target: JsonObject, key: String) {
        target.add(key, JsonNull.INSTANCE)
    }
}

private class GsonArrayAdapter : JsonArrayAdapter<JsonObject, JsonArray> {

    override fun addStringElement(target: JsonArray, value: String) {
        target.add(value)
    }

    override fun addNumberElement(target: JsonArray, value: Number) {
        target.add(value)
    }

    override fun addBooleanElement(target: JsonArray, value: Boolean) {
        target.add(value)
    }

    override fun addObjectElement(target: JsonArray, value: JsonObject) {
        target.add(value)
    }

    override fun addArrayElement(target: JsonArray, value: JsonArray) {
        target.add(value)
    }

    override fun addNullElement(target: JsonArray) {
        target.add(JsonNull.INSTANCE)
    }
}
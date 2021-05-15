package com.acelost.pojson.android.json

import com.acelost.pojson.Pojson
import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import org.json.JSONArray
import org.json.JSONObject

class Pojson2NativeJSON(
    objectFactory: JsonObjectFactory<JSONObject>,
    objectAdapter: JsonObjectAdapter<JSONObject, JSONArray>,
    arrayFactory: JsonArrayFactory<JSONArray>,
    arrayAdapter: JsonArrayAdapter<JSONObject, JSONArray>
) : Pojson<JSONObject, JSONArray>(
    objectFactory = objectFactory,
    objectAdapter = objectAdapter,
    arrayFactory = arrayFactory,
    arrayAdapter = arrayAdapter
) {

    companion object {

        fun create(): Pojson2NativeJSON {
            return Pojson2NativeJSON(
                objectFactory = NativeJSONObjectFactory(),
                objectAdapter = NativeJSONObjectAdapter(),
                arrayFactory = NativeJSONArrayFactory(),
                arrayAdapter = NativeJSONArrayAdapter()
            )
        }
    }
}

private class NativeJSONObjectFactory : JsonObjectFactory<JSONObject> {

    override fun newInstance(): JSONObject {
        return JSONObject()
    }
}

private class NativeJSONArrayFactory : JsonArrayFactory<JSONArray> {

    override fun newInstance(): JSONArray {
        return JSONArray()
    }
}

private class NativeJSONObjectAdapter : JsonObjectAdapter<JSONObject, JSONArray> {

    override fun addStringProperty(target: JSONObject, key: String, value: String) {
        target.put(key, value)
    }

    override fun addNumberProperty(target: JSONObject, key: String, value: Number) {
        target.put(key, value)
    }

    override fun addBooleanProperty(target: JSONObject, key: String, value: Boolean) {
        target.put(key, value)
    }

    override fun addObjectProperty(target: JSONObject, key: String, value: JSONObject) {
        target.put(key, value)
    }

    override fun addArrayProperty(target: JSONObject, key: String, value: JSONArray) {
        target.put(key, value)
    }

    override fun addNullProperty(target: JSONObject, key: String) {
        target.put(key, null)
    }
}

private class NativeJSONArrayAdapter : JsonArrayAdapter<JSONObject, JSONArray> {

    override fun addStringElement(target: JSONArray, value: String) {
        target.put(value)
    }

    override fun addNumberElement(target: JSONArray, value: Number) {
        target.put(value)
    }

    override fun addBooleanElement(target: JSONArray, value: Boolean) {
        target.put(value)
    }

    override fun addObjectElement(target: JSONArray, value: JSONObject) {
        target.put(value)
    }

    override fun addArrayElement(target: JSONArray, value: JSONArray) {
        target.put(value)
    }

    override fun addNullElement(target: JSONArray) {
        target.put(null)
    }
}
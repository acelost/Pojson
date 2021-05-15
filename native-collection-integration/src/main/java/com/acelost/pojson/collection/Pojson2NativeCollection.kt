package com.acelost.pojson.collection

import com.acelost.pojson.Pojson
import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import java.util.*
import kotlin.collections.LinkedHashMap

typealias NativeCollectionObject = MutableMap<String, Any?>

typealias NativeCollectionArray = MutableList<Any?>

class Pojson2NativeCollection(
    objectFactory: JsonObjectFactory<NativeCollectionObject>,
    objectAdapter: JsonObjectAdapter<NativeCollectionObject, NativeCollectionArray>,
    arrayFactory: JsonArrayFactory<NativeCollectionArray>,
    arrayAdapter: JsonArrayAdapter<NativeCollectionObject, NativeCollectionArray>
) : Pojson<NativeCollectionObject, NativeCollectionArray>(
    objectFactory = objectFactory,
    objectAdapter = objectAdapter,
    arrayFactory = arrayFactory,
    arrayAdapter = arrayAdapter
) {

    companion object {

        fun create(): Pojson2NativeCollection {
            return Pojson2NativeCollection(
                objectFactory = NativeCollectionObjectFactory(),
                objectAdapter = NativeCollectionObjectAdapter(),
                arrayFactory = NativeCollectionArrayFactory(),
                arrayAdapter = NativeCollectionArrayAdapter()
            )
        }
    }
}

private class NativeCollectionObjectFactory : JsonObjectFactory<NativeCollectionObject> {

    override fun newInstance(): NativeCollectionObject {
        return LinkedHashMap()
    }
}

private class NativeCollectionArrayFactory : JsonArrayFactory<NativeCollectionArray> {

    override fun newInstance(): NativeCollectionArray {
        return LinkedList()
    }
}

private class NativeCollectionObjectAdapter : JsonObjectAdapter<NativeCollectionObject, NativeCollectionArray> {

    override fun addStringProperty(target: NativeCollectionObject, key: String, value: String) {
        target[key] = value
    }

    override fun addNumberProperty(target: NativeCollectionObject, key: String, value: Number) {
        target[key] = value
    }

    override fun addBooleanProperty(target: NativeCollectionObject, key: String, value: Boolean) {
        target[key] = value
    }

    override fun addObjectProperty(
        target: NativeCollectionObject,
        key: String,
        value: NativeCollectionObject
    ) {
        target[key] = value
    }

    override fun addArrayProperty(
        target: NativeCollectionObject,
        key: String,
        value: NativeCollectionArray
    ) {
        target[key] = value
    }

    override fun addNullProperty(target: NativeCollectionObject, key: String) {
        target[key] = null
    }
}

private class NativeCollectionArrayAdapter : JsonArrayAdapter<NativeCollectionObject, NativeCollectionArray> {

    override fun addStringElement(target: NativeCollectionArray, value: String) {
        target.add(value)
    }

    override fun addNumberElement(target: NativeCollectionArray, value: Number) {
        target.add(value)
    }

    override fun addBooleanElement(target: NativeCollectionArray, value: Boolean) {
        target.add(value)
    }

    override fun addObjectElement(target: NativeCollectionArray, value: NativeCollectionObject) {
        target.add(value)
    }

    override fun addArrayElement(target: NativeCollectionArray, value: NativeCollectionArray) {
        target.add(value)
    }

    override fun addNullElement(target: NativeCollectionArray) {
        target.add(null)
    }
}
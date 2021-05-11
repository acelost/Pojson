package com.acelost.pojson.collection

import com.acelost.pojson.Pojson
import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import java.util.*
import kotlin.collections.LinkedHashMap

typealias NativeStructureObject = MutableMap<String, Any?>

typealias NativeStructureArray = MutableList<Any?>

class Pojson2NativeCollection {

    companion object {

        fun create(): Pojson<NativeStructureObject, NativeStructureArray> {
            return Pojson(
                objectFactory = NativeCollectionObjectFactory(),
                objectAdapter = NativeCollectionObjectAdapter(),
                arrayFactory = NativeCollectionArrayFactory(),
                arrayAdapter = NativeCollectionArrayAdapter()
            )
        }
    }
}

private class NativeCollectionObjectFactory : JsonObjectFactory<NativeStructureObject> {

    override fun newInstance(): NativeStructureObject {
        return LinkedHashMap()
    }
}

private class NativeCollectionArrayFactory : JsonArrayFactory<NativeStructureArray> {

    override fun newInstance(): NativeStructureArray {
        return LinkedList()
    }
}

private class NativeCollectionObjectAdapter : JsonObjectAdapter<NativeStructureObject, NativeStructureArray> {

    override fun addStringProperty(target: NativeStructureObject, key: String, value: String) {
        target[key] = value
    }

    override fun addNumberProperty(target: NativeStructureObject, key: String, value: Number) {
        target[key] = value
    }

    override fun addBooleanProperty(target: NativeStructureObject, key: String, value: Boolean) {
        target[key] = value
    }

    override fun addObjectProperty(
        target: NativeStructureObject,
        key: String,
        value: NativeStructureObject
    ) {
        target[key] = value
    }

    override fun addArrayProperty(
        target: NativeStructureObject,
        key: String,
        value: NativeStructureArray
    ) {
        target[key] = value
    }

    override fun addNullProperty(target: NativeStructureObject, key: String) {
        target[key] = null
    }
}

private class NativeCollectionArrayAdapter : JsonArrayAdapter<NativeStructureObject, NativeStructureArray> {

    override fun addStringElement(target: NativeStructureArray, value: String) {
        target.add(value)
    }

    override fun addNumberElement(target: NativeStructureArray, value: Number) {
        target.add(value)
    }

    override fun addBooleanElement(target: NativeStructureArray, value: Boolean) {
        target.add(value)
    }

    override fun addObjectElement(target: NativeStructureArray, value: NativeStructureObject) {
        target.add(value)
    }

    override fun addArrayElement(target: NativeStructureArray, value: NativeStructureArray) {
        target.add(value)
    }

    override fun addNullElement(target: NativeStructureArray) {
        target.add(null)
    }
}
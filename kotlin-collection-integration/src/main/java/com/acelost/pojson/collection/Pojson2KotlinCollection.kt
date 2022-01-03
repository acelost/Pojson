package com.acelost.pojson.collection

import com.acelost.pojson.Pojson
import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import java.util.*
import kotlin.collections.LinkedHashMap

typealias KotlinCollectionObject = MutableMap<String, Any?>

typealias KotlinCollectionArray = MutableList<Any?>

class Pojson2KotlinCollection(
    objectFactory: JsonObjectFactory<KotlinCollectionObject>,
    objectAdapter: JsonObjectAdapter<KotlinCollectionObject, KotlinCollectionArray>,
    arrayFactory: JsonArrayFactory<KotlinCollectionArray>,
    arrayAdapter: JsonArrayAdapter<KotlinCollectionObject, KotlinCollectionArray>
) : Pojson<KotlinCollectionObject, KotlinCollectionArray>(
    objectFactory = objectFactory,
    objectAdapter = objectAdapter,
    arrayFactory = arrayFactory,
    arrayAdapter = arrayAdapter
) {

    companion object {

        fun create(): Pojson2KotlinCollection {
            return Pojson2KotlinCollection(
                objectFactory = KotlinCollectionObjectFactory(),
                objectAdapter = KotlinCollectionObjectAdapter(),
                arrayFactory = KotlinCollectionArrayFactory(),
                arrayAdapter = KotlinCollectionArrayAdapter()
            )
        }
    }
}

private class KotlinCollectionObjectFactory : JsonObjectFactory<KotlinCollectionObject> {

    override fun newInstance(): KotlinCollectionObject {
        return LinkedHashMap()
    }
}

private class KotlinCollectionArrayFactory : JsonArrayFactory<KotlinCollectionArray> {

    override fun newInstance(): KotlinCollectionArray {
        return LinkedList()
    }
}

private class KotlinCollectionObjectAdapter : JsonObjectAdapter<KotlinCollectionObject, KotlinCollectionArray> {

    override fun addStringProperty(target: KotlinCollectionObject, key: String, value: String) {
        target[key] = value
    }

    override fun addNumberProperty(target: KotlinCollectionObject, key: String, value: Number) {
        target[key] = value
    }

    override fun addBooleanProperty(target: KotlinCollectionObject, key: String, value: Boolean) {
        target[key] = value
    }

    override fun addObjectProperty(
        target: KotlinCollectionObject,
        key: String,
        value: KotlinCollectionObject
    ) {
        target[key] = value
    }

    override fun addArrayProperty(
        target: KotlinCollectionObject,
        key: String,
        value: KotlinCollectionArray
    ) {
        target[key] = value
    }

    override fun addNullProperty(target: KotlinCollectionObject, key: String) {
        target[key] = null
    }
}

private class KotlinCollectionArrayAdapter : JsonArrayAdapter<KotlinCollectionObject, KotlinCollectionArray> {

    override fun addStringElement(target: KotlinCollectionArray, value: String) {
        target.add(value)
    }

    override fun addNumberElement(target: KotlinCollectionArray, value: Number) {
        target.add(value)
    }

    override fun addBooleanElement(target: KotlinCollectionArray, value: Boolean) {
        target.add(value)
    }

    override fun addObjectElement(target: KotlinCollectionArray, value: KotlinCollectionObject) {
        target.add(value)
    }

    override fun addArrayElement(target: KotlinCollectionArray, value: KotlinCollectionArray) {
        target.add(value)
    }

    override fun addNullElement(target: KotlinCollectionArray) {
        target.add(null)
    }
}
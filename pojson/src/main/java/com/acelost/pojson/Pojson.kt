package com.acelost.pojson

import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype

class Pojson<ObjectType, ArrayType>(
    private val objectFactory: JsonObjectFactory<ObjectType>,
    private val objectAdapter: JsonObjectAdapter<ObjectType, ArrayType>,
    private val arrayFactory: JsonArrayFactory<ArrayType>,
    private val arrayAdapter: JsonArrayAdapter<ObjectType, ArrayType>
) {

    fun render(jsonObjectPrototype: JsonObjectPrototype): ObjectType {
        val context = PojsonContext(
            objectFactory = objectFactory,
            objectAdapter = objectAdapter,
            arrayFactory = arrayFactory,
            arrayAdapter = arrayAdapter
        )
        val objectWrapper = context.newObject(jsonObjectPrototype.notation)
        return requireNotNull(objectWrapper.value)
    }

    fun render(jsonArrayPrototype: JsonArrayPrototype): ArrayType {
        val context = PojsonContext(
            objectFactory = objectFactory,
            objectAdapter = objectAdapter,
            arrayFactory = arrayFactory,
            arrayAdapter = arrayAdapter
        )
        val arrayWrapper = context.newArray(jsonArrayPrototype.notation)
        return requireNotNull(arrayWrapper.value)
    }
}


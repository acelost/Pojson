package com.acelost.pojson

import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import com.acelost.pojson.syntax.interpreter.JsonArrayNotationInterpreter
import com.acelost.pojson.syntax.interpreter.JsonObjectNotationInterpreter

class PojsonContext<ObjType, ArrType>(
    @PublishedApi
    @JvmField
    internal val objectFactory: JsonObjectFactory<ObjType>,
        @PublishedApi
    @JvmField
    internal val objectAdapter: JsonObjectAdapter<ObjType, ArrType>,
        @PublishedApi
    @JvmField
    internal val arrayFactory: JsonArrayFactory<ArrType>,
        @PublishedApi
    @JvmField
    internal val arrayAdapter: JsonArrayAdapter<ObjType, ArrType>
) {

    @PublishedApi
    @JvmField
    internal val objectNotationInterpreter = JsonObjectNotationInterpreter(this)

    @PublishedApi
    @JvmField
    internal val arrayNotationInterpreter = JsonArrayNotationInterpreter(this)

    @PublishedApi
    @JvmField
    internal var objectCursor: ObjType? = null

    @PublishedApi
    @JvmField
    internal var arrayCursor: ArrType? = null

    @PublishedApi
    @JvmField
    internal val objectWrapper = JsonObjectWrapper<ObjType>()

    @PublishedApi
    @JvmField
    internal val arrayWrapper = JsonArrayWrapper<ArrType>()

    @PublishedApi
    @JvmField
    internal val nullablePrimitiveWrapper = JsonPrimitiveWrapper()

    inline fun newObject(notation: JsonObjectNotation): JsonObjectWrapper<ObjType> {
        val prevObject = objectCursor
        val newObject = objectFactory.newInstance()
        objectCursor = newObject
        notation.invoke(objectNotationInterpreter)
        objectCursor = prevObject
        return objectWrapper.apply { value = newObject }
    }

    inline fun updateObject(notation: JsonObjectNotation) {
        notation.invoke(objectNotationInterpreter)
    }

    inline fun updateObject(update: JsonObjectAdapter<ObjType, ArrType>.(ObjType) -> Unit) {
        update.invoke(objectAdapter, requireNotNull(objectCursor))
    }

    inline fun newArray(notation: JsonArrayNotation): JsonArrayWrapper<ArrType> {
        val prevArray = arrayCursor
        val newArray = arrayFactory.newInstance()
        arrayCursor = newArray
        notation.invoke(arrayNotationInterpreter)
        arrayCursor = prevArray
        return arrayWrapper.apply { value = newArray }
    }

    inline fun updateArray(notation: JsonArrayNotation) {
        notation.invoke(arrayNotationInterpreter)
    }

    inline fun updateArray(update: JsonArrayAdapter<ObjType, ArrType>.(ArrType) -> Unit) {
        update.invoke(arrayAdapter, requireNotNull(arrayCursor))
    }

    fun wrapNullable(value: Any?): JsonPrimitiveWrapper {
        return nullablePrimitiveWrapper.apply { this.value = value }
    }

    fun nullPrimitive(): JsonPrimitiveWrapper {
        return nullablePrimitiveWrapper.apply { this.value = null }
    }

    fun nullObject(): JsonObjectWrapper<ObjType> {
        return objectWrapper.apply { this.value = null }
    }
}
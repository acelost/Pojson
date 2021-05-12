package com.acelost.pojson

import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
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
    internal val genericObjectTransfer = GenericObjectTransfer<ObjType>()

    @PublishedApi
    @JvmField
    internal val genericArrayTransfer = GenericArrayTransfer<ArrType>()

    @PublishedApi
    @JvmField
    internal val nullableStringTransfer = NullableStringTransfer()

    @PublishedApi
    @JvmField
    internal val nullableNumberTransfer = NullableNumberTransfer()

    @PublishedApi
    @JvmField
    internal val nullableBooleanTransfer = NullableBooleanTransfer()

    @PublishedApi
    @JvmField
    internal val nullableObjectTransfer = NullableObjectTransfer()

    @PublishedApi
    @JvmField
    internal val nullableArrayTransfer = NullableArrayTransfer()

    inline fun newObject(notation: JsonObjectNotation): GenericObjectTransfer<ObjType> {
        val prevObject = objectCursor
        val newObject = objectFactory.newInstance()
        objectCursor = newObject
        notation.invoke(objectNotationInterpreter)
        objectCursor = prevObject
        return genericObjectTransfer.apply { value = newObject }
    }

    inline fun updateObject(notation: JsonObjectNotation) {
        notation.invoke(objectNotationInterpreter)
    }

    inline fun updateObject(update: JsonObjectAdapter<ObjType, ArrType>.(ObjType) -> Unit) {
        update.invoke(objectAdapter, requireNotNull(objectCursor))
    }

    inline fun newArray(notation: JsonArrayNotation): GenericArrayTransfer<ArrType> {
        val prevArray = arrayCursor
        val newArray = arrayFactory.newInstance()
        arrayCursor = newArray
        notation.invoke(arrayNotationInterpreter)
        arrayCursor = prevArray
        return genericArrayTransfer.apply { value = newArray }
    }

    inline fun updateArray(notation: JsonArrayNotation) {
        notation.invoke(arrayNotationInterpreter)
    }

    inline fun updateArray(update: JsonArrayAdapter<ObjType, ArrType>.(ArrType) -> Unit) {
        update.invoke(arrayAdapter, requireNotNull(arrayCursor))
    }

    fun wrapNullable(value: String?): NullableStringTransfer {
        return nullableStringTransfer.apply { this.value = value }
    }

    fun wrapNullable(value: Number?): NullableNumberTransfer {
        return nullableNumberTransfer.apply { this.value = value }
    }

    fun wrapNullable(value: Boolean?): NullableBooleanTransfer {
        return nullableBooleanTransfer.apply { this.value = value }
    }

    fun wrapNullable(value: JsonObjectPrototype?): NullableObjectTransfer {
        return nullableObjectTransfer.apply { this.value = value }
    }

    fun wrapNullable(value: JsonArrayPrototype?): NullableArrayTransfer {
        return nullableArrayTransfer.apply { this.value = value }
    }

    fun nullString(): NullableStringTransfer {
        return nullableStringTransfer.apply { this.value = null }
    }

    fun nullNumber(): NullableNumberTransfer {
        return nullableNumberTransfer.apply { this.value = null }
    }

    fun nullBoolean(): NullableBooleanTransfer {
        return nullableBooleanTransfer.apply { this.value = null }
    }

    fun nullObject(): GenericObjectTransfer<ObjType> {
        return genericObjectTransfer.apply { this.value = null }
    }

    fun nullArray(): GenericArrayTransfer<ArrType> {
        return genericArrayTransfer.apply { this.value = null }
    }
}
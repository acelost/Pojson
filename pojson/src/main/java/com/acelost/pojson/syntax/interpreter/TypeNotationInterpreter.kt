package com.acelost.pojson.syntax.interpreter

import com.acelost.pojson.*
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype

open class TypeNotationInterpreter<ObjType, ArrType>(
    @PublishedApi
    @JvmField
    internal var context: PojsonContext<ObjType, ArrType>
) {

    fun nullable(value: String?): NullableStringTransfer {
        return context.wrapNullable(value)
    }

    fun nullable(value: Number?): NullableNumberTransfer {
        return context.wrapNullable(value)
    }

    fun nullable(value: Boolean?): NullableBooleanTransfer {
        return context.wrapNullable(value)
    }

    fun nullable(value: JsonObjectPrototype?): NullableObjectTransfer {
        return context.wrapNullable(value)
    }

    fun nullable(value: JsonArrayPrototype?): NullableArrayTransfer {
        return context.wrapNullable(value)
    }

    fun nullString(): NullableStringTransfer {
        return context.nullString()
    }

    fun nullNumber(): NullableNumberTransfer {
        return context.nullNumber()
    }

    fun nullBoolean(): NullableBooleanTransfer {
        return context.nullBoolean()
    }

    fun nullObject(): GenericObjectTransfer<ObjType> {
        return context.nullObject()
    }

    fun nullArray(): GenericArrayTransfer<ArrType> {
        return context.nullArray()
    }

    inline fun obj(notation: JsonObjectNotation): GenericObjectTransfer<ObjType> {
        return context.newObject(notation)
    }

    inline fun array(notation: JsonArrayNotation): GenericArrayTransfer<ArrType> {
        return context.newArray(notation)
    }

    fun objects(
        items: Iterable<JsonObjectPrototype>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    inline fun <T> objects(
        items: Iterable<T>,
        crossinline notation: ParameterizedJsonObjectNotation<T>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(obj { notation(item) })
            }
        }
    }

    fun numbers(items: Iterable<Number>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun strings(items: Iterable<String>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun booleans(items: Iterable<Boolean>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun arrays(
        items: Iterable<JsonArrayPrototype>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    inline fun <T> arrays(
        items: Iterable<T>,
        crossinline notation: ParameterizedJsonArrayNotation<T>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(array { notation(item) })
            }
        }
    }
}
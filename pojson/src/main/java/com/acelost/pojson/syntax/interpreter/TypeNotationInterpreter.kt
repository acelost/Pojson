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

    inline fun <T> obj(context: T, notation: ParameterizedJsonObjectNotation<T>): GenericObjectTransfer<ObjType> {
        return this.context.newObject {
            notation.invoke(this, context)
        }
    }

    inline fun array(notation: JsonArrayNotation): GenericArrayTransfer<ArrType> {
        return context.newArray(notation)
    }

    fun arrayOfNumbers(items: Iterable<Number>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun arrayOfNumbers(vararg items: Number): GenericArrayTransfer<ArrType> {
        return arrayOfNumbers(*items)
    }

    fun arrayOfStrings(items: Iterable<String>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun arrayOfStrings(vararg items: String): GenericArrayTransfer<ArrType> {
        return arrayOfStrings(*items)
    }

    fun arrayOfBooleans(items: Iterable<Boolean>): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun arrayOfBooleans(vararg items: Boolean): GenericArrayTransfer<ArrType> {
        return arrayOfBooleans(*items)
    }

    fun arrayOfObjects(
        items: Iterable<JsonObjectPrototype>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    inline fun <T> arrayOfObjects(
        items: Iterable<T>,
        crossinline notation: ParameterizedJsonObjectNotation<T>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(obj { notation(item) })
            }
        }
    }

    fun arrayOfArrays(
        items: Iterable<JsonArrayPrototype>
    ): GenericArrayTransfer<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    inline fun <T> arrayOfArrays(
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
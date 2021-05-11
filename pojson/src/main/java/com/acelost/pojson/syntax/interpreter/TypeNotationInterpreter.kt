package com.acelost.pojson.syntax.interpreter

import com.acelost.pojson.*

open class TypeNotationInterpreter<ObjType, ArrType>(
    @PublishedApi
    @JvmField
    internal var context: PojsonContext<ObjType, ArrType>
) {

    fun nullable(value: String?): JsonPrimitiveWrapper {
        return context.wrapNullable(value)
    }

    fun nullable(value: Number): JsonPrimitiveWrapper {
        return context.wrapNullable(value)
    }

    fun nullable(value: Boolean): JsonPrimitiveWrapper {
        return context.wrapNullable(value)
    }

    fun nullValue(): JsonPrimitiveWrapper {
        return context.nullPrimitive()
    }

    fun nullObject(): JsonObjectWrapper<ObjType> {
        return context.nullObject()
    }

    inline fun obj(notation: JsonObjectNotation): JsonObjectWrapper<ObjType> {
        return context.newObject(notation)
    }

    inline fun array(notation: JsonArrayNotation): JsonArrayWrapper<ArrType> {
        return context.newArray(notation)
    }

    inline fun <T> objects(
        items: Iterable<T>,
        crossinline notation: ParameterizedJsonObjectNotation<T>
    ): JsonArrayWrapper<ArrType> {
        return array {
            for (item in items) {
                element(obj { notation(item) })
            }
        }
    }

    fun numbers(items: Iterable<Number>): JsonArrayWrapper<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun strings(items: Iterable<String>): JsonArrayWrapper<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    fun booleans(items: Iterable<Boolean>): JsonArrayWrapper<ArrType> {
        return array {
            for (item in items) {
                element(item)
            }
        }
    }

    inline fun <T> arrays(
        items: Iterable<T>,
        crossinline notation: ParameterizedJsonArrayNotation<T>
    ): JsonArrayWrapper<ArrType> {
        return array {
            for (item in items) {
                element(array { notation(item) })
            }
        }
    }
}
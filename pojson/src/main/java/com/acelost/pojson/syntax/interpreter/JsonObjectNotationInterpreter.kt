package com.acelost.pojson.syntax.interpreter

import com.acelost.pojson.*
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
import com.acelost.pojson.syntax.annotation.PojsonDslMarker

@PojsonDslMarker
class JsonObjectNotationInterpreter<ObjType, ArrType>(
    context: PojsonContext<ObjType, ArrType>
) : TypeNotationInterpreter<ObjType, ArrType>(context) {

    infix operator fun String.rem(value: String) {
        val key = this
        context.updateObject { target ->
            addStringProperty(target, key, value)
        }
    }

    infix operator fun String.rem(value: Number) {
        val key = this
        context.updateObject { target ->
            addNumberProperty(target, key, value)
        }
    }

    infix operator fun String.rem(value: Boolean) {
        val key = this
        context.updateObject { target ->
            addBooleanProperty(target, key, value)
        }
    }

    infix operator fun String.rem(wrapper: JsonPrimitiveWrapper) {
        val key = this
        context.updateObject { target ->
            when (val value = wrapper.value) {
                is String -> addStringProperty(target, key, value)
                is Number -> addNumberProperty(target, key, value)
                is Boolean -> addBooleanProperty(target, key, value)
                else -> addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(wrapper: JsonObjectWrapper<*>) {
        val key = this
        context.updateObject { target ->
            if (wrapper.value != null) {
                @Suppress("UNCHECKED_CAST")
                addObjectProperty(target, key, wrapper.value as ObjType)
            } else {
                addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(wrapper: JsonArrayWrapper<*>) {
        val key = this
        context.updateObject { target ->
            @Suppress("UNCHECKED_CAST")
            addArrayProperty(target, key, wrapper.value as ArrType)
        }
    }

    infix operator fun String.rem(value: JsonObjectPrototype) {
        this % obj(value.notation)
    }

    infix operator fun String.rem(value: JsonArrayPrototype) {
        this % array(value.notation)
    }

    fun include(value: JsonObjectPrototype) {
        context.updateObject(value.notation)
    }
}
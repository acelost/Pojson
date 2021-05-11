package com.acelost.pojson.syntax.interpreter

import com.acelost.pojson.*
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
import com.acelost.pojson.syntax.annotation.PojsonDslMarker

@PojsonDslMarker
class JsonArrayNotationInterpreter<ObjType, ArrType>(
    context: PojsonContext<ObjType, ArrType>
) : TypeNotationInterpreter<ObjType, ArrType>(context) {

    fun element(value: String) {
        context.updateArray { target ->
            addStringElement(target, value)
        }
    }

    fun element(value: Number) {
        context.updateArray { target ->
            addNumberElement(target, value)
        }
    }

    fun element(value: Boolean) {
        context.updateArray { target ->
            addBooleanElement(target, value)
        }
    }

    fun element(wrapper: JsonObjectWrapper<*>) {
        context.updateArray { target ->
            if (wrapper.value != null) {
                @Suppress("UNCHECKED_CAST")
                addObjectElement(target, wrapper.value as ObjType)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(wrapper: JsonArrayWrapper<*>) {
        context.updateArray { target ->
            @Suppress("UNCHECKED_CAST")
            addArrayElement(target, wrapper.value as ArrType)
        }
    }

    fun element(value: JsonObjectPrototype) {
        element(obj(value.notation))
    }

    fun element(value: JsonArrayPrototype) {
        element(array(value.notation))
    }

    fun element(wrapper: JsonPrimitiveWrapper) {
        context.updateArray { target ->
            when (val value = wrapper.value) {
                is String -> addStringElement(target, value)
                is Number -> addNumberElement(target, value)
                is Boolean -> addBooleanElement(target, value)
                else -> addNullElement(target)
            }
        }
    }
}
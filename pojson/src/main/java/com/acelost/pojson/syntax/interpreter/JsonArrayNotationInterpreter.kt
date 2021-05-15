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

    fun element(transfer: GenericObjectTransfer<*>) {
        context.updateArray { target ->
            if (transfer.value != null) {
                @Suppress("UNCHECKED_CAST")
                addObjectElement(target, transfer.value as ObjType)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(transfer: GenericArrayTransfer<*>) {
        context.updateArray { target ->
            @Suppress("UNCHECKED_CAST")
            addArrayElement(target, transfer.value as ArrType)
        }
    }

    fun element(value: JsonObjectPrototype) {
        element(obj(value.notation))
    }

    fun element(value: JsonArrayPrototype) {
        element(array(value.notation))
    }

    fun element(transfer: NullableStringTransfer) {
        context.updateArray { target ->
            val value = transfer.value
            if (value != null) {
                addStringElement(target, value)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(transfer: NullableNumberTransfer) {
        context.updateArray { target ->
            val value = transfer.value
            if (value != null) {
                addNumberElement(target, value)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(transfer: NullableBooleanTransfer) {
        context.updateArray { target ->
            val value = transfer.value
            if (value != null) {
                addBooleanElement(target, value)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(transfer: NullableObjectTransfer) {
        context.updateArray { target ->
            val value = transfer.value
            if (value != null) {
                element(value)
            } else {
                addNullElement(target)
            }
        }
    }

    fun element(transfer: NullableArrayTransfer) {
        context.updateArray { target ->
            val value = transfer.value
            if (value != null) {
                element(value)
            } else {
                addNullElement(target)
            }
        }
    }

    fun merge(value: JsonArrayPrototype) {
        context.updateArray(value.notation)
    }
}
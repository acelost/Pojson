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

    infix operator fun String.rem(transfer: NullableStringTransfer) {
        val key = this
        context.updateObject { target ->
            val value = transfer.value
            if (value != null) {
                addStringProperty(target, key, value)
            } else {
                addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(transfer: NullableNumberTransfer) {
        val key = this
        context.updateObject { target ->
            val value = transfer.value
            if (value != null) {
                addNumberProperty(target, key, value)
            } else {
                addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(transfer: NullableBooleanTransfer) {
        val key = this
        context.updateObject { target ->
            val value = transfer.value
            if (value != null) {
                addBooleanProperty(target, key, value)
            } else {
                addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(transfer: GenericObjectTransfer<*>) {
        val key = this
        context.updateObject { target ->
            if (transfer.value != null) {
                @Suppress("UNCHECKED_CAST")
                addObjectProperty(target, key, transfer.value as ObjType)
            } else {
                addNullProperty(target, key)
            }
        }
    }

    infix operator fun String.rem(transfer: GenericArrayTransfer<*>) {
        val key = this
        context.updateObject { target ->
            @Suppress("UNCHECKED_CAST")
            addArrayProperty(target, key, transfer.value as ArrType)
        }
    }

    infix operator fun String.rem(value: JsonObjectPrototype) {
        this % obj(value.notation)
    }

    infix operator fun String.rem(transfer: NullableObjectTransfer) {
        val notation = transfer.value?.notation
        this % if (notation != null) obj(notation) else nullObject()
    }

    infix operator fun String.rem(value: JsonArrayPrototype) {
        this % array(value.notation)
    }

    infix operator fun String.rem(transfer: NullableArrayTransfer) {
        val notation = transfer.value?.notation
        this % if (notation != null) array(notation) else nullArray()
    }

    fun merge(value: JsonObjectPrototype) {
        context.updateObject(value.notation)
    }
}
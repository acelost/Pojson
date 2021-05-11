package com.acelost.pojson.adapter

interface JsonArrayAdapter<ObjType, ArrType> {

    fun addStringElement(target: ArrType, value: String)

    fun addNumberElement(target: ArrType, value: Number)

    fun addBooleanElement(target: ArrType, value: Boolean)

    fun addObjectElement(target: ArrType, value: ObjType)

    fun addArrayElement(target: ArrType, value: ArrType)

    fun addNullElement(target: ArrType)
}
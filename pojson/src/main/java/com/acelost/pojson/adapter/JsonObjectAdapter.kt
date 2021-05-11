package com.acelost.pojson.adapter

interface JsonObjectAdapter<ObjType, ArrType> {

    fun addStringProperty(target: ObjType, key: String, value: String)

    fun addNumberProperty(target: ObjType, key: String, value: Number)

    fun addBooleanProperty(target: ObjType, key: String, value: Boolean)

    fun addObjectProperty(target: ObjType, key: String, value: ObjType)

    fun addArrayProperty(target: ObjType, key: String, value: ArrType)

    fun addNullProperty(target: ObjType, key: String)
}
package com.acelost.pojson.factory

interface JsonObjectFactory<ObjType> {

    fun newInstance(): ObjType
}
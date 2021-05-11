package com.acelost.pojson.factory

interface JsonArrayFactory<ArrayType> {

    fun newInstance(): ArrayType
}
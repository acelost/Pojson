package com.acelost.pojson

class JsonObjectWrapper<T> {
    @JvmField
    var value: T? = null
}

class JsonArrayWrapper<T> {
    @JvmField
    var value: T? = null
}

class JsonPrimitiveWrapper {
    @JvmField
    var value: Any? = null
}
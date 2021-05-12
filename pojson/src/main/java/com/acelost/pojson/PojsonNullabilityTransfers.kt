package com.acelost.pojson

import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype

class NullableStringTransfer {
    @JvmField
    var value: String? = null
}

class NullableNumberTransfer {
    @JvmField
    var value: Number? = null
}

class NullableBooleanTransfer {
    @JvmField
    var value: Boolean? = null
}

class NullableObjectTransfer {
    @JvmField
    var value: JsonObjectPrototype? = null
}

class NullableArrayTransfer {
    @JvmField
    var value: JsonArrayPrototype? = null
}
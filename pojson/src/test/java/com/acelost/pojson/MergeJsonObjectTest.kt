package com.acelost.pojson

import com.acelost.pojson.prototype.JsonObjectPrototype
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MergeJsonObjectTest : BasePojsonTest() {

    @Test
    fun `Object prototype includes properties from merged object`() {
        val mixin = JsonObjectPrototype {
            "mixin-property" % 42L
        }
        val prototype = JsonObjectPrototype {
            merge(mixin)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("mixin-property"), eq(42L))
    }

    @Test
    fun `Object prototype includes properties from merged notation`() {
        val mixin: JsonObjectNotation = {
            "mixin-property" % 42L
        }
        val prototype = JsonObjectPrototype {
            merge(mixin)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("mixin-property"), eq(42L))
    }
}
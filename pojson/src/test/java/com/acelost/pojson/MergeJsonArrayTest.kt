package com.acelost.pojson

import com.acelost.pojson.prototype.JsonArrayPrototype
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class MergeJsonArrayTest : BasePojsonTest() {

    @Test
    fun `Array prototype includes elements from merged array`() {
        val mixin = JsonArrayPrototype {
            element(42L)
        }
        val prototype = JsonArrayPrototype {
            merge(mixin)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(42L))
    }

    @Test
    fun `Array prototype includes elements from merged notation`() {
        val mixin: JsonArrayNotation = {
            element(42L)
        }
        val prototype = JsonArrayPrototype {
            merge(mixin)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(42L))
    }
}
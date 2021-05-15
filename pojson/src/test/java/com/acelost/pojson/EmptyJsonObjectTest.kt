package com.acelost.pojson

import com.acelost.pojson.prototype.JsonObjectPrototype
import org.junit.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class EmptyJsonObjectTest : BasePojsonTest() {

    @Test
    fun `Object prototype without properties spawns one object without properties`() {
        val prototype = JsonObjectPrototype {
            // no-properties
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
    }
}
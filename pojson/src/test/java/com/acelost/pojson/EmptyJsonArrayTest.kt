package com.acelost.pojson

import com.acelost.pojson.prototype.JsonArrayPrototype
import org.junit.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class EmptyJsonArrayTest : BasePojsonTest() {

    @Test
    fun `Array prototype without elements spawns one array without elements`() {
        val prototype = JsonArrayPrototype {
            // no-elements
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
    }
}
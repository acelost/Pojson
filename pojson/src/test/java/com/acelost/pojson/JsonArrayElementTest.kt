package com.acelost.pojson

import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class JsonArrayElementTest : BasePojsonTest() {

    @Test
    fun `String element triggers addStringElement method`() {
        val prototype = JsonArrayPrototype {
            element("Hello, world!")
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("Hello, world!"))
    }

    @Test
    fun `Number element triggers addNumberElement method`() {
        val prototype = JsonArrayPrototype {
            element(42)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(42))
    }

    @Test
    fun `Boolean element triggers addNumberElement method`() {
        val prototype = JsonArrayPrototype {
            element(true)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(true))
    }

    @Test
    fun `Object element triggers addObjectElement method`() {
        val prototype = JsonArrayPrototype {
            element(obj {
                // no-properties
            })
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addObjectElement(any(), any())
    }

    @Test
    fun `Array element triggers addObjectElement method`() {
        val prototype = JsonArrayPrototype {
            element(array {
                // no-elements
            })
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Null string element triggers addNullElement method`() {
        val prototype = JsonArrayPrototype {
            element(nullString())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Null number element triggers addNullElement method`() {
        val prototype = JsonArrayPrototype {
            element(nullNumber())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Null boolean element triggers addNullElement method`() {
        val prototype = JsonArrayPrototype {
            element(nullBoolean())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Null object element triggers addNullElement method`() {
        val prototype = JsonArrayPrototype {
            element(nullObject())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Object prototype element triggers addObjectElement method`() {
        val prototype = JsonArrayPrototype {
            element(JsonObjectPrototype {
                // no-properties
            })
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addObjectElement(any(), any())
    }

    @Test
    fun `Array prototype element triggers addArrayElement method`() {
        val prototype = JsonArrayPrototype {
            element(JsonArrayPrototype {
                // no-elements
            })
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Strings element triggers addArrayElement method`() {
        val prototype = JsonArrayPrototype {
            element(strings(listOf("hello", "world")))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("hello"))
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("world"))
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Numbers element triggers addArrayElement method`() {
        val prototype = JsonArrayPrototype {
            element(numbers(listOf(1, 2, 3)))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(1))
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(2))
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(3))
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Booleans element triggers addArrayElement method`() {
        val prototype = JsonArrayPrototype {
            element(booleans(listOf(true, false)))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(true))
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(false))
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Objects element triggers addArrayElement method`() {
        val items = listOf(
            JsonObjectPrototype {
                // no-properties
            }
        )
        val prototype = JsonArrayPrototype {
            element(objects(items))
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addObjectElement(any(), any())
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Arrays element triggers addArrayElement method`() {
        val items = listOf(
            JsonArrayPrototype {
                // no-elements
            }
        )
        val prototype = JsonArrayPrototype {
            element(arrays(items))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(3)).newInstance()
        verify(jsonArrayAdapter, times(2)).addArrayElement(any(), any())
    }

    @Test
    fun `Nullable string element triggers addStringElement method`() {
        val nullableString: String? = "Hello, world!"
        val prototype = JsonArrayPrototype {
            element(nullable(nullableString))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("Hello, world!"))
    }

    @Test
    fun `Nullable string element triggers addNullElement method`() {
        val nullableString: String? = null
        val prototype = JsonArrayPrototype {
            element(nullable(nullableString))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Nullable number element triggers addNumberElement method`() {
        val nullableNumber: Number? = 42L
        val prototype = JsonArrayPrototype {
            element(nullable(nullableNumber))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(42L))
    }

    @Test
    fun `Nullable number element triggers addNullElement method`() {
        val nullableNumber: Number? = null
        val prototype = JsonArrayPrototype {
            element(nullable(nullableNumber))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Nullable boolean element triggers addBooleanElement method`() {
        val nullableBoolean: Boolean? = true
        val prototype = JsonArrayPrototype {
            element(nullable(nullableBoolean))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(true))
    }

    @Test
    fun `Nullable boolean element triggers addNullElement method`() {
        val nullableBoolean: Number? = null
        val prototype = JsonArrayPrototype {
            element(nullable(nullableBoolean))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Nullable object prototype element triggers addObjectElement method`() {
        val nullableObject: JsonObjectPrototype? = JsonObjectPrototype {
            // no-properties
        }
        val prototype = JsonArrayPrototype {
            element(nullable(nullableObject))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addObjectElement(any(), any())
    }

    @Test
    fun `Nullable object prototype element triggers addNullElement method`() {
        val nullableObject: JsonObjectPrototype? = null
        val prototype = JsonArrayPrototype {
            element(nullable(nullableObject))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Nullable array prototype element triggers addArrayElement method`() {
        val nullableArray: JsonArrayPrototype? = JsonArrayPrototype {
            // no-properties
        }
        val prototype = JsonArrayPrototype {
            element(nullable(nullableArray))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Nullable array prototype element triggers addNullElement method`() {
        val nullableObject: JsonArrayPrototype? = null
        val prototype = JsonArrayPrototype {
            element(nullable(nullableObject))
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }
}
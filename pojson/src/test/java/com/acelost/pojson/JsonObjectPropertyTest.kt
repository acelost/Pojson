package com.acelost.pojson

import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class JsonObjectPropertyTest : BasePojsonTest() {

    @Test
    fun `String property assignation triggers addStringProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % "Hello, world!"
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addStringProperty(any(), eq("my-property"), eq("Hello, world!"))
    }

    @Test
    fun `Number property assignation triggers addNumberProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % 42L
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("my-property"), eq(42L))
    }

    @Test
    fun `Boolean property assignation triggers addBooleanProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % true
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addBooleanProperty(any(), eq("my-property"), eq(true))
    }

    @Test
    fun `Object property assignation triggers addObjectProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % obj {
                // no-properties
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("my-property"), any())
    }

    @Test
    fun `Array property assignation triggers addArrayProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % array {
                // no-elements
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-property"), any())
    }

    @Test
    fun `Null string property assignation triggers addNullProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullString()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Null number property assignation triggers addNullProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullNumber()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Null boolean property assignation triggers addNullProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullBoolean()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Null object property assignation triggers addNullProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullObject()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Object prototype property assignation triggers addObjectProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-object" % JsonObjectPrototype {
                // no-properties
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("my-object"), any())
    }

    @Test
    fun `Array prototype property assignation triggers addArrayProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-array" % JsonArrayPrototype {
                //no-elements
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
    }

    @Test
    fun `Strings property assignation triggers addArrayProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-array" % strings(listOf("hello", "world"))
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("hello"))
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("world"))
    }

    @Test
    fun `Numbers property assignation triggers addArrayProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-array" % numbers(listOf(1, 2, 3))
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(1))
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(2))
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(3))
    }

    @Test
    fun `Booleans property assignation triggers addArrayProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-array" % booleans(listOf(true, false))
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(true))
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(false))
    }

    @Test
    fun `Objects property assignation triggers addArrayProperty method`() {
        val items = listOf(
            JsonObjectPrototype {
                // no-properties
            }
        )
        val prototype = JsonObjectPrototype {
            "my-array" % objects(items)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
        verify(jsonArrayAdapter, times(1)).addObjectElement(any(), any())
    }

    @Test
    fun `Arrays property assignation triggers addArrayPropery method`() {
        val items = listOf(
            JsonArrayPrototype {
                // no-elements
            }
        )
        val prototype = JsonObjectPrototype {
            "my-array" % arrays(items)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-array"), any())
        verify(jsonArrayAdapter, times(1)).addArrayElement(any(), any())
    }

    @Test
    fun `Nullable string property assignation triggers addStringProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullable("Hello, world!")
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addStringProperty(any(), eq("my-property"), eq("Hello, world!"))
    }

    @Test
    fun `Nullable string property assignation triggers addNullProperty method`() {
        val nullableString: String? = null
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableString)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Nullable number property assignation triggers addNumberProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(42)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("my-property"), eq(42))
    }

    @Test
    fun `Nullable number property assignation triggers addNullProperty method`() {
        val nullableNumber: Number? = null
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableNumber)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Nullable boolean property assignation triggers addBooleanProperty method`() {
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(true)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addBooleanProperty(any(), eq("my-property"), eq(true))
    }

    @Test
    fun `Nullable boolean property assignation triggers addNullProperty method`() {
        val nullableBoolean: Boolean? = null
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableBoolean)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Nullable object prototype property assignation triggers addObjectProperty method`() {
        val nullableObject: JsonObjectPrototype? = JsonObjectPrototype {
            // no-properties
        }
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableObject)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("my-property"), any())
    }

    @Test
    fun `Nullable object prototype property assignation triggers addNullProperty method`() {
        val nullableObject: JsonObjectPrototype? = null
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableObject)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }

    @Test
    fun `Nullable array prototype property assignation triggers addArrayProperty method`() {
        val nullableArray: JsonArrayPrototype? = JsonArrayPrototype {
            // no-elements
        }
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableArray)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("my-property"), any())
    }

    @Test
    fun `Nullable array prototype property assignation triggers addNullProperty method`() {
        val nullableArray: JsonArrayPrototype? = null
        val prototype = JsonObjectPrototype {
            "my-property" % nullable(nullableArray)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("my-property"))
    }
}
package com.acelost.pojson

import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import com.acelost.pojson.prototype.JsonArrayPrototype
import com.acelost.pojson.prototype.JsonObjectPrototype
import org.junit.After
import org.junit.Test
import org.mockito.kotlin.*

class PojsonTest {

    private val jsonObjectFactory = mock<JsonObjectFactory<Any>> {
        on { newInstance() } doReturn Any()
    }
    private val jsonArrayFactory = mock<JsonArrayFactory<Any>> {
        on { newInstance() } doReturn Any()
    }
    private val jsonObjectAdapter = mock<JsonObjectAdapter<Any, Any>>()
    private val jsonArrayAdapter = mock<JsonArrayAdapter<Any, Any>>()
    private val pojson = Pojson(
        objectFactory = jsonObjectFactory,
        arrayFactory = jsonArrayFactory,
        objectAdapter = jsonObjectAdapter,
        arrayAdapter = jsonArrayAdapter
    )

    @After
    fun `Assert all interactions checked`() {
        verifyNoMoreInteractions(jsonObjectFactory)
        verifyNoMoreInteractions(jsonArrayAdapter)
        verifyNoMoreInteractions(jsonObjectAdapter)
        verifyNoMoreInteractions(jsonArrayAdapter)
    }

    @Test
    fun `Object prototype without properties spawns one object without properties`() {
        val prototype = JsonObjectPrototype {
            // no-properties
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
    }

    @Test
    fun `Object prototype with number property spawns one object with number property`() {
        val prototype = JsonObjectPrototype {
            "my-property" % 42L
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("my-property"), eq(42L))
    }

    @Test
    fun `Object prototype with number property spawns one object with string property`() {
        val prototype = JsonObjectPrototype {
            "my-property" % "Hello, world!"
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addStringProperty(any(), eq("my-property"), eq("Hello, world!"))
    }

    @Test
    fun `Object prototype with number property spawns one object with boolean property`() {
        val prototype = JsonObjectPrototype {
            "my-property" % true
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addBooleanProperty(any(), eq("my-property"), eq(true))
    }

    @Test
    fun `Object prototype with nested object spawns object with object property`() {
        val prototype = JsonObjectPrototype {
            "nested-obj" % obj {
                // no-properties
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("nested-obj"), any())
    }

    @Test
    fun `Object prototype with nested array spawns object with array property`() {
        val prototype = JsonObjectPrototype {
            "nested-array" % array {
                // no-elements
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("nested-array"), any())
    }

    @Test
    fun `Object prototype with null value property spawns object with null property`() {
        val prototype = JsonObjectPrototype {
            "null-property" % nullString()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("null-property"))
    }

    @Test
    fun `Object prototype with null object property spawns object with null property`() {
        val prototype = JsonObjectPrototype {
            "null-property" % nullObject()
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("null-property"))
    }

    @Test
    fun `Object prototype with nested object prototype spawns object with nested object`() {
        val prototype = JsonObjectPrototype {
            "nested-object" % JsonObjectPrototype {
                // no-properties
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("nested-object"), any())
    }

    @Test
    fun `Object prototype with nested array prototype spawns object with nested array`() {
        val prototype = JsonObjectPrototype {
            "nested-array" % JsonArrayPrototype {
                //no-elements
            }
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addArrayProperty(any(), eq("nested-array"), any())
    }

    @Test
    fun `Object prototype with nullable primitive property spawns object with value`() {
        val prototype = JsonObjectPrototype {
            "nullable-property" % nullable(42L)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNumberProperty(any(), eq("nullable-property"), eq(42L))
    }

    @Test
    fun `Object prototype with nullable primitive property spawns object with null`() {
        val nullableString: String? = null
        val prototype = JsonObjectPrototype {
            "nullable-property" % nullable(nullableString)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("nullable-property"))
    }

    @Test
    fun `Object prototype with nullable object property spawns object with value`() {
        val nullableObject = JsonObjectPrototype {
            // no-properties
        }
        val prototype = JsonObjectPrototype {
            "nullable-property" % nullable(nullableObject)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(2)).newInstance()
        verify(jsonObjectAdapter, times(1)).addObjectProperty(any(), eq("nullable-property"), any())
    }

    @Test
    fun `Object prototype with nullable object property spawns object with null`() {
        val nullableObject: JsonObjectPrototype? = null
        val prototype = JsonObjectPrototype {
            "nullable-property" % nullable(nullableObject)
        }
        pojson.render(prototype)

        verify(jsonObjectFactory, times(1)).newInstance()
        verify(jsonObjectAdapter, times(1)).addNullProperty(any(), eq("nullable-property"))
    }

    @Test
    fun `Object prototype includes properties from mixin object`() {
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
    fun `Array prototype without elements spawns one array without elements`() {
        val prototype = JsonArrayPrototype {
            // no-elements
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
    }

    @Test
    fun `Array prototype with number element spawns one array with number element`() {
        val prototype = JsonArrayPrototype {
            element(42L)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNumberElement(any(), eq(42L))
    }

    @Test
    fun `Array prototype with string element spawns one array with string element`() {
        val prototype = JsonArrayPrototype {
            element("Hello, world!")
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addStringElement(any(), eq("Hello, world!"))
    }

    @Test
    fun `Array prototype with boolean element spawns one array with boolean element`() {
        val prototype = JsonArrayPrototype {
            element(true)
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addBooleanElement(any(), eq(true))
    }

    @Test
    fun `Array prototype with object element spawns one array with object element`() {
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
    fun `Array prototype with array element spawns one array with array element`() {
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
    fun `Array prototype with null value element spawns one array with null element`() {
        val prototype = JsonArrayPrototype {
            element(nullString())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Array prototype with null object element spawns one array with null element`() {
        val prototype = JsonArrayPrototype {
            element(nullObject())
        }
        pojson.render(prototype)

        verify(jsonArrayFactory, times(1)).newInstance()
        verify(jsonArrayAdapter, times(1)).addNullElement(any())
    }

    @Test
    fun `Array prototype with nested object prototype spawns one array with object element`() {
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
    fun `Array prototype with nested array prototype spawns array with nested array element`() {
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
    fun `Array prototype includes elements from mixin array`() {
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
}
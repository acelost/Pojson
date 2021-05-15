package com.acelost.pojson

import com.acelost.pojson.adapter.JsonArrayAdapter
import com.acelost.pojson.adapter.JsonObjectAdapter
import com.acelost.pojson.factory.JsonArrayFactory
import com.acelost.pojson.factory.JsonObjectFactory
import org.junit.After
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoMoreInteractions

open class BasePojsonTest {

    protected val jsonObjectFactory = mock<JsonObjectFactory<Any>> {
        on { newInstance() } doReturn Any()
    }
    protected val jsonArrayFactory = mock<JsonArrayFactory<Any>> {
        on { newInstance() } doReturn Any()
    }
    protected val jsonObjectAdapter = mock<JsonObjectAdapter<Any, Any>>()
    protected val jsonArrayAdapter = mock<JsonArrayAdapter<Any, Any>>()
    protected val pojson = Pojson(
        objectFactory = jsonObjectFactory,
        arrayFactory = jsonArrayFactory,
        objectAdapter = jsonObjectAdapter,
        arrayAdapter = jsonArrayAdapter
    )

    @After
    fun `Assert all interactions checked`() {
        verifyNoMoreInteractions(jsonObjectFactory)
        verifyNoMoreInteractions(jsonArrayFactory)
        verifyNoMoreInteractions(jsonObjectAdapter)
        verifyNoMoreInteractions(jsonArrayAdapter)
    }
}
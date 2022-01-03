package com.acelost.pojson.prototype

import com.acelost.pojson.JsonArrayNotation
import com.acelost.pojson.ParameterizedJsonArrayNotation
import com.acelost.pojson.ParameterizedJsonObjectNotation

class JsonArrayPrototype(val notation: JsonArrayNotation) {

    companion object {

        fun numbers(items: Iterable<Number>): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(item)
                }
            }
        }

        fun strings(items: Iterable<String>): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(item)
                }
            }
        }

        fun booleans(items: Iterable<Boolean>): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(item)
                }
            }
        }

        fun objects(items: Iterable<JsonObjectPrototype>): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(item)
                }
            }
        }

        inline fun <T> objects(
            items: Iterable<T>,
            crossinline notation: ParameterizedJsonObjectNotation<T>
        ): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(obj { notation(item) })
                }
            }
        }

        fun arrays(items: Iterable<JsonArrayPrototype>): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(item)
                }
            }
        }

        inline fun <T> arrays(
            items: Iterable<T>,
            crossinline notation: ParameterizedJsonArrayNotation<T>
        ): JsonArrayPrototype {
            return JsonArrayPrototype {
                for (item in items) {
                    element(array { notation(item) })
                }
            }
        }
    }
}
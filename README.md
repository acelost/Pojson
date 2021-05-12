# Pojson

Pojson is a kotlin library for json prototyping. It brings DSL for building `JsonObjectPrototype` and `JsonArrayPrototype`. Prototypes don't references to any json object/array models. You can choose implementation you want to use for rendering json. Simple json prototype may looks like:

```kotlin
val prototype = JsonObjectPrototype {
    "my-string-property" % "Hello, world!"
    "my-number-property" % 42L
    "my-object-property" % obj {
        "my-nested-object-property" % true
    }
    "my-array-property" % array {
        element("Hello, world!")
        element(42L)
        element(true)
    }
}
```
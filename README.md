# Pojson

Pojson is a kotlin library for json prototyping. It brings DSL for building `JsonObjectPrototype` and `JsonArrayPrototype`. Prototypes don't reference to any json object/array models. You can choose implementation you want to use for rendering json. Simple json prototype may looks like:

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

## Usage

Actually `Pojson` is a kotlin class with methods `render` for object and array.
```kotlin
val prototype = JsonObjectPrototype {
    ..
}
val pojson = Pojson(..)
pojson.render(prototype) // Returns selected json object representation
```
`Pojson` constructor requires implementation of interfaces `JsonObjectFactory`, `JsonArrayFactory`, `JsonObjectAdapter`, `JsonArrayAdapter`. Usually implementation of this interfaces is a simple task. You can implement this interfaces manually or use one of existing integration with json object/array representation.
```kotlin
val prototype = JsonObjectPrototype {
    ..
}
Pojson2Gson.create().render(prototype) // Returns com.google.gson.JsonObject
Pojson2AndroidJSON.create().render(prototype) // Returns org.json.JSONObject
Pojson2KotlinCollection.create().render(prototype) // Returns kotlin.collections.MutableMap
```

## Syntax

### Create object
Pojson provides class `JsonObjectPrototype` with constructor as entry point for object notation.
```kotlin
JsonObjectPrototype {
    ..
}
```

### Assign property
Pojson provides overloaded operator `%` for assigning value to key in object notation.
```kotlin
JsonObjectPrototype {
    "my-property" % "my-value"
}
```

### Create array
Pojson provides class `JsonArrayPrototype` with constructor as entry point for array notation.
```kotlin
JsonArrayPrototype {
    ..
}
```

### Append element
Pojson provides `element(..)` method for appending items to array.
```kotlin
JsonArrayPrototype {
    element(42L)
    element("Hello, world!")
}
```

### Nested object
Pojson provides method `obj { .. }` for nested object instantiation.
```kotlin
JsonObjectPrototype {
    "my-property" % obj {
        "my-nested-property" % "my-nested-value"
    }
}
```

### Nested array
Pojson provides method `array { .. }` for nested array instantiation.
```kotlin
JsonObjectPrototype {
    "my-property" % array {
        ..
    }
}
```

### Nullability
By default pojson requires all values to be non-null.
If you want to work with nullable values you should specify it explicitly.
Pojson provides method `nullable` for this purpose.
```kotlin
val myString: String? = "Hello, world!"
// Not working
JsonObjectPrototype {
    "my-property" % myString // IDE error: expected [String] but found [String?]
}
// Working
JsonObjectPrototype {
    "my-property" % nullable(myString) // No error, correct type inference
}
```
Also pojson provides utility methods `nullString`, `nullNumber`, `nullBoolean`, `nullObject` and `nullArray`  
for kotlin type inference when you want to use if-else expressions inside object/array notation.
```kotlin
JsonObjectPrototype {
    "my-property" % if (myFlag) obj {
        ..
    } else nullObject()
}
```

### Composition
Pojson provides capability to composite prototypes.
```kotlin
val manufacturer = JsonObjectPrototype {
    "name" % "Land Rover"
    "country" % "United Kingdom"
}
val car = JsonObjectPrototype {
    "model" % "Defender"
    "year" % 2017
    "manufacturer" % manufacturer
}
```

### Merge
Pojson provides capability to merge prototype properties into another prototype.
```kotlin
val design = JsonObjectPrototype {
    "color" % "black"
    "wheels" % "20.0 inch"
}
val car = JsonObjectPrototype {
    "model" % "Defender"
    "year" % 2017
    merge(design)
}
```

## Integration

### Core

![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.acelost/pojson-core/badge.svg)

Core module contains syntax declaration without integrations with third-party solutions.

```groovy
dependencies {
    implementation 'io.github.acelost:pojson-core:${latestVersion}'
}
```

### Gson

![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.acelost/pojson-gson/badge.svg)

Gson module contains syntax declaration plus integration with gson object model. You can render prototypes to `com.google.gson.JsonObject` and `com.google.gson.JsonArray`.

```groovy
dependencies {
    implementation 'io.github.acelost:pojson-gson:${latestVersion}'
}
```

### Android Json

![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.acelost/pojson-android-json/badge.svg)

Android Json module contains syntax declaration plus integration with native android object model. You can render prototypes to `org.json.JSONObject` and `org.json.JSONArray`.

```groovy
dependencies {
    implementation 'io.github.acelost:pojson-android-json:${latestVersion}'
}
```

### Kotlin Collection

![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.acelost/pojson-kotlin-collection/badge.svg)

Koltin Collection module contains syntax declaration plus integration with kotlin collections. You can render prototypes to `kotlin.collections.Map` and `kotlin.collections.List`.

```groovy
dependencies {
    implementation 'io.github.acelost:pojson-kotlin-collection:${latestVersion}'
}
```
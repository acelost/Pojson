package com.acelost.pojson

import com.acelost.pojson.syntax.interpreter.JsonArrayNotationInterpreter
import com.acelost.pojson.syntax.interpreter.JsonObjectNotationInterpreter

/**
 * Json object notation closure.
 */
typealias JsonObjectNotation = JsonObjectNotationInterpreter<*, *>.() -> Unit

/**
 * Json object notation closure with parameter.
 */
typealias ParameterizedJsonObjectNotation<T> = JsonObjectNotationInterpreter<*, *>.(T) -> Unit

/**
 * Json array notation closure.
 */
typealias JsonArrayNotation = JsonArrayNotationInterpreter<*, *>.() -> Unit

/**
 * Json array notation closure with parameter.
 */
typealias ParameterizedJsonArrayNotation<T> = JsonArrayNotationInterpreter<*, *>.(T) -> Unit
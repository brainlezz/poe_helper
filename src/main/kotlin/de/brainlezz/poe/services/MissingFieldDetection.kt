package de.brainlezz.poe.services

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class MissingFieldDetection : ExclusionStrategy{
    var lastClass = "none"
    override fun shouldSkipField(f: FieldAttributes?): Boolean {
        if (f == null ) return false
        var `class` = f.declaredClass
        if(`class` == null) println("Field without a class: ${f.name} (last class : $lastClass)")

        return false
    }

    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        lastClass = clazz!!.name
        return false
    }

}
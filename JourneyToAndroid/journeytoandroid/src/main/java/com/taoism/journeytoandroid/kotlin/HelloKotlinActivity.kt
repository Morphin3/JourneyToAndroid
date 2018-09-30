package com.taoism.journeytoandroid.kotlin

import android.os.Bundle
import com.taoism.journeytoandroid.BaseActivity
import com.taoism.journeytoandroid.R

class HelloKotlinActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_kotlin)

//        Class<?> threadClazz = Class.forName("com.taoism.journeytojava.annotation.AnnotationTest");

        sum(1, 2)

        val x = parseInt("3")
        val y = parseInt("4")
        if (x != null && y != null) {
            println(x + y)
        }

        test()

        println(describe("Hello"))
    }

    fun sum(a: Int, b: Int): Int {
        return a + b;
    }


//fun sum(a: Int, b: Int) = a + b

    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    val PI = 3.14;

    fun test() {
        val a: Int = 1
        val b = 2
        val c: Int
        c = 3

        var x = 5
        x += 1

        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }

        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }

        val array = listOf(1, 2, 3, 4)

//        for ((index, value) in array) {
//            println("the element at $index is $value")
//        }


        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { println(it) }

    }


    fun parseInt(str: String): Int? {
        val a: Int = 1
        return null
    }

    fun getStringLength(obj: Any): Int? {
        if (obj is String && obj.length > 0) {
            return obj.length
        }
        return null
    }

    fun describe(obj: Any): String =
            when (obj) {
                1 -> "one"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknown"
            }

    fun testRange(i: Int) {
        val x = 10
        val y = 9
        if (x in 1..y + 1) {
            println("$x fits in range")
        }
    }

    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }
}

package com.example.data.util

import com.google.firebase.firestore.QuerySnapshot

fun String?.toNotNull() = this ?: ""
fun Int?.toNotNull() = this ?: 0
fun Double?.toNotNull() = this ?: 0.0

/**
 * Helper fun to transform firestore results to a list of a certain object.
 *
 * Usage:
 *
 * val documents = firestore.collection("XXX").get().await()
 * val list = documents.toListOf<Foo>()
 *
 * list is now either a list of foo objects or an empty list
 */
inline fun <reified T> QuerySnapshot.toListOf(): List<T> = try {
    val list = mutableListOf<T>()
    documents.forEach { document ->
        val model = document.toObject(T::class.java)
        model?.let { list.add(it) }
    }
    list
} catch (e: Exception) {
    listOf()
}
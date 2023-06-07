package com.mediamarktsaturn.techsummit23

import org.apache.hc.core5.http.HttpResponse
import java.io.*
import java.util.Scanner

fun Serializable.toByteArray(): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    var objectOutputStream: ObjectOutputStream? = null
    try {
        objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(this)
        objectOutputStream.flush()
        val result = byteArrayOutputStream.toByteArray()
        return result
    } finally {
        if (objectOutputStream != null) {
            objectOutputStream.close()
        }
        byteArrayOutputStream.close()
    }
}

fun handleResponse(response: HttpResponse?): String {
    if (response == null) {
        throw IllegalStateException("Response is null")
    }

    val code: Int = response.code
    if (code == 200 || code == 201) {
        return response.reasonPhrase
    }

    if (code >= 400 && code <= 499) {
        throw IllegalStateException("Server responses with client error")
    }

    if (code >= 500 && code <= 599) {
        throw IllegalStateException("Server responses with server error")
    }

    throw IllegalStateException("Receive unexpected response code " + code)
}

private const val DELIMITER = "[^a-zA-Z'äöü]+"

fun File.countWord(): MutableMap<String, Int> {
    var scanner: Scanner? = null
    try {
        scanner = Scanner(this)
        scanner.useDelimiter(DELIMITER)
        val wordCount: MutableMap<String, Int> = mutableMapOf<String, Int>()
        while (scanner.hasNext()) {
            val word: String = scanner.next()
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 1)
            } else {
                val currentCount = wordCount.get(word)
                if (currentCount != null) {
                    wordCount.put(word, currentCount + 1)
                }
            }
        }
        return wordCount
    } catch (e: IOException) {
        throw IllegalArgumentException("Unable to read the current file ${this.name}!", e)
    } finally {
        if (scanner != null) {
            scanner.close()
        }
    }
}

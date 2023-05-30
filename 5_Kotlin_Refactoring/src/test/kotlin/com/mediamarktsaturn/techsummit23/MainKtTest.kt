package com.mediamarktsaturn.techsummit23

import org.apache.hc.core5.http.HttpResponse
import org.apache.hc.core5.http.message.BasicHttpResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.Serializable

class Event(val name: String, val year: Int) : Serializable

class MainKtTest {

    @Test
    fun `test Serializable_toByteArray`() {
        val actual = Event(name = "tech-summit", year = 2023).toByteArray()

        byteArrayOf(
            -84, -19, 0, 5, 115, 114, 0, 39, 99, 111, 109, 46, 109, 101, 100,
            105, 97, 109, 97, 114, 107, 116, 115, 97, 116, 117, 114, 110, 46,
            116, 101, 99, 104, 115, 117, 109, 109, 105, 116, 50, 51, 46, 69,
            118, 101, 110, 116, -115, -65, -60, 5, -85, -82, -99, -71, 2, 0,
            2, 73, 0, 4, 121, 101, 97, 114, 76, 0, 4, 110, 97, 109, 101, 116,
            0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116,
            114, 105, 110, 103, 59, 120, 112, 0, 0, 7, -25, 116, 0, 11, 116,
            101, 99, 104, 45, 115, 117, 109, 109, 105, 116,
        ).forEachIndexed { index, expected ->
            assertEquals(expected, actual[index])
        }
    }

    @Test
    fun `test handleResponse success cases`() {
        assertEquals(
            "OK",
            handleResponse(BasicHttpResponse(200, "OK")),
        )

        assertEquals(
            "CREATED",
            handleResponse(BasicHttpResponse(201, "CREATED")),
        )
    }

    @ParameterizedTest
    @MethodSource("provideDataForTestHandleResponseErrorCases")
    fun `test handleResponse error cases`(
        response: HttpResponse?,
        expectedMessage: String,
    ) {
        val thrown = assertThrows(IllegalStateException::class.java) { handleResponse(response) }
        assertEquals(expectedMessage, thrown.message)
    }

    companion object {
        @JvmStatic
        private fun provideDataForTestHandleResponseErrorCases() = listOf(
            Arguments.of(null, "Response is null"),
            Arguments.of(BasicHttpResponse(400, "Bad Request"), "Server responses with client error"),
            Arguments.of(BasicHttpResponse(451, "Unavailable For Legal Reasons"), "Server responses with client error"),
            Arguments.of(BasicHttpResponse(500, "Internal Server Error"), "Server responses with server error"),
            Arguments.of(BasicHttpResponse(511, "Network Authentication Required"), "Server responses with server error"),
            Arguments.of(BasicHttpResponse(202, "Accepted"), "Receive unexpected response code 202"),
        )
    }
}

package com.example.stockapplication.data.api

import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit

const val REQUEST_ERROR = "The request was not connected to the service"
const val WEB_SOCKET_ERROR = "WebSocket was not started"

class HttpService {

    private val httpClient: OkHttpClient

    private var _webSocket: WebSocket? = null
    private val webSocket get() = checkNotNull(_webSocket) { WEB_SOCKET_ERROR }

    private var _request: Request? = null
    private val request get() = checkNotNull(_request) { REQUEST_ERROR }

    init {
        httpClient = generateClient()
    }

    fun connectRequest(request: Request) {
        _request = request
    }

    fun connectWithWebSocket(listener: ResponseListener) {
        httpClient.newWebSocket(request, generateWebSocketListener(listener))
    }

    fun sendMessageToWebSocket(request: String) {
        webSocket.send(request)
    }

    private fun generateClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    private fun generateWebSocketListener(listener: ResponseListener) = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            if (response.code == 101) {
                _webSocket = webSocket
                listener.onConnectSuccess()
            } else {
                listener.onConnectError()
            }
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            listener.onMessage(text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            listener.onMessage(bytes)
        }
    }

    interface ResponseListener {
        fun onConnectSuccess()
        fun onConnectError()
        fun onClose()
        fun onMessage(response: String)
        fun onMessage(bytes: ByteString)
    }
}
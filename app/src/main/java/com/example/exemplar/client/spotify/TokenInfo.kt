package com.example.exemplar.client.spotify

import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    val access_token: String,
    val expires_in: Int,
    val token_type: String
)
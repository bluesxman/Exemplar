package com.example.exemplar.client.spotify

val clientId: String
    get() = System.getenv()["CLIENT_ID"]!!

val clientSecret: String
    get() = System.getenv()["CLIENT_SECRET"]!!


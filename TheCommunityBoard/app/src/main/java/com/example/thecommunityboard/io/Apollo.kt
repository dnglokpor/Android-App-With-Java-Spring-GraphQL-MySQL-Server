package com.example.thecommunityboard.io

import com.apollographql.apollo3.ApolloClient

private const val TAG = "Apollo"
private const val SERVER_ADDRESS = "http://10.200.4.114:8080/thecommunityboard"

val apolloClient = ApolloClient.Builder()
    .serverUrl(SERVER_ADDRESS)
    .build()

class Apollo {
}
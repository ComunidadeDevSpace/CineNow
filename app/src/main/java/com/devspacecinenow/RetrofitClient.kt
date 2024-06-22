package com.devspacecinenow

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val httpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzM2M2YzFjNmJlMTdkMWNlNjk5ZjQ5ZTNlMTVkNmQ1OCIsIm5iZiI6MTcxOTA2NTE1MS4zNzQwNTYsInN1YiI6IjY2NzZkODRmMTE5NmZmOGU0ZmFhNDRkYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-yA9ZmQq2koN5ca8YORQZ49PJKof1xfM5PmO_xJradM"

            clientBuilder.addInterceptor { chain ->
                val original: Request = chain.request()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }

            return clientBuilder.build()
        }

    val retrofitInstance: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}
package com.jairoqc.laboratorio3examen.network

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    suspend fun getTeachers(): Response<TeacherListResponse>
}
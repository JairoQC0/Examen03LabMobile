package com.jairoqc.laboratorio3examen.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jairoqc.laboratorio3examen.network.RetrofitClient
import com.jairoqc.laboratorio3examen.network.TeacherListResponse
import com.jairoqc.laboratorio3examen.network.TeacherResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class TeacherViewModel : ViewModel() {
    val teacherList = MutableLiveData<List<TeacherResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun fetchTeachers() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response: Response<TeacherListResponse> = RetrofitClient.apiService.getTeachers()
                if (response.isSuccessful) {
                    teacherList.value = response.body()?.teachers
                } else {
                    errorMessage.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Unknown Error"
            } finally {
                isLoading.value = false
            }
        }
    }
}

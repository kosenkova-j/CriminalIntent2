package com.example.criminalintent2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import java.util.UUID
import android.util.Log

class CrimeDetailViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.get() // Здесь мы получаем экземпляр репозитория
    private val crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> = crimeIdLiveData.switchMap { crimeId ->
        Log.d("CrimeDetailViewModel", "Получаем Crime с ID: $crimeId") // Логируем ID преступления
        crimeRepository.getCrime(crimeId)
    }

    fun loadCrime(crimeId: UUID) {
        Log.d("CrimeDetailViewModel", "Загружаем Crime с ID: $crimeId") // Логируем загрузку
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        Log.d("CrimeDetailViewModel", "Сохраняем Crime: $crime") // Логируем сохранение
        crimeRepository.updateCrime(crime)
    }
}

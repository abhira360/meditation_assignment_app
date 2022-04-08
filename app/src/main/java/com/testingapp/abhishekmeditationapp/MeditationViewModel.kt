package com.testingapp.abhishekmeditationapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeditationViewModel: ViewModel() {

    var imageLink =  MutableLiveData<String>()
    val musicLink = MutableLiveData<String>()

}
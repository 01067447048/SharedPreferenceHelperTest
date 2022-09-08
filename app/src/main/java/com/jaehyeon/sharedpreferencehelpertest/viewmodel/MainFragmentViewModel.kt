package com.jaehyeon.sharedpreferencehelpertest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaehyeon.sharedpreferencehelpertest.repository.SharedPreferenceHelper
import com.jaehyeon.sharedpreferencehelpertest.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/09/08.
 */
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val helper: SharedPreferenceHelper
): ViewModel() {

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> get() = _error

    private val _success = MutableLiveData<Event<Unit>>()
    val success: LiveData<Event<Unit>> get() = _success

    /**
     * SharedPreference 에 저장할 함수.
     * Float 형태가 아니면 터지기 때문에 try-catch 로 안정성 확보.
     */
    fun setWeightLocalStorage(weight: String) {
        try {
            helper.weight = weight.toFloat()
            _success.value = Event(Unit)
        } catch (t: Throwable) {
            Log.e(javaClass.simpleName, "setWeightLocalStorage: ${t.localizedMessage}", )
            _error.value = Event("실수 형태로 입력 해라...")
        }
    }

    /**
     * SharedPreference 에서 몸무게를 가져 오는 함수.
     */
    fun getWeight(): Float {
        return helper.weight
    }

    /**
     * Test 용도.
     * SharedPreference Clear
     */
    fun clearLocalStorage() {
        helper.clear()
    }
}
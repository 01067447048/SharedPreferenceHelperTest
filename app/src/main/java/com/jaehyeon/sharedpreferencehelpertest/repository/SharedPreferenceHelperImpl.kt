package com.jaehyeon.sharedpreferencehelpertest.repository

import android.content.Context
import androidx.core.content.edit
import com.jaehyeon.sharedpreferencehelpertest.R
import javax.inject.Inject


/**
 * Created by Jaehyeon on 2022/09/08.
 */
class SharedPreferenceHelperImpl @Inject constructor(
    private val context: Context
): SharedPreferenceHelper {

    private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    override var weight: Float
        get() = sharedPreferences.getFloat(WEIGHT, 0.0f)
        @Synchronized
        set(value) {
            sharedPreferences.edit(false) {
                putFloat(WEIGHT, value)
            }
        }

    override fun clear() {
        sharedPreferences.edit {
            remove(WEIGHT).apply()
        }
    }


    companion object {
        const val WEIGHT = "sharedPreference_weight"
    }
}
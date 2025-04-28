package com.example.recommendedarch.common.model

import com.example.recommendedarch.R
import com.example.recommendedarch.common.entities.MyException
import com.example.recommendedarch.common.utils.Constants

open class BaseRepository {
    protected suspend fun executeAction(
        myException: MyException = MyException(Constants.EC_UNKNOWN, R.string.common_unknown_error),
        block: suspend () -> Unit
    ) {
        try {
            block()
        } catch (e: Exception) {
            throw myException
        }
    }
}
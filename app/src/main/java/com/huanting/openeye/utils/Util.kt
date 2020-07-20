package com.huanting.openeye.utils

import android.content.Context
import android.util.Log
import android.util.TypedValue
import com.huanting.openeye.App
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.hours
import kotlin.time.minutes

class Util {

    companion object {

        fun formatData(time: Long?): String {
            if (time != null) {
                val sf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                return sf.format(time)
            }

            return ""
        }

        fun formatData(time: Long?, pattern: String): String {
            if (time != null) {
                val sf = SimpleDateFormat(pattern)
                return sf.format(time)
            }
            return ""
        }

        fun getWindowMetrics(mContext: Context): IntArray {
            val datas = IntArray(2)
            val dm = mContext.resources.displayMetrics
            datas[0] = dm.widthPixels
            datas[1] = dm.heightPixels
            return datas
        }

        fun dpTopx(dp: Int): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), App.getInstance()!!.applicationContext.resources.displayMetrics).toInt()
        }
    }
}
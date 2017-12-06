package com.linyuzai.mvp.util

import android.content.Context
import android.graphics.Point
import android.view.WindowManager


/**
 * Created by linyuzai on 2017/12/6.
 * @author linyuzai
 */
object ScreenUtil {
    var point: Point? = null

    fun getWidth(context: Context): Int {
        if (point == null)
            measureScreen(context)
        return point!!.x
    }

    fun getHeight(context: Context): Int {
        if (point == null)
            measureScreen(context)
        return point!!.y
    }

    private fun measureScreen(context: Context) {
        point = Point()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(point)
    }
}
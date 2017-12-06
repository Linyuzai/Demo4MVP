package com.linyuzai.mvp.v7

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewTreeObserver
import com.linyuzai.mvp.base.UIComponent
import com.linyuzai.mvp.keyboard.KeyboardHelper
import com.linyuzai.mvp.keyboard.KeyboardState
import com.linyuzai.mvp.keyboard.SoftKeyboardListener
import com.linyuzai.mvp.theme.MicroTheme
import com.linyuzai.mvp.theme.MicroThemeChangeable
import com.linyuzai.mvp.theme.MicroThemeManager
import com.linyuzai.mvp.util.ScreenUtil

/**
 * Created by linyuzai on 2017/10/26.
 * The base activity to extends
 * @author linyuzai
 */
abstract class BaseV7Activity : AppCompatActivity(), UIComponent, MicroThemeChangeable, KeyboardHelper, SoftKeyboardListener {
    private val mKeyboardState = KeyboardState(false, 0)
    private var mKeyboardStateChangeListener: ViewTreeObserver.OnGlobalLayoutListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout()
        val theme = MicroThemeManager.getCurrentTheme()
        if (theme != null)
            onThemeChange(theme)
        view(savedInstanceState)
        MicroThemeManager.register(this)
    }

    /**
     * layout the ui
     */
    abstract fun layout()

    override fun view(savedInstanceState: Bundle?) {}

    override fun onThemeChange(theme: MicroTheme) {}

    override fun onDestroy() {
        MicroThemeManager.unregister(this)
        disableKeyboardObserver()
        super.onDestroy()
    }

    override fun enableKeyboardObserver() {
        mKeyboardStateChangeListener = ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            window.decorView.getWindowVisibleDisplayFrame(r)
            //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
            val heightDifference = ScreenUtil.getHeight(this@BaseV7Activity) - (r.bottom - r.top)
            val isKeyboardShowing = heightDifference > ScreenUtil.getHeight(this@BaseV7Activity) / 3

            //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
            if (mKeyboardState.isShow && !isKeyboardShowing || !mKeyboardState.isShow && isKeyboardShowing) {
                mKeyboardState.isShow = isKeyboardShowing
                onKeyboardStateChanged(mKeyboardState.isShow, heightDifference)
            }
        }
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener(mKeyboardStateChangeListener)
    }

    override fun disableKeyboardObserver() {
        if (mKeyboardStateChangeListener != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(mKeyboardStateChangeListener)
            } else {
                window.decorView.viewTreeObserver.removeGlobalOnLayoutListener(mKeyboardStateChangeListener)
            }
            mKeyboardStateChangeListener = null
        }
    }

    override fun onKeyboardStateChanged(isShow: Boolean, height: Int) {}
}
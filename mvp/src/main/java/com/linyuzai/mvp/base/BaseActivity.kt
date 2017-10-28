package com.linyuzai.mvp.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linyuzai.mvp.theme.MicroTheme
import com.linyuzai.mvp.theme.MicroThemeChangeable
import com.linyuzai.mvp.theme.MicroThemeManager

/**
 * Created by linyuzai on 2017/10/26.
 * The base activity to extends
 * @author linyuzai
 */
abstract class BaseActivity : AppCompatActivity(), UIComponent, MicroThemeChangeable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout()
        onThemeChange(MicroThemeManager.getCurrentTheme())
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
        super.onDestroy()
    }
}
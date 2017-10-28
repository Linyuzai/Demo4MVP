package com.linyuzai.mvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linyuzai.mvp.theme.MicroTheme
import com.linyuzai.mvp.theme.MicroThemeChangeable
import com.linyuzai.mvp.theme.MicroThemeManager

/**
 * Created by linyuzai on 2017/10/26.
 * The base fragment to extends
 * @author linyuzai
 */
abstract class BaseFragment : Fragment(), UIComponent, MicroThemeChangeable {

    private var isViewCreated: Boolean = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layout(inflater, container)
        onThemeChange(MicroThemeManager.getCurrentTheme())
        view(savedInstanceState)
        isViewCreated = true
        MicroThemeManager.register(this)
        return view
    }

    override fun onDestroyView() {
        MicroThemeManager.unregister(this)
        isViewCreated = false
        super.onDestroyView()
    }

    /**
     * layout the ui
     */
    abstract fun layout(inflater: LayoutInflater?, container: ViewGroup?): View

    override fun view(savedInstanceState: Bundle?) {}

    override fun onThemeChange(theme: MicroTheme) {}

    fun isViewCreated(): Boolean = isViewCreated
}
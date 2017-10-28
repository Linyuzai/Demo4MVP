package com.linyuzai.mvp.base

import android.os.Bundle

/**
 * Created by linyuzai on 2017/10/27.
 * @author linyuzai
 */
interface UIComponent {
    /**
     * init the data on view and etc.
     */
    fun view(savedInstanceState: Bundle?)
}
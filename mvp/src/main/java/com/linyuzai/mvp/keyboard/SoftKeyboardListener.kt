package com.linyuzai.mvp.keyboard

/**
 * Created by linyuzai on 2017/12/6.
 * @author linyuzai
 */
interface SoftKeyboardListener {
    fun onKeyboardStateChanged(isShow: Boolean, height: Int)
}
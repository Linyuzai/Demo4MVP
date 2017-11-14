package com.linyuzai.mvp.base

import com.linyuzai.mvp.IPresenter
import com.linyuzai.mvp.IView

/**
 * Created by linyuzai on 2017/11/14.
 * @author linyuzai
 */
abstract class Presenter<out T : IView<IPresenter<*>>> constructor(private val view: T) : IPresenter<T> {
    override fun view(): T = view
}
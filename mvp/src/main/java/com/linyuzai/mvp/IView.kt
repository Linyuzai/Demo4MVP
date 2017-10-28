package com.linyuzai.mvp

/**
 * Created by linyuzai on 2017/8/24 0024.
 * @see IPresenter use this interface to update activity
 * @author linyuzai
 */
interface IView<out T : IPresenter> {
    /**
     * Inject a presenter
     * @return Presenter instance
     */
    fun bindPresenter(): T

    /**
     * Get the presenter instance
     * @return Presenter
     */
    fun presenter(): T

    fun showMessage(msg: String)
}
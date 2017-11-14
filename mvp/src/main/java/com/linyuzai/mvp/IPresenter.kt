package com.linyuzai.mvp

/**
 * Created by linyuzai on 2017/8/24 0024.
 * activity use this interface to processing data
 * @author linyuzai
 */
interface IPresenter<out T> {
    fun view(): T
}
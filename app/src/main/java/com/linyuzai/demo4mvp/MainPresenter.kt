package com.linyuzai.demo4mvp

import com.linyuzai.mvp.base.Presenter

/**
 * Created by linyuzai on 2017/11/14.
 */
class MainPresenter constructor(view: MainContract.View) : Presenter<MainContract.View>(view), MainContract.Presenter {

    fun test(){

    }
}
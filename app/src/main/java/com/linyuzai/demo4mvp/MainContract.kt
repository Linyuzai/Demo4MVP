package com.linyuzai.demo4mvp

import com.linyuzai.mvp.IPresenter
import com.linyuzai.mvp.IView

/**
 * Created by linyuzai on 2017/11/14.
 */
object MainContract {
    interface View : IView<Presenter>

    interface Presenter : IPresenter<View>
    //interface Presenter : IPresenter
}
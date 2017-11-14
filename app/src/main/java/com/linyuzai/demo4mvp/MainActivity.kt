package com.linyuzai.demo4mvp

import com.linyuzai.mvp.v7.MvpV7Activity

class MainActivity : MvpV7Activity<MainContract.Presenter>(), MainContract.View {

    override fun layout() {
        setContentView(R.layout.activity_main)
    }

    override fun bindPresenter(): MainContract.Presenter = MainPresenter(this)
}


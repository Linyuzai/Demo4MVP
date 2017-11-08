package com.linyuzai.mvp.v7

import android.os.Bundle
import android.widget.Toast
import com.linyuzai.mvp.IPresenter
import com.linyuzai.mvp.IView

/**
 * Created by linyuzai on 2017/8/24 0024.
 * The mvp activity to extends
 * @author linyuzai
 */
abstract class MvpV7Activity<out T : IPresenter> : BaseV7Activity(), IView<T> {

    private lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = bindPresenter()
        super.onCreate(savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
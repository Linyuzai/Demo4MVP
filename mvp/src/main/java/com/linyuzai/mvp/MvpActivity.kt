package com.linyuzai.mvp

import android.os.Bundle
import android.widget.Toast
import com.linyuzai.mvp.base.BaseActivity

/**
 * Created by linyuzai on 2017/8/24 0024.
 * The mvp activity to extends
 * @author linyuzai
 */
abstract class MvpActivity<out T : IPresenter> : BaseActivity(), IView<T> {

    private lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = bindPresenter()
        super.onCreate(savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
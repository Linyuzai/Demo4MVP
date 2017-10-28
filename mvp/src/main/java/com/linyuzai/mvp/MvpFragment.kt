package com.linyuzai.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.linyuzai.mvp.base.BaseFragment

/**
 * Created by linyuzai on 2017/10/9.
 * The mvp fragment to extends
 * @author linyuzai
 */
abstract class MvpFragment<out T : IPresenter> : BaseFragment(), IView<T> {

    private lateinit var presenter: T

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = bindPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}
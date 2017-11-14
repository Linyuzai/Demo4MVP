package com.linyuzai.mvp.v4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.linyuzai.mvp.IPresenter
import com.linyuzai.mvp.IView

/**
 * Created by linyuzai on 2017/10/9.
 * The mvp fragment to extends
 * @author linyuzai
 */
abstract class MvpV4Fragment<out T : IPresenter<IView<*>>> : BaseV4Fragment(), IView<T> {

    private lateinit var presenter: T

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = bindPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}
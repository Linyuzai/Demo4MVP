package com.linyuzai.mvp.rx2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.linyuzai.mvp.rx2.base.BaseRx2Fragment
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.Observable

/**
 * Created by linyuzai on 2017/10/9.
 * The mvp fragment to extends
 * @author linyuzai
 */
abstract class MvpRx2Fragment<out T : IRx2Presenter<IRx2View<*>>> : BaseRx2Fragment(), IRx2View<T> {

    private lateinit var presenter: T

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = bindPresenter()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()

    override fun <T> bind(o: Observable<T>): Observable<T> = o.bindToLifecycle(this)

    @SuppressWarnings("unchecked")
    override fun <T> bind(o: Observable<T>, e: ActivityEvent): Observable<T> = o.apply {
        if (this@MvpRx2Fragment.activity is LifecycleProvider<*>)
            bindUntilEvent(this@MvpRx2Fragment.activity as LifecycleProvider<ActivityEvent>, e)
    }

    override fun <T> bind(o: Observable<T>, e: FragmentEvent): Observable<T> =
            o.bindUntilEvent(this, e)
}
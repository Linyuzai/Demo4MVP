package com.linyuzai.mvp.rx2.v7

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import com.linyuzai.mvp.rx2.IRx2Presenter
import com.linyuzai.mvp.rx2.IRx2View
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.Observable

/**
 * Created by linyuzai on 2017/8/24 0024.
 * The mvp activity to extends
 * @author linyuzai
 */
abstract class MvpRx2V7Activity<out T : IRx2Presenter<IRx2View<*>>> : BaseRx2V7Activity(), IRx2View<T> {

    private lateinit var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = bindPresenter()
        super.onCreate(savedInstanceState)
    }

    override fun presenter(): T = presenter

    override fun showMessage(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    override fun <T> bind(o: Observable<T>): Observable<T> = o.bindToLifecycle(this)

    override fun <T> bind(o: Observable<T>, e: ActivityEvent): Observable<T> = o.bindUntilEvent(this, e)

    @SuppressWarnings("unchecked")
    override fun <T> bind(o: Observable<T>, e: FragmentEvent): Observable<T> = o.apply {
        supportFragmentManager.fragments.filter { it is LifecycleProvider<*> }.forEach {
            bindUntilEvent(it as LifecycleProvider<FragmentEvent>, e)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            fragmentManager.fragments.filter { it is LifecycleProvider<*> }.forEach {
                bindUntilEvent(it as LifecycleProvider<FragmentEvent>, e)
            }
        }
    }
}
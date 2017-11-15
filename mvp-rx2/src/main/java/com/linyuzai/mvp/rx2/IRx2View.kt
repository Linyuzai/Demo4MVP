package com.linyuzai.mvp.rx2

import com.linyuzai.mvp.IView
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observable

/**
 * Created by linyuzai on 2017/11/14.
 * @author linyuzai
 */
interface IRx2View<out T> : IView<T> {
    fun <T> bind(o: Observable<T>): Observable<T>

    fun <T> bind(o: Observable<T>, e: ActivityEvent): Observable<T>

    fun <T> bind(o: Observable<T>, e: FragmentEvent): Observable<T>
}
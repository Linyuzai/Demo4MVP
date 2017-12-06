package com.linyuzai.mvp.rx2.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.CheckResult
import com.linyuzai.mvp.base.BaseActivity
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by linyuzai on 2017/10/26.
 * The base activity to extends
 * @author linyuzai
 */
abstract class BaseRx2Activity : BaseActivity(), LifecycleProvider<ActivityEvent> {
    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()
    @CheckResult
    override fun lifecycle(): Observable<ActivityEvent> = this.lifecycleSubject.hide()

    @CheckResult
    override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> =
            RxLifecycle.bindUntilEvent(this.lifecycleSubject, event)

    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> =
            RxLifecycleAndroid.bindActivity(this.lifecycleSubject)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.lifecycleSubject.onNext(ActivityEvent.CREATE)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        this.lifecycleSubject.onNext(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        this.lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    @CallSuper
    override fun onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE)
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }
}
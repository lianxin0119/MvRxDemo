package space.lianxin.comm.ui.activity

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewId

/**
 * ===========================================
 * MvRx的Activity的基类。
 *
 * @author: lianxin
 * @date: 2021/2/25 13:03
 * ===========================================
 */
abstract class ComMvRxAvtivity<T : ViewBinding> : ComActivity<T>(), MvRxView {

    protected val epoxyController by lazy { buildEpoxyController() }
    private val mvrxViewIdProperty = MvRxViewId()
    final override val mvrxViewId: String by mvrxViewIdProperty

    override fun onCreate(savedInstanceState: Bundle?) {
        mvrxViewIdProperty.restoreFrom(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewIdProperty.saveTo(outState)
    }

    /**
     * Fragments should override the subscriptionLifecycle owner so that subscriptions made after onCreate
     * are properly disposed as fragments are moved from/to the backstack.
     */
    override val subscriptionLifecycleOwner: LifecycleOwner
        get() = this

    override fun onStart() {
        super.onStart()
        // This ensures that invalidate() is called for static screens that don't
        // subscribe to a ViewModel.
        postInvalidate()
    }

    override fun invalidate() {
        epoxyController.requestModelBuild()
    }

    /** 订阅viewmodel中的值,当任何值发生改变时都去会刷新UI。若只想订阅部分值需自己实现。 */
    protected fun subscribeVM(vararg viewModels: BaseMvRxViewModel<*>) {
        viewModels.forEach {
            it.subscribe(owner = this, subscriber = {
                postInvalidate()
            })
        }
    }

    abstract fun buildEpoxyController(): AsyncEpoxyController

}
package space.lianxin.comm.ui.activity

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ActivityUtils
import space.lianxin.base.extention.setStatusBarBlackText
import space.lianxin.base.ui.activity.BaseActivity
import space.lianxin.comm.ui.dialog.ActionLoadingDialog
import space.lianxin.comm.ui.dialog.LoadingDialog

/**
 * ===========================================
 * 项目通用的activity。增加弹窗相关。在使用mvrx时，一个界面如果存在多输入的，会导致
 * 显示异常，可以通过继承该类来布局，其他情况建议继承[ComMvRxAvtivity]的子类。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:09
 * ===========================================
 */
abstract class ComActivity<T : ViewBinding> : BaseActivity<T>() {

    private var loadingDialog: LoadingDialog? = null
    private var actionLoadingDialog: ActionLoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (window != null) {
            window.navigationBarColor = Color.WHITE
        }
        savedInstanceState?.let {
            ActivityUtils.getActivityList().clear()
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        hideInputMethod()
    }

    override fun initStatus() {
        setStatusBarBlackText()
    }

    /** loading是否正在显示。 */
    fun isLoadingShowing(): Boolean {
        return (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING) != null
                || supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING_ACTION) != null)
    }

    open fun showLoading(style: (LoadingDialog.() -> Unit)? = null) {
        if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING) != null
            || supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING_ACTION) != null
        ) {
            //loading已经在显示中
            return
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance()
        }
        style?.let {
            loadingDialog?.apply(it)
        }
        try {
            loadingDialog?.show(supportFragmentManager, TAG_FRAGMENT_LOADING)
        } catch (e: Exception) {
        }
    }

    open fun showActionLoading(
        tips: String? = null,
        style: (ActionLoadingDialog.() -> Unit)? = null
    ) {
        if (supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING_ACTION) != null ||
            supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_LOADING) != null
        ) {
            // loading已经在显示中
            return
        }
        if (actionLoadingDialog == null) {
            actionLoadingDialog = ActionLoadingDialog.newInstance()
        }
        actionLoadingDialog?.setTips(tips)
        style?.let {
            actionLoadingDialog?.apply(it)
        }
        try {
            actionLoadingDialog?.show(supportFragmentManager, TAG_FRAGMENT_LOADING_ACTION)
        } catch (e: Exception) {
        }
    }

    /** 收起键盘 */
    fun hideInputMethod() {
        currentFocus?.let {
            val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {
        private const val TAG_FRAGMENT_LOADING = "LoadingDialog"
        private const val TAG_FRAGMENT_LOADING_ACTION = "ActionLoadingDialog"
    }

}
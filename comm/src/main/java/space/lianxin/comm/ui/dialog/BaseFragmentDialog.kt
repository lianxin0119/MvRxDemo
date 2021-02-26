package space.lianxin.comm.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import space.lianxin.comm.extention.dpInt
import space.lianxin.comm.extention.isDoubleClick
import space.lianxin.comm.extention.setBackgroundAlpha

/**
 * ===========================================
 * Dialog的基类。
 *
 * @author: lianxin
 * @date: 2021/2/25 12:19
 * ===========================================
 */
abstract class BaseFragmentDialog : androidx.fragment.app.DialogFragment() {

    var mWidth = ViewGroup.LayoutParams.WRAP_CONTENT
    var mHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    var mGravity = Gravity.CENTER
    var mOffsetX = 0
    var mOffsetY = 0
    var mAnimation: Int? = null
    var touchOutside: Boolean = true
    var mSoftInputMode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
    var lowerBackground = false // 是否降级背景，例如图片预览的时候不可以降级（设置Activity的透明度）
    var canPressBackDismiss: Boolean = true
    lateinit var mContext: Context

    // listener
    private var viewLoadedListener: ((View) -> Unit)? = null
    private var showListener: (() -> Unit)? = null
    private var disListener: (() -> Unit)? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected abstract fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle()
        val view = setView(inflater, container, savedInstanceState)
        viewLoadedListener?.invoke(view)
        return view
    }

    /**** 降低背景的Window等级 ****/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (lowerBackground) mContext.setBackgroundAlpha(0.2F)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        if (lowerBackground) mContext.setBackgroundAlpha(1F)
        super.onDestroyView()
    }

    /** 防止同时弹出两个dialog */
    override fun show(manager: FragmentManager, tag: String?) {
        if (isDoubleClick() || activity?.isFinishing == true) {
            return
        }
        showListener?.invoke()
        setBooleanField("mDismissed", false)
        setBooleanField("mShownByMe", true)
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    private fun setBooleanField(fieldName: String, value: Boolean) {
        try {
            val field = DialogFragment::class.java.getDeclaredField(fieldName)
            field.isAccessible = true
            field.set(this, value)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        disListener?.invoke()
        super.onDismiss(dialog)
    }

    /** show回调 */
    fun onShow(listener: () -> Unit) {
        showListener = listener
    }

    /** dismiss回调 */
    fun onDismiss(listener: () -> Unit) {
        disListener = listener
    }

    /**
     * 布局加载完成监听事件
     * 用于 获取布局中的 view
     */
    fun onViewLoaded(listener: (View) -> Unit) {
        viewLoadedListener = listener
    }

    /** 设置统一样式 */
    private fun setStyle() {
        val window = dialog?.window // 获取Window
        dialog?.requestWindowFeature(STYLE_NO_TITLE) // 无标题
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 透明背景
        if (lowerBackground) window?.setDimAmount(0F) // 去除 dialog 弹出的阴影
        dialog?.setCanceledOnTouchOutside(touchOutside)
        window!!.decorView.setPadding(0, 0, 0, 0) // 设置宽高
        val wlp = window.attributes
        wlp.width = mWidth
        wlp.height = mHeight
        // 设置对齐方式
        wlp.gravity = mGravity
        // 设置偏移量
        wlp.x = mOffsetX.dpInt()
        wlp.y = mOffsetY.dpInt()
        wlp.softInputMode = mSoftInputMode
        // 设置动画
        mAnimation?.also { window.setWindowAnimations(it) }
        window.attributes = wlp
        dialog?.setOnKeyListener { _, keyCode, _ ->
            if (!canPressBackDismiss && keyCode == KeyEvent.KEYCODE_BACK) {
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

}
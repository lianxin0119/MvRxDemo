package space.lianxin.comm.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import space.lianxin.comm.R

/**
 * ===========================================
 * 项目通用的dialog。
 *
 * @author: lianxin
 * @date: 2021/2/25 11:16
 * ===========================================
 */
class LoadingDialog : DialogFragment() {

    var touchOutside: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setStyle()
        return inflater.inflate(R.layout.comm_dialog_loading, container, false)
    }

    /**
     * 设置统一样式
     */
    private fun setStyle() {
        // 获取Window
        val window = dialog?.window
        // 无标题
        dialog?.requestWindowFeature(STYLE_NO_TITLE)
        // 透明背景
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setDimAmount(0F) // 去除 dialog 弹出的阴影
        dialog?.setCanceledOnTouchOutside(touchOutside)
        // 设置宽高
        window!!.decorView.setPadding(0, 0, 0, 0)
        val wlp = window.attributes
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT
        // 设置对齐方式
        wlp.gravity = Gravity.CENTER
        window.attributes = wlp
    }

    companion object {
        fun newInstance(): LoadingDialog = LoadingDialog()
    }
}

// DSL
inline fun loadingDialog(
    fragmentManager: FragmentManager,
    dsl: LoadingDialog.() -> Unit
): LoadingDialog {
    val dialog = LoadingDialog.newInstance()
    dialog.apply(dsl)
    dialog.show(fragmentManager, "LoadingDialog")
    return dialog
}
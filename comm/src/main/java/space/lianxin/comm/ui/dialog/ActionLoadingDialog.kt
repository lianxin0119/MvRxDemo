package space.lianxin.comm.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import space.lianxin.comm.R
import space.lianxin.comm.extention.showTextNotNull

/**
 * ===========================================
 * 操作类型的loading默认点击外部不消失，按返回键不消失.
 *
 * @author: lianxin
 * @date: 2021/2/25 12:17
 * ===========================================
 */
class ActionLoadingDialog : BaseFragmentDialog() {

    private var loadingTv: TextView? = null
    private var tips: String? = null

    init {
        touchOutside = false
        lowerBackground = true
        isCancelable = false
    }

    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.comm_dialog_loading_action, container, false)
        loadingTv = view.findViewById(R.id.loadingTv)
        loadingTv?.showTextNotNull(tips)
        return view
    }

    fun setTips(text: String?) {
        tips = text
        loadingTv?.showTextNotNull(tips)
    }

    companion object {
        fun newInstance(): ActionLoadingDialog = ActionLoadingDialog()
    }
}

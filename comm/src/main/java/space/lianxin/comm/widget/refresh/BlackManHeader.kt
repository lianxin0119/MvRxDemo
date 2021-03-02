package space.lianxin.comm.widget.refresh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import space.lianxin.comm.R

/**
 * ===========================================
 * 小黑人动画的下拉刷新的头.
 *
 * @author: lianxin
 * @date: 2021/3/2 10:21
 * ===========================================
 */
class BlackManHeader(context: Context) : RefreshHeader {

    private val rootView: View =
        View.inflate(context, R.layout.comm_layout_refresh_header_black_man, null)

    private var headerLav: LottieAnimationView? = null
    private var headerTv: TextView? = null

    init {
        headerLav = rootView.findViewById(R.id.headerLav)
        headerTv = rootView.findViewById(R.id.headerTv)
    }

    override fun getSpinnerStyle(): SpinnerStyle = SpinnerStyle.Translate

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        headerLav?.cancelAnimation()
        headerLav?.progress = 0f
        return 200
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {}

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {}

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {}

    override fun getView(): View {
        return rootView
    }

    override fun setPrimaryColors(vararg colors: Int) {}

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        headerLav?.playAnimation()
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None -> {
                headerTv?.text = "下拉刷新"
            }
            RefreshState.PullDownToRefresh -> {
                headerTv?.text = "下拉刷新"
            }
            RefreshState.ReleaseToRefresh -> {
                headerTv?.text = "释放刷新"
            }
            RefreshState.Refreshing -> {
                headerTv?.text = ""
            }
            else -> {
            }
        }
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun isSupportHorizontalDrag() = false
}
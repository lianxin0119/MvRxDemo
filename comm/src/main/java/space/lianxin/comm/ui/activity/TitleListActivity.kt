package space.lianxin.comm.ui.activity

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import space.lianxin.base.extention.click
import space.lianxin.comm.databinding.ActivityTitleListBinding

/**
 * ===========================================
 * 标题列表结构的Activity的基类。
 *
 * @author: lianxin
 * @date: 2021/2/25 14:00
 * ===========================================
 */
abstract class TitleListActivity : ComMvRxAvtivity<ActivityTitleListBinding>() {

    override fun inflateBinding() = ActivityTitleListBinding.inflate(layoutInflater)

    @CallSuper
    override fun initView() {
        initHeader()
        initTitleBar(
            binding.titleBar.titleTv,
            binding.titleBar.rightTv,
            binding.titleBar.rightIv,
            binding.titleBar.backIv
        )
        initRecyclerView(binding.recyclerView)
        binding.recyclerView.setController(epoxyController)
    }

    override fun initData() {}

    /** 初始化titleBar */
    protected open fun initTitleBar(
        title: TextView,
        rightTv: TextView,
        rightIv: ImageView,
        backIv: ImageView
    ) {
    }

    /** 初始化列表相关信息 */
    protected open fun initRecyclerView(recyclerView: RecyclerView) {}

    /** 设置头部返回键按钮点击事件 */
    fun setHeaderBackClick(event: () -> Unit) {
        binding.titleBar.backIv.click(event)
    }

    /** 初始化头部信息 */
    private fun initHeader() {
        setHeaderBackClick { finish() }
        binding.refreshLayout.setEnableRefresh(true)
        binding.refreshLayout.setEnableLoadMore(false)
    }

    /** 关联到item的滑动(可以监听item滑动的距离和百分比等。) */
    protected fun attachToItemScroll() {
        EpoxyVisibilityTracker().attach(binding.recyclerView)
    }

}
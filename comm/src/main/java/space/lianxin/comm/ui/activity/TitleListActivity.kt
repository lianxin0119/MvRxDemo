package space.lianxin.comm.ui.activity

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import space.lianxin.base.extention.click
import space.lianxin.base.extention.setStatusColor
import space.lianxin.comm.databinding.CommActivityTitleListBinding

/**
 * ===========================================
 * 标题列表结构的Activity的基类。当不需要使用标题结构时可以直接调用titleBar
 * 的[setVisible]方法。
 *
 * @author: lianxin
 * @date: 2021/2/25 14:00
 * ===========================================
 */
abstract class TitleListActivity : ComMvRxAvtivity<CommActivityTitleListBinding>(),
    OnRefreshListener {

    override fun inflateBinding() = CommActivityTitleListBinding.inflate(layoutInflater)

    @CallSuper
    override fun initView() {
        setStatusColor(Color.TRANSPARENT)
        initHeader()
        initTitleBar(
            binding.titleBar.titleTv,
            binding.titleBar.rightTv,
            binding.titleBar.rightIv,
            binding.titleBar.backIv
        )
        binding.refreshLayout.setOnRefreshListener(this)
        initRecyclerView(binding.refreshLayout, binding.recyclerView)
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

    /** 初始化刷新和列表view相关 */
    protected open fun initRecyclerView(
        refreshView: SmartRefreshLayout,
        recyclerView: RecyclerView
    ) {
    }

    /** 设置头部返回键按钮点击事件 */
    fun setHeaderBackClick(event: () -> Unit) {
        binding.titleBar.backIv.click(event)
    }

    /** 初始化头部信息 */
    private fun initHeader() {
        binding.titleBar.titleTv.text = "返回"
        setHeaderBackClick { finish() }
        binding.refreshLayout.setEnableRefresh(true)
        binding.refreshLayout.setEnableLoadMore(false)
    }

    /** 关联到item的滑动(可以监听item滑动的距离和百分比等。) */
    protected fun attachToItemScroll() {
        EpoxyVisibilityTracker().attach(binding.recyclerView)
    }

    protected fun setTitle(title: String) {
        binding.titleBar.titleTv.text = title
        binding.titleBar.centerTitleTxtView.text = title
    }

    protected open fun finishRefresh() {
        binding.refreshLayout.finishRefresh()
    }

}
package space.lianxin.comm.ui.activity

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.AsyncEpoxyController
import space.lianxin.base.extention.click
import space.lianxin.base.extention.setStatusColor
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.databinding.CommActivityTitleBinding

/**
 * ===========================================
 * 标题内容结构的Activity基类。
 *
 * @author: lianxin
 * @date: 2021/2/19 12:39
 * ===========================================
 */
abstract class TitleActivity<T : ViewBinding> : ComMvRxAvtivity<CommActivityTitleBinding>() {

    override fun inflateBinding() = CommActivityTitleBinding.inflate(layoutInflater)

    lateinit var cBinding: T

    abstract fun initContentBinding(): T

    @CallSuper
    override fun initView() {
        setStatusColor(Color.TRANSPARENT)
        cBinding = initContentBinding()
        binding.root.addView(cBinding.root)
        initHeader()
        initTitleBar(
            binding.titleBar.titleTv,
            binding.titleBar.rightTv,
            binding.titleBar.rightIv,
            binding.titleBar.backIv
        )
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

    /** 设置头部返回键按钮点击事件 */
    fun setHeaderBackClick(event: () -> Unit) {
        binding.titleBar.backIv.click(event)
    }

    /** 初始化头部信息 */
    private fun initHeader() {
        binding.titleBar.titleTv.text = "返回"
        setHeaderBackClick { finish() }
    }

    protected fun setTitle(title: String) {
        binding.titleBar.titleTv.text = title
        binding.titleBar.centerTitleTxtView.text = title
    }

    override fun buildEpoxyController() = simpleController { }

}

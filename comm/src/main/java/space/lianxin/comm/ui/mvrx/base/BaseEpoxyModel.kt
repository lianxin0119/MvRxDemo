package space.lianxin.comm.ui.mvrx.base

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelWithHolder
import space.lianxin.comm.extention.dpInt
import space.lianxin.comm.extention.orZero

/**
 * ===========================================
 * model的基类。在使用[@EpoxyModelClass]注解的时候指定的layout布局名称，应当具有唯一性。
 * 否者布局所在的module被当做library库时，相同命名的布局文件会覆盖当前layout。出现layout错误。
 * 此外，对应的layout布局也应当指定[viewBindingIgnore="true"]
 *
 * @author: lianxin
 * @date: 2021/2/28 10:50
 * ===========================================
 */
abstract class BaseEpoxyModel<T : BaseEpoxyHolder> : EpoxyModelWithHolder<T>() {

    // 设置外边距
    @EpoxyAttribute
    var topMarginDp: Float? = null
    @EpoxyAttribute
    var leftMarginDp: Float? = null
    @EpoxyAttribute
    var rightMarginDp: Float? = null
    @EpoxyAttribute
    var bottomMarginDp: Float? = null

    // 设置内边距
    @EpoxyAttribute
    var topPaddingDp: Float? = null
    @EpoxyAttribute
    var leftPaddingDp: Float? = null
    @EpoxyAttribute
    var rightPaddingDp: Float? = null
    @EpoxyAttribute
    var bottomPaddingDp: Float? = null

    // 设置宽高
    @EpoxyAttribute
    var heightDp: Float? = null
    @EpoxyAttribute
    var widthDp: Float? = null

    // 设置背景
    @EpoxyAttribute
    @ColorInt
    var bgColor: Int? = null
    @EpoxyAttribute
    @ColorRes
    var bgColorRes: Int? = null
    @EpoxyAttribute
    var bgDrawable: Drawable? = null
    @EpoxyAttribute
    @DrawableRes
    var bgDrawableRes: Int? = null

    override fun bind(holder: T) {
        super.bind(holder)
        setSize(holder.itemView)
        setBgColor(holder.itemView)
        setBgDrawable(holder.itemView)
        setMargin(holder.itemView)
        setPadding(holder.itemView)
        onBind(holder)
        onBind(holder.itemView)
    }

    private fun setSize(view: View) {
        if (heightDp == null && widthDp == null) {
            return
        }
        val layoutParams = view.layoutParams
        heightDp?.let { layoutParams.height = it.dpInt() }
        widthDp?.let { layoutParams.width = it.dpInt() }
        view.layoutParams = layoutParams
    }

    private fun setBgColor(view: View) {
        bgColor?.let { view.setBackgroundColor(it) }
        bgColorRes?.let { view.setBackgroundResource(it) }
    }

    private fun setBgDrawable(view: View) {
        bgDrawable?.let { view.background = it }
        bgDrawableRes?.let { view.setBackgroundResource(it) }
    }

    private fun setPadding(view: View) {
        if (leftPaddingDp == null
            && topPaddingDp == null
            && rightPaddingDp == null
            && bottomPaddingDp == null
        ) {
            return
        }
        view.setPadding(
            leftPaddingDp.orZero().dpInt(),
            topPaddingDp.orZero().dpInt(),
            rightPaddingDp.orZero().dpInt(),
            bottomPaddingDp.orZero().dpInt()
        )
    }

    private fun setMargin(view: View) {
        if (leftMarginDp == null
            && topMarginDp == null
            && rightMarginDp == null
            && bottomMarginDp == null
        ) {
            return
        }
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val marginLayoutParams = (view.layoutParams as ViewGroup.MarginLayoutParams)
            val left = if (leftMarginDp == null) {
                marginLayoutParams.leftMargin
            } else {
                leftMarginDp.orZero().dpInt()
            }
            val top = if (topMarginDp == null) {
                marginLayoutParams.topMargin
            } else {
                topMarginDp.orZero().dpInt()
            }
            val right = if (rightMarginDp == null) {
                marginLayoutParams.rightMargin
            } else {
                rightMarginDp.orZero().dpInt()
            }
            val bottom = if (bottomMarginDp == null) {
                marginLayoutParams.bottomMargin
            } else {
                bottomMarginDp.orZero().dpInt()
            }
            marginLayoutParams.setMargins(left, top, right, bottom)
            view.layoutParams = marginLayoutParams
        }
    }

    override fun onFailedToRecycleView(holder: T): Boolean {
        return super.onFailedToRecycleView(holder)
    }

    /** 需要使用自定义Holder时重写该方法 */
    open fun onBind(holder: T) {}

    /**
     * 不需要使用自定义Holder是只是使用ItemView重写该方法
     * 使用anko的话简化步骤，参数view就是holder的ItemView
     */
    open fun onBind(itemView: View) {}
}
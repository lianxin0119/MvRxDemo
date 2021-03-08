package space.lianxin.comm.ui.mvrx.item

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.blankj.utilcode.util.SizeUtils
import kotlinx.android.synthetic.main.comm_mvrx_item_textimg.view.*
import space.lianxin.base.extention.click
import space.lianxin.base.extention.getResColor
import space.lianxin.base.extention.visible
import space.lianxin.comm.R
import space.lianxin.comm.extention.load
import space.lianxin.comm.extention.pressEffectBgColor
import space.lianxin.comm.extention.visibilityWith
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyHolder
import space.lianxin.comm.ui.mvrx.base.BaseEpoxyModel
import java.util.regex.Pattern

/**
 * ===========================================
 * 这个item 总共包含 左边图标，左边文字标题，右边文字描述，右边图标
 *
 * @author: lianxin
 * @date: 2021/3/7 18:04
 * ===========================================
 */
@EpoxyModelClass
abstract class TextImgItem : BaseEpoxyModel<BaseEpoxyHolder>() {

    override fun getDefaultLayout() = R.layout.comm_mvrx_item_textimg

    @EpoxyAttribute
    var title: CharSequence? = null

    @EpoxyAttribute
    var imgUrl: String? = null

    @EpoxyAttribute
    var imgRes: Int? = null

    @EpoxyAttribute
    var desc: CharSequence? = null

    /** 高亮文字 */
    @EpoxyAttribute
    var highlightText: CharSequence? = null

    @EpoxyAttribute
    var showRightIc: Boolean = false

    /**
     * title 的默认样式
     * 14sp FF333333 marginStart 16dp
     */
    @EpoxyAttribute
    var titleStyle: (TextView.() -> Unit)? = null

    /**
     * desc 的默认样式
     * 12sp FF999999
     */
    @EpoxyAttribute
    var descStyle: (TextView.() -> Unit)? = null

    /**
     * 默认大小 25dp 25dp marginStart 16dp
     */
    @EpoxyAttribute
    var imgStyle: (ImageView.() -> Unit)? = null

    @EpoxyAttribute
    var rightImgStyle: (ImageView.() -> Unit)? = null

    @EpoxyAttribute
    var onClick: (() -> Unit)? = null

    @EpoxyAttribute
    var minHeight: Float? = null

    @EpoxyAttribute
    var pressEffectBgColorEnable: Boolean = true

    override fun onBind(itemView: View) {
        setTitle(itemView.mvrxItemName)
        setImg(itemView.mvrxIcon)
        setDesc(itemView.mvrxDesc)
        setRightImg(itemView.mvrxNext)
        if (minHeight != null) {
            minHeight?.let {
                if (itemView is ConstraintLayout) {
                    itemView.minHeight = SizeUtils.dp2px(it)
                }
            }
        }
        itemView.click {
            onClick?.invoke()
        }
        if (pressEffectBgColorEnable) {
            itemView.pressEffectBgColor()
        }
    }

    private fun setTitle(textView: TextView) {
        title?.let {
            if (highlightText != null) {
                val spannable = SpannableString(it)
                val patten = Pattern.quote(highlightText?.toString() ?: "")
                val matcher =
                    Pattern.compile(patten, Pattern.CASE_INSENSITIVE).matcher(spannable)
                val colorSpan =
                    ForegroundColorSpan(textView.context.getResColor(R.color.keyLightColor))
                while (matcher.find()) {
                    spannable.setSpan(
                        colorSpan,
                        matcher.start(),
                        matcher.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                textView.text = spannable
            } else {
                textView.text = it
            }
        }
        titleStyle?.let {
            textView.apply(it)
        }
    }

    private fun setImg(imageView: ImageView) {
        imgUrl?.let {
            imageView.load(it)
            imageView.visible()
        }
        imgRes?.let {
            imageView.setBackgroundResource(it)
            imageView.visible()
        }
        imgStyle?.let {
            imageView.apply(it)
        }
    }

    private fun setDesc(textView: TextView) {
        desc?.let {
            textView.text = it
            textView.visible()
        }
        descStyle?.let {
            textView.apply(it)
        }
    }

    private fun setRightImg(imageView: ImageView) {
        imgRes?.let {
            imageView.setImageResource(it)
        }
        imgUrl?.let {
            imageView.load(it)
        }
        rightImgStyle?.let {
            imageView.apply(it)
        }
        imageView.visibilityWith { showRightIc }
    }

}
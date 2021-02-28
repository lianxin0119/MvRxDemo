package space.lianxin.comm.ui.mvrx.base

import android.view.View
import com.airbnb.epoxy.EpoxyHolder

/**
 * ===========================================
 * BaseEpoxyHolder
 *
 * @author: lianxin
 * @date: 2021/2/28 10:49
 * ===========================================
 */
open class BaseEpoxyHolder : EpoxyHolder() {

    lateinit var itemView: View

    override fun bindView(itemView: View) {
        this.itemView = itemView
    }

}
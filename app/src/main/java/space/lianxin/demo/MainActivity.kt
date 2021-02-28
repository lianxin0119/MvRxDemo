package space.lianxin.demo

import android.graphics.Color
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.ui.activity.TitleListActivity
import space.lianxin.comm.ui.mvrx.item.noMoreItem

class MainActivity : TitleListActivity() {

    override fun initView() {
        super.initView()
        attachToItemScroll()
    }

    override fun buildEpoxyController() = simpleController {
        noMoreItem {
            id(1)
            bgColor(Color.parseColor("#44110000"))
            topMarginDp(12f)
            heightDp(42f)
        }
        noMoreItem {
            id(2)
            bgColor(Color.parseColor("#44220000"))
            topMarginDp(12f)
            heightDp(48f)
        }
        noMoreItem {
            id(3)
            bgColor(Color.parseColor("#44330000"))
            topMarginDp(12f)
            heightDp(40f)
        }
        noMoreItem {
            id(4)
            bgColor(Color.parseColor("#44440000"))
            topMarginDp(12f)
            heightDp(41f)
        }
        noMoreItem {
            id(5)
            bgColor(Color.parseColor("#44550000"))
            topMarginDp(12f)
            heightDp(49f)
        }
        noMoreItem {
            id(6)
            bgColor(Color.parseColor("#44660000"))
            topMarginDp(12f)
            heightDp(40f)
        }
        noMoreItem {
            id(7)
            bgColor(Color.parseColor("#44770000"))
            topMarginDp(12f)
            heightDp(44f)
        }
        noMoreItem {
            id(8)
            bgColor(Color.parseColor("#44880000"))
            topMarginDp(12f)
            heightDp(40f)
        }
        noMoreItem {
            id(9)
            bgColor(Color.parseColor("#44990000"))
            topMarginDp(12f)
            heightDp(46f)
        }
    }

}
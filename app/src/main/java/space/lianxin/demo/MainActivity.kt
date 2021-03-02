package space.lianxin.demo

import android.widget.ImageView
import android.widget.TextView
import space.lianxin.base.extention.click
import space.lianxin.base.extention.visible
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.ui.activity.TitleListActivity

class MainActivity : TitleListActivity() {

    override fun initView() {
        super.initView()
        epoxyController.setFilterDuplicates(true)
        attachToItemScroll()
    }

    override fun buildEpoxyController() = simpleController {
    }

    override fun initTitleBar(
        title: TextView,
        rightTv: TextView,
        rightIv: ImageView,
        backIv: ImageView
    ) {
        rightIv.visible()
        rightIv.click {
//            showActionLoading()  // 强loading
            showLoading()
        }
    }

}
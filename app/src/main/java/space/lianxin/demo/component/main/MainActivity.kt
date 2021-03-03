package space.lianxin.demo.component.main

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.mvrx.Loading
import space.lianxin.base.extention.click
import space.lianxin.base.extention.visible
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.ui.activity.TitleListActivity
import space.lianxin.demo.component.login.viewmodel.LoginViweModel

class MainActivity : TitleListActivity() {

    private val viewModel by lazy { LoginViweModel() }

    override fun initView() {
        super.initView()
        epoxyController.setFilterDuplicates(true)
        attachToItemScroll()
    }

    override fun buildEpoxyController() = simpleController(viewModel) { state ->
        if (state.loginRequest is Loading) {
            return@simpleController
        }
    }

    override fun initTitleBar(
        title: TextView,
        rightTv: TextView,
        rightIv: ImageView,
        backIv: ImageView
    ) {
        rightIv.visible()
        rightIv.click {}
    }

}
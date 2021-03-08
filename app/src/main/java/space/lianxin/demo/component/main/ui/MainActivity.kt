package space.lianxin.demo.component.main.ui

import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ActivityUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import space.lianxin.base.extention.click
import space.lianxin.base.extention.visible
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.constants.arouter.RouterParam
import space.lianxin.comm.ui.activity.TitleListActivity
import space.lianxin.comm.ui.mvrx.item.textImgItem
import space.lianxin.comm.utils.NavigateUtil
import space.lianxin.demo.R
import space.lianxin.demo.component.main.viewmodel.MainViewModel

@Route(path = RouterConstants.App.MainActivity)
class MainActivity : TitleListActivity() {

    private val viewModel by lazy { MainViewModel() }

    @JvmField
    @Autowired(name = RouterParam.App.Title)
    var titleHeadr: String? = null

    override fun onCreateBefore() {
        super.onCreateBefore()
        ARouter.getInstance().inject(this)
    }

    override fun initView() {
        super.initView()
        epoxyController.setFilterDuplicates(true)
        subscribeVM(viewModel)
        attachToItemScroll()
    }

    override fun initData() {
        super.initData()
        viewModel.loadData()
    }

    override fun onResume() {
        super.onResume()
        ActivityUtils.finishOtherActivities(this::class.java, false)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
    }

    override fun buildEpoxyController() = simpleController(viewModel) { state ->
        if (state.data.isNullOrBlank()) {
            return@simpleController
        }
        textImgItem {
            id("line1")
            imgRes(R.mipmap.ic_launcher)
            title("去Oa")
            onClick {
                NavigateUtil.navigation(RouterConstants.Oa.MainActivity)
            }
        }
        textImgItem {
            id("line2")
            imgRes(R.mipmap.ic_launcher)
            title("去Ring")
            onClick {
                NavigateUtil.navigation(RouterConstants.Ring.MainActivity)
            }
        }

    }

    override fun initTitleBar(
        title: TextView,
        rightTv: TextView,
        rightIv: ImageView,
        backIv: ImageView
    ) {
        titleHeadr?.let {
            title.text = it
        }
        rightIv.visible()
        rightIv.click {}
    }

}
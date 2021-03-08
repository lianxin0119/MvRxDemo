package space.lianxin.oa

import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smartrefresh.layout.api.RefreshLayout
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.ui.activity.TitleListActivity

@Route(path = RouterConstants.Oa.MainActivity)
class OaMainActivity : TitleListActivity() {

    override fun onRefresh(refreshLayout: RefreshLayout) {
    }

    override fun buildEpoxyController() = simpleController {

    }

}

package space.lianxin.oa

import com.alibaba.android.arouter.facade.annotation.Route
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.ui.activity.TitleListActivity

@Route(path = RouterConstants.Oa.MainActivity)
class OaMainActivity : TitleListActivity() {

    override fun buildEpoxyController() = simpleController {

    }

}

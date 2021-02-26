package space.lianxin.comm.ui.activity

import com.airbnb.epoxy.AsyncEpoxyController
import space.lianxin.comm.databinding.ActivityTitleBinding

/**
 * ===========================================
 *
 *
 * @author: lianxin
 * @date: 2021/2/19 12:39
 * ===========================================
 */
class TitleActivity : ComMvRxAvtivity<ActivityTitleBinding>() {

    override fun inflateBinding() = ActivityTitleBinding.inflate(layoutInflater)

    override fun buildEpoxyController(): AsyncEpoxyController {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        TODO("Not yet implemented")
    }
}

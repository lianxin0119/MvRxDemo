package space.lianxin.comm.ui.activity

import androidx.annotation.CallSuper
import space.lianxin.comm.databinding.ActivityTitleListBinding

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/2/25 14:00
 * ===========================================
 */
abstract class TitleListActivity : ComMvRxAvtivity<ActivityTitleListBinding>() {

    override fun inflateBinding() = ActivityTitleListBinding.inflate(layoutInflater)


    @CallSuper
    override fun initView() {

        binding.recyclerView.setController(epoxyController)
    }

    override fun initData() {}


}
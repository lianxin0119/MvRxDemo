package space.lianxin.ring.component.main.ui

import com.airbnb.mvrx.Loading
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smartrefresh.layout.api.RefreshLayout
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.ui.activity.TitleListActivity
import space.lianxin.comm.ui.mvrx.item.lineItem
import space.lianxin.comm.ui.mvrx.item.loadMoreItem
import space.lianxin.comm.ui.mvrx.item.noMoreItem
import space.lianxin.comm.ui.mvrx.item.textImgItem
import space.lianxin.ring.component.main.viewmodel.RingMainState
import space.lianxin.ring.component.main.viewmodel.RingMainViewModel

@Route(path = RouterConstants.Ring.MainActivity)
class RingMainActivity : TitleListActivity() {

    private val viewModel by lazy { RingMainViewModel() }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.refresh()
    }

    override fun initView() {
        super.initView()
        attachToItemScroll()
    }

    override fun initData() {
        super.initData()
        viewModel.selectSubscribe(
            this,
            RingMainState::request
        ) {
            if (it !is Loading) {
                binding.refreshLayout
                finishRefresh()
            }
        }
        subscribeVM(viewModel)
        viewModel.refresh()
    }

    override fun buildEpoxyController() = simpleController(viewModel) { state ->
        if (state.request is Loading) {
            return@simpleController
        }
        state.listData.forEach {
            textImgItem {
                id("deviceName_${it.approach_name}")
                title(it.approach_name)
                showRightIc(true)
            }
            lineItem {
                id("line_${it.approach_name}")
                rightMarginDp(12f)
                leftMarginDp(12f)
            }
        }
        if (state.hasMore) {
            loadMoreItem {
                id("loadmore")
                onLoadMore {
                    viewModel.loadMore()
                }
            }
        } else {
            noMoreItem { id("nomore") }
        }
    }

}

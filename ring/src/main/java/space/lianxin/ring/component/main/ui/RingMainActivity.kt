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
import space.lianxin.comm.utils.NavigateUtil
import space.lianxin.ring.component.main.viewmodel.RingMainState
import space.lianxin.ring.component.main.viewmodel.RingMainViewModel
import space.lianxin.ring.repostory.bean.request.RDeviceName

@Route(path = RouterConstants.Ring.MainActivity)
class RingMainActivity : TitleListActivity() {

    private var body: RDeviceName = RDeviceName()

    private val viewModel by lazy { RingMainViewModel() }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        body = body.copy(page = 0)
        viewModel.refresh(body)
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
                postInvalidate()
            }
        }
        viewModel.refresh(body)
    }

    override fun buildEpoxyController() = simpleController(viewModel) { state ->
        state.listData.forEachIndexed { index, it ->
            textImgItem {
                id("deviceName_$index")
                title(it.approach_name)
                showRightIc(true)
                onClick {
                    NavigateUtil.navigation(RouterConstants.Ring.RingDetailActivity)
                }
            }
            lineItem {
                id("line_$index")
                rightMarginDp(12f)
                leftMarginDp(12f)
            }
        }
        if (state.hasMore) {
            loadMoreItem {
                id("loadmore")
                onLoadMore {
                    body = body.copy(page = body.page + 1)
                    viewModel.loadMore(body)
                }
            }
        } else {
            noMoreItem { id("nomore") }
        }
    }

}

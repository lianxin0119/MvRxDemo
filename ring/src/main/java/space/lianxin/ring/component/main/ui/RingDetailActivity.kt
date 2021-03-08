package space.lianxin.ring.component.main.ui

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import space.lianxin.base.ui.controller.simpleController
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.extention.dpInt
import space.lianxin.comm.ui.activity.TitleListActivity
import space.lianxin.comm.ui.mvrx.item.lineItem
import space.lianxin.comm.ui.mvrx.item.loadMoreItem
import space.lianxin.comm.ui.mvrx.item.nameValueItem
import space.lianxin.comm.ui.mvrx.item.noMoreItem
import space.lianxin.ring.component.main.viewmodel.RingDetailViewModel

/**
 * ===========================================
 * @author: lianxin
 * @date: 2021/3/8 19:17
 * ===========================================
 */
@Route(path = RouterConstants.Ring.RingDetailActivity)
class RingDetailActivity : TitleListActivity() {

    private val viewmodel by lazy { RingDetailViewModel() }

    override fun buildEpoxyController() = simpleController(viewmodel) { state ->

        nameValueItem {
            id(1)
            topMarginDp(10f)
            name("name1:")
            value(state.data?.arg1)
        }
        lineItem {
            id("line1")
        }
        nameValueItem {
            id(2)
            name("name2:")
            value(state.data?.arg2)
        }
        lineItem {
            id("line2")
        }
        loadMoreItem {
            id("loadmore")
        }
        noMoreItem {
            id("nomore")
        }

    }

    override fun initView() {
        super.initView()
        binding.root.setBackgroundColor(Color.parseColor("#11FF0000"))
    }

    override fun initData() {
        super.initData()
        subscribeVM(viewmodel)
        viewmodel.loadData()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
    }

    override fun initRecyclerView(refreshView: SmartRefreshLayout, recyclerView: RecyclerView) {
        super.initRecyclerView(refreshView, recyclerView)
        recyclerView.setPadding(12.dpInt(), 0, 12.dpInt(), 0)
        refreshView.setEnableRefresh(false)
    }

}
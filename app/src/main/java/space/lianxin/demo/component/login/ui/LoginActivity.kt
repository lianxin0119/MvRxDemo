package space.lianxin.demo.component.login.ui

import android.text.Editable
import com.airbnb.mvrx.UniqueOnly
import com.alibaba.android.arouter.facade.annotation.Route
import space.lianxin.base.extention.click
import space.lianxin.base.extention.gone
import space.lianxin.comm.constants.arouter.RouterConstants
import space.lianxin.comm.constants.arouter.RouterStart
import space.lianxin.comm.ui.activity.TitleActivity
import space.lianxin.demo.component.login.viewmodel.LoginState
import space.lianxin.demo.component.login.viewmodel.LoginViweModel
import space.lianxin.demo.databinding.ActivityLoginBinding

/**
 * ===========================================
 * 登录的Activity
 *
 * @author: lianxin
 * @date: 2021/3/6 13:39
 * ===========================================
 */
@Route(path = RouterConstants.App.LoginActivity)
class LoginActivity : TitleActivity<ActivityLoginBinding>() {

    override fun initContentBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun initData() {
        super.initData()
        viewModel.asyncSubscribe(
            this,
            LoginState::loginRequest,
            deliveryMode = UniqueOnly(mvrxViewId),
            onFail = {
                cBinding.result.text = it.message
            },
            onSuccess = {
                cBinding.result.text = it.toString()
                RouterStart.App.startMainActivity("从Login来")
            }
        )
    }

    private val viewModel by lazy { LoginViweModel() }

    override fun initView() {
        super.initView()
        binding.titleBar.root.gone()
        cBinding.num.text = Editable.Factory.getInstance().newEditable("15680663779")
        cBinding.pwd.text = Editable.Factory.getInstance().newEditable("1556")
        cBinding.submit.click {
            viewModel.login(cBinding.num.text.toString(), cBinding.pwd.text.toString())
        }
    }


}
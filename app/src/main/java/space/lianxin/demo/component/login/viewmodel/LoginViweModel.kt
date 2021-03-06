package space.lianxin.demo.component.login.viewmodel

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import org.kodein.di.generic.instance
import space.lianxin.base.mvvm.MvRxViewModel
import space.lianxin.comm.repository.OauthRepository
import space.lianxin.comm.repository.bean.request.LoginBean
import space.lianxin.comm.repository.bean.result.LoginResultBean
import space.lianxin.demo.application.App

data class LoginState(
    val loginRequest: Async<LoginResultBean> = Uninitialized
) : MvRxState

/**
 * ===========================================
 * 登录相关的ViewModel。
 *
 * @author: lianxin
 * @date: 2021/3/3 13:52
 * ===========================================
 */
class LoginViweModel(
    instate: LoginState = LoginState()
) : MvRxViewModel<LoginState>(instate) {

    private val oauthRepo by App.INSTANCE.kodein.instance<OauthRepository>()

    fun login(number: String, sms: String) {
        withState {
            if (it.loginRequest is Loading) {
                return@withState
            }
            val bean = LoginBean(number, sms, "")
            oauthRepo.phoneLogin(bean).execute { req ->
                copy(loginRequest = req)
            }
        }
    }

}

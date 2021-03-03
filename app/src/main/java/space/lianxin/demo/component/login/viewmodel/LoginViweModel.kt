package space.lianxin.demo.component.login.viewmodel

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import org.kodein.di.generic.instance
import space.lianxin.base.mvvm.MvRxViewModel
import space.lianxin.demo.application.App
import space.lianxin.demo.repository.UserRepository

data class LoginState(
    val loginRequest: Async<Boolean> = Uninitialized
) : MvRxState

/**
 * ===========================================
 * 登录相关的ViewModel。
 * @author: lianxin
 * @date: 2021/3/3 13:52
 * ===========================================
 */
class LoginViweModel(
    instate: LoginState = LoginState()
) : MvRxViewModel<LoginState>(instate) {

    private val userRepo by App.INSTANCE.kodein.instance<UserRepository>()

    fun login() {
        userRepo.login().execute {
            copy(loginRequest = it)
        }
    }

}

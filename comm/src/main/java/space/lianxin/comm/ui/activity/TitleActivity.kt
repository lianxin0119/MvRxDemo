package space.lianxin.comm.ui.activity

import space.lianxin.comm.databinding.ActivityTitleBinding

/**
 * ===========================================
 * 标题内容结构的Activity基类。
 *
 * @author: lianxin
 * @date: 2021/2/19 12:39
 * ===========================================
 */
abstract class TitleActivity : ComMvRxAvtivity<ActivityTitleBinding>() {

    override fun inflateBinding() = ActivityTitleBinding.inflate(layoutInflater)

}

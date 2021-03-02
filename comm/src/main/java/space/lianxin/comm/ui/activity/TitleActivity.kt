package space.lianxin.comm.ui.activity

import space.lianxin.comm.databinding.CommActivityTitleBinding

/**
 * ===========================================
 * 标题内容结构的Activity基类。
 *
 * @author: lianxin
 * @date: 2021/2/19 12:39
 * ===========================================
 */
abstract class TitleActivity : ComMvRxAvtivity<CommActivityTitleBinding>() {

    override fun inflateBinding() = CommActivityTitleBinding.inflate(layoutInflater)

}

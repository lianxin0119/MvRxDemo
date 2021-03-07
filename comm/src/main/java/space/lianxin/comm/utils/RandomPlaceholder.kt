package space.lianxin.comm.utils

import space.lianxin.comm.R
import kotlin.random.Random

/**
 * ================================================
 * 随机占位图工具类.
 *
 * Created by JessYan on 22/06/2016
 * ================================================
 */
object RandomPlaceholder {

    private val placeholderResIdList = mutableListOf(
        R.drawable.comm_img_placeholder_ffbcc6cc,
        R.drawable.comm_img_placeholder_ffc6d1c0,
        R.drawable.comm_placeholder_ffc7c9d6,
        R.drawable.comm_img_placeholder_ffc9becf,
        R.drawable.comm_img_placeholder_ffc9e2f0,
        R.drawable.comm_img_placeholder_ffcad1db,
        R.drawable.comm_img_placeholder_ffcad2e0,
        R.drawable.comm_img_placeholder_ffcad8e8,
        R.drawable.comm_img_placeholder_ffcadee3,
        R.drawable.comm_img_placeholder_ffccd9e3,
        R.drawable.comm_img_placeholder_ffccded2,
        R.drawable.comm_img_placeholder_ffcce3da,
        R.drawable.comm_img_placeholder_ffcfcabe,
        R.drawable.comm_img_placeholder_ffcfd4e6,
        R.drawable.comm_img_placeholder_ffd9d3eb,
        R.drawable.comm_img_placeholder_ffddd3eb,
        R.drawable.comm_img_placeholder_ffe6cfde,
        R.drawable.comm_img_placeholder_ffe6e6cf
    )

    private val random = Random(placeholderResIdList.size)

    /** 获取随机的占位图资源ID */
    fun getPlaceHolder(): Int {
        return placeholderResIdList[random.nextInt(placeholderResIdList.size)]
    }

}
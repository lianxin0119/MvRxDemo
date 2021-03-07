package space.lianxin.comm.constants.arouter

/**
 * ===========================================
 * ARouter常量。
 *
 * @author: lianxin
 * @date: 2021/3/2 11:58
 * ===========================================
 */
object RouterConstants {

    object App {
        private const val Group = "/App/"

        const val MainActivity = "${Group}MainActivity"
        const val LoginActivity = "${Group}LoginActivity"
    }


    /** OA模块 */
    object Oa {
        private const val Group = "/Oa/"

        const val MainActivity = "${Group}OaMainActivity"
    }

    /** 擂台赛模块 */
    object Ring {
        private const val Group = "/Ring/"

        const val MainActivity = "${Group}RingMainActivity"
    }


}
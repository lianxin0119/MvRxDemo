package space.lianxin.comm.repository

import com.tencent.mmkv.MMKV

/**
 * ===========================================
 * 缓存的数据仓库.
 *
 * @author: lianxin
 * @date: 2021/3/3 20:39
 * ===========================================
 */
object CacheRepository {

    /** 获取所有用户共用的MMKV */
    fun getCommMMKV(): MMKV {
        return MMKV.defaultMMKV()
    }

    /** 获取用户的MMKV */
    fun getUserMMKV(): MMKV {
        return MMKV.mmkvWithID(OauthRepository.getUserId().toString())
    }
}
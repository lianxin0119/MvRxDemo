package space.lianxin.comm.extention

import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import java.io.Serializable
import java.util.*

/**
 * ===========================================
 * ARouter相关扩展。
 *
 * @author: lianxin
 * @date: 2021/3/7 12:38
 * ===========================================
 */

/**  Int扩展 */
fun Postcard.putExtra(key: String, extra: Int) = withInt(key, extra)

/**  String扩展 */
fun Postcard.putExtra(key: String, extra: String) = withString(key, extra)

/**  Long扩展 */
fun Postcard.putExtra(key: String, extra: Long) = withLong(key, extra)

/**  Boolean扩展 */
fun Postcard.putExtra(key: String, extra: Boolean) = withBoolean(key, extra)

/**  Parcelable扩展 */
fun Postcard.putExtra(key: String, extra: Parcelable) = withParcelable(key, extra)

/**  Serializable扩展 */
fun Postcard.putExtra(key: String, extra: Serializable) = withSerializable(key, extra)

/** Object扩展 */
fun Postcard.putExtra(key: String, obj: Any) = withObject(key, obj)

/**  ParcelableArrayList扩展 */
fun <T : Parcelable> Postcard.putExtra(key: String, extra: ArrayList<T>) =
    withParcelableArrayList(key, extra)


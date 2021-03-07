package space.lianxin.comm.extention

import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import org.kodein.di.generic.instance
import space.lianxin.base.app.BaseApplication
import space.lianxin.base.net.imageloader.CornerType
import space.lianxin.base.net.imageloader.ImageConfig
import space.lianxin.base.net.imageloader.ImageLoader
import space.lianxin.base.net.imageloader.glide.GlideApp
import space.lianxin.comm.utils.RandomPlaceholder
import java.io.File

/**
 * ===========================================
 * ImageView相关扩展。
 *
 * @author: lianxin
 * @date: 2021/3/7 18:27
 * ===========================================
 */

/**
 * 加载url图片,默认CenterCrop和CrossFade效果
 */
fun ImageView.load(
    url: String?,
    onFail: ((GlideException) -> Unit)? = null
) {
    url?.let {
        val placeholderId = RandomPlaceholder.getPlaceHolder()
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(false)
            .url(it)
            .onFailedCallBack { glideException ->
                onFail?.invoke(glideException)
            }
            .errorSrc(placeholderId)//设置默认的占位图
            .placeholder(placeholderId)//设置默认的加载错误图
            .build()
        load(config)
    }
}

/** 加载本地资源,默认CenterCrop和CrossFade效果 */
fun ImageView.load(@DrawableRes resourceId: Int?) {
    resourceId?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(false)
            .resourceId(it)
            .build()
        load(config)
    }
}


/** 加载文件图片,默认CenterCrop和CrossFade效果 */
fun ImageView.load(file: File?) {
    GlideApp.with(BaseApplication.INSTANCE)
        .asBitmap()
        .load(file)
        .centerCrop()
        .transition(BitmapTransitionOptions.withCrossFade())
        .into(this)
}


/** 加载图片有占位图和加载错误图,默认CenterCrop和CrossFade效果 */
fun ImageView.load(url: String?, loadingResId: Int, errorResId: Int) {
    url?.let {
        val config: ImageConfig = ImageConfig.builder()
            .imageView(this)
            .url(it)
            .placeholder(loadingResId)
            .errorSrc(errorResId)
            .build()
        load(config)
    }
}

/** 根据配置加载图片 */
fun ImageView.load(config: ImageConfig) {
    if (config.url == null && config.uri == null && config.resourceId == null) {
        return
    }
    config.context = BaseApplication.INSTANCE
    config.imageView = this
    val loader: ImageLoader by BaseApplication.INSTANCE.kodein.instance<ImageLoader>()
    loader.loadImage(config)
}

/**
 * 加载高斯模糊图
 * @param radius 模糊度1-25
 * @param sampling 缩放，越大缩放比例越大
 */
fun ImageView.loadBlur(
    url: String?,
    radius: Int = 7,
    sampling: Int = 24,
    onSuccess: ((bitmap: Bitmap?) -> Unit)? = null
) {
    url?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .onRequestSuccessCallBack { bitmap ->
                onSuccess?.invoke(bitmap)
            }
            .blur(radius, sampling)
            .url(it)
            .build()
        load(config)
    }
}

/**
 * 加载高斯模糊图
 * @param radius 模糊度1-25
 * @param sampling 缩放，越大缩放比例越大
 */
fun ImageView.loadBlur(
    uri: Uri?,
    radius: Int = 12,
    sampling: Int = 7,
    successCallBack: ((Bitmap) -> Unit)? = null
) {
    uri?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .placeholder(RandomPlaceholder.getPlaceHolder())
            .onSuccess { bitmap ->
                bitmap?.let {
                    successCallBack?.invoke(it)
                }
            }
            .blur(radius, sampling)
            .uri(it)
            .build()
        load(config)
    }
}

/**
 * 加载高斯模糊图
 * @param radius 模糊度1-25
 * @param sampling 缩放，越大缩放比例越大
 */
fun ImageView.loadBlur(@DrawableRes resourceId: Int?, radius: Int = 12, sampling: Int = 7) {
    resourceId?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .blur(radius, sampling)
            .resourceId(it)
            .build()
        load(config)
    }
}

/**
 * 加载圆角图
 * @param radius 圆角dp
 * @param cornerType 圆角类型默认四个角都有
 * @param margin
 */
fun ImageView.loadCorner(
    uri: Uri?,
    radius: Int,
    cornerType: CornerType = CornerType.ALL,
    margin: Int = 0
) {
    uri?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .corner(radius.dpInt(), cornerType, margin)
            .uri(it)
            .build()
        load(config)
    }
}

/**
 * 加载圆角图
 * @param radius 圆角dp
 * @param cornerType 圆角类型默认四个角都有
 * @param margin
 */
fun ImageView.loadCorner(
    @DrawableRes resourceId: Int?,
    radius: Int,
    cornerType: CornerType = CornerType.ALL,
    margin: Int = 0
) {
    resourceId?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .corner(radius.dpInt(), cornerType, margin)
            .resourceId(it)
            .build()
        load(config)
    }
}

/** 加载圆形 */
fun ImageView.loadCircle(uri: Uri?) {
    uri?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .circleCrop()
            .uri(it)
            .build()
        load(config)
    }
}

/** 加载圆形 */
fun ImageView.loadCircle(@DrawableRes resourceId: Int?) {
    resourceId?.let {
        val config: ImageConfig = ImageConfig.builder()
            .useCrossFade(true)
            .circleCrop()
            .resourceId(it)
            .build()
        load(config)
    }
}

// 默认的图片加载SDL,可以配置默认的占位图等
fun imageLoadDefult(config: ImageConfig.Builder.() -> Unit) {
    val builder = ImageConfig.builder()
    builder.apply(config)
    builder.useCrossFade(false)
    val imageConfig = builder.build()
    imageConfig.imageView?.load(imageConfig)
}

fun ImageView.leftMargin(dp: Int) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.marginStart = dp.dpInt()
    }
    layoutParams = params
}

fun ImageView.bottomMargin(px: Int) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(
            params.leftMargin,
            params.topMargin,
            params.rightMargin,
            px
        )
    }
    layoutParams = params
}

fun ImageView.topMargin(px: Int) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.setMargins(
            params.leftMargin,
            px,
            params.rightMargin,
            params.bottomMargin
        )
    }
    layoutParams = params
}

fun cancelDisClickableStyles(vararg view: ImageView) {
    val iterator = view.iterator()
    while (iterator.hasNext()) {
        iterator.next().setColorFilter(0)
    }
}
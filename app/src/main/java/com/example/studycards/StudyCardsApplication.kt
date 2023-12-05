package com.example.studycards

import android.app.Application
import com.example.studycards.ui.utils.UnsplashSizingInterceptor
import coil.ImageLoader
import coil.ImageLoaderFactory


@Suppress("unused")
class StudyCardsApplication : Application(),ImageLoaderFactory {
    //Using coil.AsyncImage requires override of the default ImageLoader

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            // Ignore the Unsplash cache headers as they set `Cache-Control:must-revalidate` which
            // requires a network operation even if the image is cached locally.
            .respectCacheHeaders(false)
            .build()
    }
}

package org.illegaller.ratabb.hishoot2i.di

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationManagerCompat
import common.FileConstants
import common.custombitmap.AlphaPatternBitmap
import common.custombitmap.BadgeBitmapBuilder
import common.egl.MaxTexture
import common.egl.MaxTextureCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import imageloader.ImageLoader
import imageloader.coil.CoilImageLoaderImpl
import org.illegaller.ratabb.hishoot2i.BuildConfig
import org.illegaller.ratabb.hishoot2i.data.rx.AppScheduler
import org.illegaller.ratabb.hishoot2i.data.rx.SchedulerProvider
import template.TemplateFactoryManager
import template.TemplateFactoryManagerImpl
import javax.inject.Singleton

@Module(includes = [BindDataModule::class])
@InstallIn(SingletonComponent::class)
object ProvideDataModule {

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = AppScheduler

    @Provides
    @Singleton
    fun provideMaxTexture(): MaxTexture = MaxTextureCompat

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    ): ImageLoader = CoilImageLoaderImpl(context, BuildConfig.DEBUG)

    @Provides
    @Singleton
    fun provideFactoryManager(
        @ApplicationContext context: Context,
        fileConstants: FileConstants
    ): TemplateFactoryManager = TemplateFactoryManagerImpl(context, fileConstants)

    @Provides
    @Singleton
    fun provideNotificationManagerCompat(
        @ApplicationContext context: Context
    ): NotificationManagerCompat = NotificationManagerCompat.from(context)

    @Provides
    @Singleton
    fun providePackageManager(
        @ApplicationContext context: Context
    ): PackageManager = context.packageManager

    @Provides
    @Singleton
    fun provideAlphaPattern(
        @ApplicationContext context: Context
    ): AlphaPatternBitmap = AlphaPatternBitmap(context)

    @Provides
    @Singleton
    fun provideBadgeBuilder(
        @ApplicationContext context: Context
    ): BadgeBitmapBuilder = BadgeBitmapBuilder(context)
}

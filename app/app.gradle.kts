@file:Suppress("SpellCheckingInspection")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}
apply("$rootDir/buildsystem/signingRelease.gradle")
android {
    defaultConfig {
        applicationId = "org.illegaller.ratabb.hishoot2i"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
        val fileAuthority = "$applicationId.fileAuthority"
        val imageReceiverKey = "$applicationId.IMAGE_RECEIVER"
        buildConfigField("String", "FILE_AUTHORITY", "\"${fileAuthority}\"")
        buildConfigField("String", "IMAGE_RECEIVER", "\"${imageReceiverKey}\"")
        manifestPlaceholders = mapOf(
            "image_receiver_key" to imageReceiverKey,
            "file_authority" to fileAuthority
        )
        resConfigs("en", "xxhdpi") // TODO: ?
        dependenciesInfo.includeInApk = false // Disables dependency metadata when building APKs.
    }
    packagingOptions {
        exclude("kotlin/**")
        exclude("**/*.kotlin_metadata")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/*.version")
        exclude("META-INF/*.properties")
        exclude("androidsupportmultidexversion.txt")
        exclude("DebugProbesKt.bin")
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            isZipAlignEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-app.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    buildFeatures.viewBinding = true
    compileOptions.coreLibraryDesugaringEnabled = true //
}
kapt {
    arguments {
        arg("dagger.experimentalDaggerErrorMessages", "enabled") //
        arg("dagger.formatGeneratedSource", "disabled")
    }
}
dependencies {
    implementation(project(":common"))
    implementation(project(":imageloader"))
    implementation(project(":template"))
    implementation(project(":pref"))

    val kotlinVersion: String by rootProject
    implementation(kotlin("stdlib", version = kotlinVersion))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.10")
    val xMultiDexVersion: String by rootProject
    implementation("androidx.multidex:multidex:$xMultiDexVersion")

    val xAnnotationVersion: String by rootProject
    compileOnly("androidx.annotation:annotation:$xAnnotationVersion")
    val xCoreVersion: String by rootProject
    implementation("androidx.core:core:$xCoreVersion")
    implementation("androidx.core:core-ktx:$xCoreVersion") //
    implementation("androidx.browser:browser:1.2.0")
    implementation("androidx.appcompat:appcompat-resources:1.2.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.customview:customview:1.1.0")
    val xActivityVersion: String by rootProject
    implementation("androidx.activity:activity:$xActivityVersion")
    implementation("androidx.documentfile:documentfile:1.0.1")
    val xFragmentVersion: String by rootProject
    implementation("androidx.fragment:fragment:$xFragmentVersion")
    implementation("androidx.fragment:fragment-ktx:$xFragmentVersion")
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")
    val xNavigationVersion: String by rootProject
    implementation("androidx.navigation:navigation-fragment-ktx:$xNavigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$xNavigationVersion")
    implementation("androidx.navigation:navigation-common:$xNavigationVersion")
    implementation("androidx.navigation:navigation-common-ktx:$xNavigationVersion")
    implementation("androidx.navigation:navigation-runtime:$xNavigationVersion")
    implementation("androidx.navigation:navigation-runtime-ktx:$xNavigationVersion")
    val xLifeCycleVersion: String by rootProject
    implementation("androidx.lifecycle:lifecycle-common:$xLifeCycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel:$xLifeCycleVersion")

    val googleMaterial: String by rootProject
    implementation("com.google.android.material:material:$googleMaterial")

    implementation("javax.inject:javax.inject:1")
    val daggerVersion: String by rootProject
    implementation("com.google.dagger:dagger:$daggerVersion") //
    val daggerHiltVersion: String by rootProject
    implementation("com.google.dagger:hilt-core:$daggerHiltVersion")
    implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")
    // val xHiltVersion: String by rootProject
    // kapt("androidx.hilt:hilt-compiler:$xHiltVersion")

    val timberVersion: String by rootProject
    implementation("com.jakewharton.timber:timber:$timberVersion")
    implementation("cat.ereza:customactivityoncrash:2.3.0")
    val leakcanaryVersion: String by rootProject
    implementation("com.squareup.leakcanary:plumber-android:$leakcanaryVersion")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:$leakcanaryVersion")

    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.19")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("org.reactivestreams:reactive-streams:1.0.3")
}

apply("$rootDir/buildsystem/appVersioning.gradle")

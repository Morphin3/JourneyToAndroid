
#tinker multidex keep patterns:
-keep public class * implements com.tencent.tinker.loader.app.ApplicationLifeCycle {
    *;
}

-keep public class * extends com.tencent.tinker.loader.TinkerLoader {
    *;
}

-keep public class * extends android.app.Application {
    *;
}

#your dex.loader patterns here
-keep class com.taoism.journeytoandroid.tinker.BaseBuildInfo {
    *;
}

-keep class com.tencent.tinker.loader.** {
    *;
}

-keep class com.taoism.journeytoandroid.JourneyApplication {
    *;
}


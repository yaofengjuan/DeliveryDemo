# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService


# We want to keep methods in Activity that could be used in the XML attribute onClick
-keep public class * extends android.app.Activity {##不管用
   public void *(...);
}
-keep public class * extends android.support.v7.app.AppCompatActivity{##不管用
   public void *(...);
}

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * {			 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {			 # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}


# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}
#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep

-keep @android.support.annotation.Keep class * {*;}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}

-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}
#####避免混淆的基本####
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference

-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**{ *;}
-keep public class * extends android.support.v4.widget
-keep public class * extends android.support.v4.app.Fragment{
 public void *(...);
}
-keep public class android.support.v7.**{*;}
-keep public class * extends android.support.v7.**{*;}

#####################

##---------------custom------------------
-keep class com.ya.deliverydemo.adapter.**{*;}
-keep class com.ya.deliverydemo.entity.**{*;}
##---------------custom------------------


##---------------Begin: proguard configuration for Gson ----------
-keep public class com.google.gson.**
-keep public class com.google.gson.** {public private protected *;}

-keepattributes Signature
-keepattributes *Annotation*
-keep public class com.project.mocha_patient.login.SignResponseData { private *; }

##---------------End: proguard configuration for Gson ----------

##--------------start:android-async-http--------------------
##-libraryjars libs/android-async-http-1.4.6.jar
-dontwarn com.loopj.android.http.**
-keep class com.loopj.android.http.**{*;}
##--------------end:android-async-http----------------

##--------------start:showapi_sdk.jar-----------------
##-libraryjars libs/showapi_sdk.jar
-dontwarn com.show.api.**
-keep class com.show.api.**{*;}
##--------------end:showapi_sdk.jar------------------

##---------------start:glide------------------------
-keep public class com.github.bumptech.glide.**{*;}
##---------------end:glide--------------------------

##---------------start:superrecyclerview------------------------
-keep public class com.malinskiy.superrecyclerview.**{*;}
##---------------end:superrecyclerview------------------------
-dontwarn okio.**
-dontwarn retrofit2.**
-dontwarn rx.**
##---------------start:roundedimageview------------------------
-dontwarn com.makeramen.roundedimageview.**
-keep  class com.makeramen.roundedimageview.**{*;}
-keep class com.squareup.picasso.Transformation.**{*;}
##---------------end:roundedimageview------------------------


##---------------start:design------------------------
-keep public class android.support.design.internal.**{*;}
-keep public class android.support.design.widget.**{*;}
-keep public class android.support.design.**{*;}
##---------------end:design--------------------------
-keep class rx.internal.util.unsafe.**{*;}






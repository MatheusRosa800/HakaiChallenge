-keep public class * {
    public <fields>;
    public <methods>;
}


-keep class * {
    @androidx.annotation.Keep *;
}
-keep class * {
    @javax.annotation.Generated *;
}

-dontoptimize
-dontshrink

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BounceCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Verifier
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn javax.annotation.concurrent.GuardedBy

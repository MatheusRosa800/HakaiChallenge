import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.PackageManager;

public class Utilities {

    public static String getProp(Context context, String property) {
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class<?> systemProperties = classLoader.loadClass("android.os.SystemProperties");

            Method get = systemProperties.getMethod("get", String.class);

            Object[] params = new Object[1];
            params[0] = new String(property);

            return (String) get.invoke(systemProperties, params);
        } catch (IllegalArgumentException iAE) {
            throw iAE;
        } catch (Exception exception) {
            throw null;
        }
    }

    public static boolean hasPackageNameInstalled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

    
        try {
            packageManager.getInstallerPackageName(packageName);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }
}

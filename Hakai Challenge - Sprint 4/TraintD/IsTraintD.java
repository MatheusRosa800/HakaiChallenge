package com.sprint4.redribbon.traintd;


import android.content.Context;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import javax.crypto.Cipher;
import com.sprint4.redribbon.common.Utilities;



public class IsTraintD {

   
    public static boolean hasTaintClass() {
        try {
            Class.forName("dalvik.system.Taint");
            return true;
        } catch (ClassNotFoundException exception) {
            return false;
        }
    }

   
    public static boolean hasTaintMemberVariables() {
        boolean taintDetected = false;
        Class<FileDescriptor> fileDescriptorClass = FileDescriptor.class;

        try {
            Field field = fileDescriptorClass.getField("name");
            taintDetected = true; // Taintdroid likely detected
        } catch (NoSuchFieldException nsfe) {
            
        }

        Class<Cipher> cipherClass = Cipher.class;
        try {
            Field key = cipherClass.getField("key");
            taintDetected = true; 
        } catch (NoSuchFieldException nsfe) {
            
        }

        return taintDetected;
    }

    public static boolean hasAppAnalysisPackage(Context context) {
        return Utilities.hasPackageNameInstalled(context, "org.appanalysis");
    }
}

DebugUtils.java 

package com.example.sprint2;

import android.content.Context;
import android.provider.Settings;
import android.os.Debug;

public class DebugUtils {

    // Método estático para verificar se o debugger está conectado ou aguardando conexão
    public static boolean isDebuggerAttached() {
        boolean isDebuggerConnected = Debug.isDebuggerConnected();
        boolean isWaitingForDebugger = Debug.waitingForDebugger();

        // Retorna true se o debugger estiver conectado ou aguardando conexão
        return isDebuggerConnected || isWaitingForDebugger;
    }

    // Método estático para verificar se a depuração USB está habilitada
    public static boolean isUsbDebuggingEnabled(Context context) {
        // Retorna true se a depuração USB estiver habilitada
        return Settings.Secure.getInt(context.getContentResolver(),
                Settings.Secure.ADB_ENABLED, 0) == 1;
    }
}

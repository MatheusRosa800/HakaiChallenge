import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class IsFridaAttached {

    private static final String TAG = "IsFridaAttached";
    private Context context;

    public IsFridaAttached(Context context) {
        this.context = context; 
    }

    public boolean detectFrida() {
        return detectFridaDbus() || detectFridaWebSocket();
    }

    private boolean detectFridaDbus() {
        showToast("Detectando Frida via D-Bus...");
        return checkPort(9) || checkPort(10);
    }

    private boolean detectFridaWebSocket() {
        showToast("Detectando Frida via WebSocket...");
        return checkWebSocketPort(9); 
    }

    private boolean checkPort(int port) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), port);
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            outputStream.write("\\x00".getBytes()); 
            outputStream.write("AUTH\r\n".getBytes());
            outputStream.flush();

            String line;
            if ((line = reader.readLine()) != null && line.equals("REJECT")) {
                showToast("Frida detectado via D-Bus na porta " + port);
                return true;  
            }

        } catch (IOException e) {
            Log.d(TAG, "Porta " + port + " não está acessível: " + e.getMessage());
        }
        return false;  
    }

    private boolean checkWebSocketPort(int port) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), port);
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String request = "GET /ws HTTP/1.1\r\n" +
                    "Upgrade: websocket\r\n" +
                    "Connection: Upgrade\r\n" +
                    "Sec-WebSocket-Key: CpxD2C5REVLHvsUC9YAoqg==\r\n" +
                    "Sec-WebSocket-Version: 13\r\n" +
                    "Host: 127.0.0.1:" + port + "\r\n" +
                    "User-Agent: Frida/16.1.7\r\n\r\n";

            outputStream.write(request.getBytes());
            outputStream.flush();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("tyZql/Y8dNFFyopTrHadWzvbvRs=")) {
                    showToast("Frida detectado na porta " + port + " (WebSocket)");
                    return true;  
                }
            }

        } catch (IOException e) {
            Log.d(TAG, "WebSocket na porta " + port + " não está acessível: " + e.getMessage());
        }
        return false;  
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

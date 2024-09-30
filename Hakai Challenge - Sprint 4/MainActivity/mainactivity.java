import static com.sprint4.redribbon.emulator.AntiEmulator.log;
import static com.sprint4.redribbon.tampering.FileHashUtils.getFileSha256Hash;

import com.sprint4.redribbon.debug.DebugUtils;

import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Handler; 
import android.os.Looper;
import android.view.Menu;
import android.widget.Toast;

import com.sprint4.redribbon.emulator.AntiEmulator;
import com.sprint4.redribbon.frida.IsFridaAttached;
import com.sprint4.redribbon.rootbeer.isRooted;
import androidx.appcompat.app.AppCompatActivity;

import com.sprint4.redribbon.redribbon.caller;
import com.sprint4.redribbon.tampering.FileHashUtils;

import java.util.concurrent.ExecutorService;

import com.sprint4.redribbon.traintd.IsTraintD;


public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService;

    private Handler handler; 

    private Runnable checkDebuggerRunnable;
    private static final String GITHUB_FILE_URL = "https://raw.githubusercontent.com/MatheusRosa800/HakaiChallenge/refs/heads/main/Hakai%20Challenge%20-%20Sprint%203%20/Proguard%20/redribbon_proguard.pro"; // Substitua pelo seu URL
    private boolean isFridaDetected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TRAINTDROID DETECT
        
        // Variáveis para verificar se algo foi detectado
        boolean hasTaintClass = IsTraintD.hasTaintClass();
        boolean hasTaintMembers = IsTraintD.hasTaintMemberVariables();
        boolean hasAppAnalysis = IsTraintD.hasAppAnalysisPackage(this);

        if (IsTraintD.hasTaintClass()) {
            // TaintDroid class detected
            Toast.makeText(this, "TaintDroid detectado!", Toast.LENGTH_LONG).show();
        }

        if (IsTraintD.hasTaintMemberVariables()) {
            // TaintDroid member variables detected
            Toast.makeText(this, "TaintDroid detectado!", Toast.LENGTH_LONG).show();
        }

        if (IsTraintD.hasAppAnalysisPackage(this)) {
            // TaintDroid application detected
            Toast.makeText(this, "TaintDroid detectado!", Toast.LENGTH_LONG).show();
        }


        // Verificação final se nada foi detectado
        if (!hasTaintClass && !hasTaintMembers && !hasAppAnalysis) {
            Toast.makeText(this, "TaintDroid não detectado!", Toast.LENGTH_LONG).show();
        }



        // ROOT DETECT

        // Verifica se o dispositivo está com acesso ROOT e exibe uma mensagem
        isRooted rootCheck = new isRooted();
        boolean isRooted = rootCheck.isDeviceRooted(this);
        if (isRooted) {
            Toast.makeText(MainActivity.this, "ROOT detectado!.", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(MainActivity.this, "ROOT não detectado!.", Toast.LENGTH_LONG).show();
        }




        // TAMPERING DETECT
        
        // Instanciando a classe RedRibbon
        caller caller = new caller();

        // Caminho do arquivo APK do aplicativo
        String filePath = getApplicationContext().getPackageCodePath();

        // Calcular a hash do APK
        String localFileHash = getFileSha256Hash(filePath);
        Toast.makeText(this, "Hash do arquivo local: " + localFileHash, Toast.LENGTH_LONG).show();

        // URL do arquivo contendo o hash no GitHub
        String gitHubFileUrl = "https://raw.githubusercontent.com/MatheusRosa800/testehash/main/ssl%20pining.txt";

        // Executar a verificação de forma assíncrona
        new FetchGitHubHashTask(caller, filePath).execute(gitHubFileUrl);



        // Instancia a classe AntiFrida
        IsFridaAttached fridaDetect = new IsFridaAttached(this); // Passando o contexto

        // Verificar se o Frida está presente
        try {
            isFridaDetected = fridaDetect.detectFrida();
        } catch (Exception e) {
            Toast.makeText(this, " " + e.getMessage(), Toast.LENGTH_LONG).show();
            // Log do erro para depuração
            e.printStackTrace();
        }

        // Se o Frida for detectado, finalizar o aplicativo
        if (isFridaDetected) {
            Toast.makeText(this, "Frida detectado!", Toast.LENGTH_SHORT).show();
            finish(); // Finaliza a activity
        } else {
            Toast.makeText(this, "Frida não detectado!", Toast.LENGTH_SHORT).show();
        }





        // DEBUGGER DETECT E USB DETECT
        
        // Configura o Handler e o Runnable para verificar o debugger e a depuração USB a cada 30 segundos
        handler = new Handler(Looper.getMainLooper());
        checkDebuggerRunnable = new Runnable() {

            @Override
            public void run() {
                // Verifica se o debugger está conectado ou aguardando conexão
                boolean isDebuggerConnected = DebugUtils.isDebuggerAttached();
                if (isDebuggerConnected) {
                    Toast.makeText(MainActivity.this, "Debugger conectado ou aguardando conexão!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Debugger conectado!", Toast.LENGTH_LONG).show();
                }

                // Verifica se a depuração USB está habilitada
                boolean isUsbDebuggingEnabled = DebugUtils.isUsbDebuggingEnabled(MainActivity.this);
                if (isUsbDebuggingEnabled) {
                    Toast.makeText(MainActivity.this, "Depuração USB ativada!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(MainActivity.this, "Depuração USB não ativada!", Toast.LENGTH_LONG).show();
                }

                // Reposta o Runnable a cada 30 segundos
                handler.postDelayed(this, 30000); // 30000 milissegundos = 30 segundos

                isQEmuEnvDetected();
            }


        };


        // Inicia o primeiro ciclo de verificação com um atraso de 1 segundo após a verificação de ROOT
        handler.postDelayed(checkDebuggerRunnable, 3000); 
        
    }



    // ANTI EMULATOR
    public boolean isQEmuEnvDetected() {
        log("Procurando por QEmu env...");

        log("isOperatorNameAndroid : " + AntiEmulator.isOperatorNameAndroid(getApplicationContext()));
        log("hasEmulatorBuild : " + AntiEmulator.hasEmulatorBuild(getApplicationContext()));
        log("hasQEmuDriver : " + AntiEmulator.hasQEmuDrivers());
        log("hasQEmuFiles : " + AntiEmulator.hasQEmuFiles());
        log("hasGenyFiles : " + AntiEmulator.hasGenyFiles());
        log("hasEmulatorAdb :" + AntiEmulator.hasEmulatorAdb());

        if (AntiEmulator.hasEmulatorBuild(getApplicationContext())
                || AntiEmulator.hasQEmuDrivers() || AntiEmulator.hasEmulatorAdb()
                || AntiEmulator.hasQEmuFiles()
                || AntiEmulator.hasGenyFiles()) {
            Toast.makeText(MainActivity.this, "Emulator detectado!", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(MainActivity.this, "Emulator não detectado!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    
    // ANTI TAMPERING
    private class FetchGitHubHashTask extends AsyncTask<String, Void, String> {
        private final caller caller;
        private final String filePath;

        public FetchGitHubHashTask(caller caller, String filePath) {
            this.caller = caller;
            this.filePath = filePath;
        }

        @Override
        protected String doInBackground(String... urls) {
            String gitHubHash = null;
            try {
                gitHubHash = FileHashUtils.getHashFromGitHub(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gitHubHash;
        }

        @Override
        protected void onPostExecute(String gitHubHash) {
            if (gitHubHash != null) {
                Toast.makeText(MainActivity.this, "Hash do arquivo na nuvem: " + gitHubHash, Toast.LENGTH_LONG).show();

               
                boolean tampered = caller.isTampering(filePath, gitHubHash);
                if (tampered) {
                    Toast.makeText(MainActivity.this, "Tampering Alert: O arquivo foi adulterado.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Passou: O arquivo não foi adulterado.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Erro ao obter hash da URL.", Toast.LENGTH_LONG).show();
            }
        }


    }
}

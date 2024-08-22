public class SharedPreferencesManager {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_TEXT = "savedText";
    private SharedPreferences sharedPreferences;

    // Construtor privado para impedir instanciação direta da classe
    private SharedPreferencesManager() {
    }

    // Método estático para obter uma instância única da classe
    public static SharedPreferencesManager getInstance(Context context) {
        return new SharedPreferencesManager(context);
    }

    // Construtor que inicializa as SharedPreferences criptografadas
    private SharedPreferencesManager(Context context) {
        try {
            // Cria uma chave mestra para criptografia
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            // Inicializa as SharedPreferences criptografadas
            sharedPreferences = EncryptedSharedPreferences.create(
                    PREFS_NAME,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            Log.e("SharedPreferences", "Erro ao inicializar as SharedPreferences criptografadas", e);
            // Você pode lidar com o erro aqui, talvez lançando uma exceção personalizada
        }
    }

    // Método para salvar o texto nas SharedPreferences
    public void saveText(String text) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TEXT, text);
        editor.apply();
    }

    // Método para recuperar o texto das SharedPreferences
    public String getText() {
        return sharedPreferences.getString(KEY_TEXT, "");
    }
}

# Apresentação: Sprint 2 - Hakai Challenge

## Introdução

Após realizar os testes e localizar os pontos de vulnerabilidade no aplicativo escolhido, chega o momento de começar a levantar as possíveis soluções existentes e mais adequadas para o problema em questão. Para obter maior precisão nos resultados, foi realizado um trabalho de correlação entre as vulnerabilidades encontradas, os grupos de controle do padrão Owasp MASVS e qual solução de mercado poderia prover a segurança necessária no contexto da vulnerabilidade encontrada no aplicativo.

Com a finalidade de ter uma visão mais clara a respeito da superfície do aplicativo foi criado um organograma com apoio da ferramenta Threat Dragon. Na modelagem de ameaças, 12 vulnerabilidades encontradas no aplicativos, foram devidamente registradas dentro de cada grupo de controle. O registro facilitou o entendimento o tipo de vulnerabilidade, o acesso a uma breve descrição da mesma e as formas de mitigação. Com estas informações organizadas foi possível encontrar as soluções adequadas já disponíveis a serem implementadas de forma personalizada.


## Grupo Red Ribbon
- **Membros da Equipe:**
  - Matheus Rosa
  - Henrique Koji
  - Felipe Madeira
  - Pedro Augusto
  - Caio Vinícius

- **Descrição:**
A Red Ribbon é uma organização que presta serviços relacionados à aplicações mobile usando uma solução RASP SDK (Runtime Application Self-Protection).

## Correlacionando as vulnerabilidades
Pra correlacionar a vulnerabilidades com as soluções, foi preciso primeiro verificar do que já se tem como efetivo no mercado. No paper **"Honey, I Shrunk Your App Security: The State of Android App Hardening"** publicado por alunos da Friedrich-Alexander University da Alemanha, é possível encontrar um levantamento muito completo de soluções RASP e suas funcionalidades. Nele, foi possível identificar 12 soluções nas quais já existem produtos de mercado. Cruzando estas soluções com as mitigações necessárias para proteger o aplicativo, chegamos à conclusão de que 10 destas seria o suficiente para solucionar o problema. 

![diagrama](.img/diagrama.png)

### Soluções já existentes no mercado
- Anti-Tampering: Verificar a assinatura do pacote de assinatura do pacote de aplicativos Android na inicialização.
- Anti-Hooking: Encontrar vestígios de estruturas hooking conhecidas no sistema de arquivos ou na memória
- Anti-Debugging: Detectar a presença depuradores e abortar a execução se necessário
- Anti-Emulator: Detecção de sandboxes e encerramento do programa
- Code Obfuscation: Ocultar o código tanto quanto possível com nivelamento de fluxo de controle, predicados opacos e aleatórios e fusão e divisão de funções
- White-Box Cryptography: Impedir o vazamento de segredos criptográficos
- Device Binding: Tirar impressões digitais do dispositivo e armazena identificadores exclusivos na primeira inicialização do aplicativo emparelhando o dispositivo com a conta
- Root Detection: Verifica processos, diretórios e aplicativos no dispositivo que indicam o root
- Anti-Keylogger: Fornece teclado nativo do aplicativo
- Anti-Screen Reader: Desabilitar as capturas de tela durante a execução
- Data Encryption: Fornece armazenamento seguro criptografado protegido pela criptografia de caixa branca
- Secure Comunication: Fornece API própria de rede HTTPS e implementar certificado de cliente no arquivo de configuração para verificações

### Vulnerabilidades e mitigações
- Bypass na lógica de autenticação do usuário:
    - Anti-hooking
- Criptografia fraca
    - White-Box Cryptography
- Interceptação de dados transmitidos por protocolo inseguro
    - Secure Communication
- Acesso não autorizado ao provedor de conteúdo do aplicativo
    - Anti-Emulator
    - Anti-Hooking
    - Anti-Debugging
- Credenciais vazadas por meio de redefinição de senha
    - Anti-Hooking
- Interceptação de credenciais por meio de registro inseguro
    - White-Box Cryptography
    - Data Encryption
- Bypass na verificação de integridade do dispositivo
    - Anti-Tampening
    - Root Detection
- Código fonte expõe credenciais de acesso deixadas pelo desenvolvedor
    - Code Obfuscation
    - White-Box Cryptography
- Patch binário possibilita acesso a um usuário com privilégios administrativos
    - Anti-Tampering
- Bypass na lógica de backup de credenciais do programa
    - Data Encryption
    - White-Box Cryptography
- Exploração do cache do teclado
    - Anti-Keylogger
- Extração de dados enviados para a área de transferência
    - White-Box Cryptography

### Filtrando as soluções

- Anti-Hooking
- Anti-Emulator
- Anti-Debugging
- Anti-Tampering
- Secure Comunication
- White-Box Cryptography
- Anti-Keylogger
- Data Encryption
- Code Obfuscation
- Root Detection

## Produtos e ferramentas
Com as soluções definidas, é possível chegar em quais produtos, ferramentas e bibliotecas conseguem empregar os tópicos específicos de forma eficiente. As ferramentas escolhidas pelo grupo foram exclusivamente gratuitas, pois o exercício em questão busca verificar a eficiência desta abordagem. Isso não descarta a possibilidade de emprego de soluções pagas ou até mesmo do desenvolvimento de uma funcionalidade extra de segurança específica caso seja necessário.



### AndroidX Security
"Gerencia chaves com segurança e criptografa arquivos de preferências compartilhadas"
##### Resolve
    - Data Encryption
##### Implementação

A aplicação de criptografia com a biblioteca AndroidX Security é feita na lógica da classe que recebe entrada e armazena. No exemplo foi criado um campo de entrada simples que armazena os valores inseridos pelo usuário e imprime na tela. Para que este valor esteja protegido, enquanto estiver salvo no dispositivo deve-se habilitar a biblioteca nas dependências do arquivo build.gradle na raiz do aplicativo:

```
implementation(libs.security.crypto)
```

Depois se aplica na classe as funções de encriptar e descriptografar o texto recebido. No exemplo, em uma entrada de texto simples o código assim:

```
package com.example.beta;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

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

```

As chamadas do main.activity devem então interagir com esta classe, que vai criptografar e armazenar os dados. Para validar isso, no aplicativo primeiro inserimos "texto seguro" e observamos que o retorno foi o mesmo.

![xsecurity](.img/xsecurity.png)

Já quando acessamos o banco de dados via adb, ao abrir o arquivo xml onde a entrada foi armazenada, podemos perceber que o texto foi de fato criptografado. 

![xsecurity](.img/xsecurity2.png)

##### Referência
https://developer.android.com/jetpack/androidx/releases/security?hl=pt-br

### ProGuard
É um ofuscador otimizador e pré-verficador para Java
##### Resolve
    - Code Obfuscation
##### Implementação
Para aplicar a biblioteca e implementar a ofuscação do código fonte é preciso primeiro chamar o proguard no arquivo build.gradle presente na pasta raiz do projeto. 

![proguard1](.img/proguard1.png)

Dentro da classe de release, o seguinte código pode ser inserido, com a função de ofuscar e compactar o código fonte da aplicação.

```
isMinifyEnabled = true
isShrinkResources = true
if(signingConfig != null)
proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
```
Depois disso é necessário inserir no arquivo proguard-rules.pro as orientações de aplicação do proguard, que no exemplo foram os seguintes:

```
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
```

Com isso, após compilado é possível abrir o apk e verificar que algumas chamadas foram alteradas com a finalidade de dificultar a compreensão do código.

![proguard2](.img/proguard2.png)


##### Referência
https://github.com/Guardsquare/proguard

### RootBeer
"Esse dispositivo tem root?" É a pergunta que a biblioteca promete responder através de diversos métodos
##### Resolve
    - Root Detection
##### Implementação
Para aplicar o root beer é necessário primeiro inserira chamada de sua biblioteca no arquivo buld.gradle.kts a nível da raiz do aplicativo. Para isso, na seção de dependências se deve inserir a seguinte informação:

```
implementation(libs.rootbeer.lib)
```
Depois disso é necessário criar a função de verificação. Na implementação do aplicativo teste, este arquivo foi criado em paralelo ao "MainActivity" na pasta Java da main do projeto com nome RootCheck. O conteúdo é basicamente o mesmo indicado na documentação da biblioteca com a importações necessárias:

```
package com.example.beta;

import android.content.Context;

import com.scottyab.rootbeer.RootBeer;

public class RootCheck {

        // Function to check if the device is rooted
        public boolean isDeviceRooted(Context context) {
            RootBeer rootBeer = new RootBeer(context);
            return rootBeer.isRooted();
    }
}
```
Agora basta chamar a função no script MainActivity e definir qual ação será tomada dentro da classe "OnCreated". No exemplo aplicado, ao verificar que o dispositivo tem acesso Root ele apenas exibirá uma mensagem, no entanto outras decisões podem ser tomadas, como fechamento do dispositivo ou até envio de alertas para um servidor remoto.

```
        RootCheck rootCheck = new RootCheck();

        boolean isRooted = rootCheck.isDeviceRooted(this);
	if (isRooted) {
            Snackbar.make(binding.getRoot(), "O dispositivo está com acesso ROOT.", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(binding.getRoot(), "O dispositivo não está com acesso ROOT.", Snackbar.LENGTH_SHORT).show();
        }

```
Depois de completar os passos anteriores é possível verificar que ao rodar em dispositivo com acesso root, o app vai levantar uma alerta.

![rootbeer](.img/rootbeer.png)

##### Referência
https://github.com/scottyab/rootbeer    

### Free RASP
É uma biblioteca móvel produzida pela TalSec e fornecida em versão gratuita e paga. Contém diversas verificações de segurança, destinada a vetores variados de ataque. 
##### Resolve
    - Anti-Hooking
    - Anti-Emulador
    - Anti-Debugging
    - Anti Tampering
##### Implementação
Em andamento
##### Referência
https://github.com/talsec/Free-RASP-Community

### OpenRASP
É uma solução de controle de perímetro. Consegue monitorar vários eventos através de integração dos seus mecanismos de defesa com o servidor do aplicativo.
##### Resolve
    - Secure Comunication
	- White-Box Cryptography
##### Implementação
Em andamento
##### Referência
https://github.com/baidu/openrasp

### Material Dialogs
É uma biblioteca que possui diversos módulos para coleta de entradas de forma segura. Uma delas é o SecureEditText
##### Resolve
    - Anti-Keylogger
##### Implementação
Em andamento
##### Referência
https://github.com/afollestad/material-dialogs



## Resultados
- **Overview dos Relatórios:**


## Conclusão


- **Agradecimentos:**
  Agradecemos a Hakai Security pela oportunidade de participar deste desafio e aos avaliadores por seu tempo e consideração.

![image](https://github.com/MatheusRosa800/HakaiChallenge/assets/105319207/a2aa1d8e-6d24-48fa-a646-aaee07b63028) 
![OIG3-removebg-preview](https://github.com/MatheusRosa800/HakaiChallenge/assets/127846261/a48fd91e-3084-4313-a8a8-c8cd067cb7fb)



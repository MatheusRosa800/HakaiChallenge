# Explorando a fraca criptografia do programa

## Descrição

Quando uma chave criptográfica é codificada dentro de um aplicativo Android, isso significa que a chave está diretamente incorporada no código-fonte do aplicativo. Podemos explorar essa vulnerabilidade explorando um diretório específico no sistema de arquivos do dispositivo onde são armazenados arquivos de preferências compartilhadas para o aplicativo "com.android.insecurebankv2".

## Referências

https://redfoxsec.com/blog/broken-cryptography-android-applications/

https://blog.quarkslab.com/android-data-encryption-in-depth.html

https://mas.owasp.org/MASTG/tests/android/MASVS-CRYPTO/MASTG-TEST-0013/

## Impacto

O impacto da vulnerabilidade de criptografia fraca no aplicativo InsecureBankv2 pode ser significativo e abrangente, como o roubo de credenciais, perde de confiança de usuários, com acesso a informações pessoais dos usuários, os invasores podem realizar atividades de roubo de identidade, como abrir contas falsas em nome dos usuários ou realizar transações financeiras fraudulentas. O que se encaixa exatamente em nosso cenário, afinal, é um banco.

## Prova de conceito

A conclusão de que a vulnerabilidade de criptografia está presente em um determinado diretório, “**/data/data”**, é resultado de análise comportamento do aplicativo. Muitos aplicativos armazenam dados sensíveis, como informações de login, tokens de autenticação e outros dados confidenciais, dentro de seus diretórios em **“/data/data”**

Portanto precisamos nos conectar no emulador, e interagir com o S.O

![.img/manifest.png](.img/manifest.png)

```
adb shell

```

Ao rodarmos o comando, o adb shell, temos acesso ao Android, e seguido as requisições de chamada, do Manifest, chegamos ate o **‘shared_prefs’,** logo, precisamos acessar esse diretório para explorar ainda mais a vulnerabilidade

![.img/adbbackup.png](.img/adbbackup.png)

![.img/fullbackup.png](.img/fullbackup.png)

Vamos utilizar o comando para ir no diretório desejado dentro do sistema Android e verificar o que há no diretório:

```
 cd /data/data/com.android.insecurebankv2/shared_prefs/
 
 *precisamos de privilégios, podemos usar o su*

generic_x86:/data/data/com.android.insecurebankv2/shared_prefs # **ls**

```

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/78058eaf-e6fc-4663-bdc9-307e8c83830d/d8502e06-40ff-4b43-8ce9-1e46e51619c0/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/78058eaf-e6fc-4663-bdc9-307e8c83830d/e1b01eb5-3ea0-4202-bd06-d36236bd15d2/Untitled.png)

Como visto, podemos observar que há um SharedPreferences.xml, que possivelmente pode haver dados sensíveis.

```
generic_x86:/data/data/com.android.insecurebankv2/shared_prefs # cat mySharedPreferences.xml

```

Assim, teremos uma  “Senha super secreta “ e um “Username criptografado”

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/78058eaf-e6fc-4663-bdc9-307e8c83830d/3f927328-9d2e-43db-b346-2089be377373/Untitled.png)

Vamos extrair o conteúdo do arquivo:

```bash
unzip InsecureBankv2.apk
```

Podemos analisar o arquivo: 

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/78058eaf-e6fc-4663-bdc9-307e8c83830d/dbee2932-7bf1-421c-8da9-c6860ceed9c6/Untitled.png)

Ou seja, temos a chave AES, logo, podemos  “decodar” a senha e o usuário que estava dentro do XML.

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/78058eaf-e6fc-4663-bdc9-307e8c83830d/c356fe65-78b1-45b0-aa9b-a7ed5a09c4ab/Untitled.png)

Correlacionamos com o usuário e logamos no sistema.

![.img/credenciaisxml.png](.img/credenciaisxml.png)

## Ação sugerida para mitigação

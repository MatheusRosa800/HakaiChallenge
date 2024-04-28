# Acesso não autorizado ao provedor de conteúdo do aplicativo

## Descrição

O provedor de conteúdo que permite compartilhar dados entre usuáros e sistema do aplicativo é acessível e consultável por qualquer pessoa de posse do aplicativo. A falha possibilita a enumeração dos usuários registrados localmente e pode culminar em vazamento de dados sensíveis.

## Referências 

https://mas.owasp.org/MASTG/Android/0x05h-Testing-Platform-Interaction/#permission-enforcement

https://blog.oversecured.com/Content-Providers-and-the-potential-weak-spots-they-can-have/

https://developer.android.com/guide/topics/providers/content-provider-basics

https://medium.com/@paritasampa95/content-provider-in-android-07d499cf7539

https://mas.owasp.org/MASVS/09-MASVS-PLATFORM/

https://embarcados.com.br/comunicacao-entre-processos/


## Impacto

A vulnerabilidade pode estar associada a vazamento de dados, exposição não intencional, além de ser uma superficíe de ataque para injeção SQL e escalação de permissões.

## Prova de conceito

Com acesso ao arquivo AndroidManifest.xml é possóvel identificar a existencia de um receptor de broadcast no aplicativo.

![content_provider0](.img/content_provider0.png)

A verificação anterior possibilita encontrar a localização da classe por meio da ferramenta de pesquisa no JadX. 

![content_show](.img/content_show.png)

Dessa forma se obtem a URI para testar o acesso e tentar obter algumas informações compartilhadas pelo aplicativo.

![content_provider](.img/content_provider.png)

O levantamento das informações possibilita a consulta com a ferramenta **adb** com o seginte comando:

```
adb shell content query --uri content://com.android.insecurebankv2.TrackUserContentProvider/trackerusers
```

O retorno será a lista de usuários logados no aplicativo e seus respectivos ids. 

![content_provider1](.img/content_provider2.png)

## Ação sugerida para mitigação

A segurança dos aplicativos móveis depende muito de sua interação com a plataforma móvel, o que muitas vezes envolve a exposição **intencional** de dados(senhas, cartão de crédito) ou funcionalidades por meio do uso de mecanismos de inter-process communication (IPC)-*mecanismo que permite que dois ou mais processos realizem a troca de dados entre si*.

Que estes são fornecidos pela plataforma e WebViews para aprimorar a experiência do usuário, porém, os mesmos podem ser explorados por invasores ou outros aplicativos instalados, comprometendo a segurança do apicativo em questão.

A mitigação se deve a permitir que todas essas interações envolvendo mecanismos de IPC aconteçam de forma segura.

E garantir que esses dados não sejam vazados involuntariamente devido a mecanismos da plataforma, como capturas de tela geradas automaticamente e gravações de tela.

## Código de exemplo para mitigação

### SCREEN SHARING DETECTION

```
val raspConfig = RaspConfig.Builder()
    .screenSharing(DetectionConfig)
    // configuration of other RASP features
    .build()
```








# Apresentação: Sprint 2 - Hakai Challenge

## Introdução

Após realizar os testes e localizar os pontos de vulnerabilidade no aplicativo escolhido, chega o momento de começar a levantar as possíveis soluções existentes e mais adequadas para o problema em questão. Para obter maior precisão nos resultados, foi realizado um trabalho de correlação entre as vulnerabilidades encontradas, os grupos de controle do padrão Owasp MASVS e qual solução de mercado poderia prover a segurança necessária no contexto da vulnerabilidade encontrada no aplicativo.

Com a finalidade de ter uma visão mais clara a respeito da superfície do aplicativo foi criado um organograma com apoio da ferramenta Threat Dragon. Na modelagem de ameaças, 12 vulnerabilidades encontradas no aplicativos, foram devidamente registradas dentro de cada grupo de controle. O registro facilitou o entendimento o tipo de vulnerabilidade, o acesso a uma breve descriçao da mesma e as formas de mitigação. Com estas informações organizadas foi possível encontrar as soluções adequadas já disponíveis a serem implementadas de forma personalida.

## Grupo Red Ribbon
- **Membros da Equipe:**
  - Matheus Rosa
  - Henrique Koji
  - Felipe Madeira
  - Pedro Augusto
  - Caio Vinícius

- **Descrição:**
A Red Ribbon é uma organização que presta serviços relacionados à aplicações mobile usando uma solução RASP SDK (Runtime Application Self-Protection).

## Correlacionando as vulnerabiliades
Pra correlacionar a vulnerabiliades com as soluções, foi preciso primeiro verificar do que já se tem como efetivo no mercado. No paper **"Honey, I Shrunk Your App Security: The State of Android App Hardening"** publicado por alunos da Friedrich-Alexander University da Alemanha, é possível encontrar um levantamento muito completo de soluções RASP e suas funcionalidades. Nele, foi possível indeitificar 12 soluções nas quais já existem produtos de mercado. Cruzando estas soluções com as mitigações necessárias para protejer o aplicativo, chegamos à conclusão de que 10 destas seria o suficiente para solucionar o problema. 

### Soluções já existentes no mercado
- Anti-Tampering: Verificar a assinatura do pacote de assinatura do pacote de aplicativos Android na inicialização.
- Anti-Hooking: Encontrar vestígios de estruturas hooking conhecidas no sistema de arquivos ou na memória
- Anti-Debugging: Detectar a presença depuradores e abortar a execução se necessário
- Anti-Emulator: Detecção de sandboxes e encerramento do programa
- Code Obfuscation: Ocultar o código tanto quanto possível com nivelamento de fluxo de controle, predicados opacos e aleatórios e fusão e divisão de funções
- White-Box Cryptogrsphy: Impedir o vazamento de segredos criptográficos
- Device Binding: Tirar impressões digitais do dispositivo e armazena identificadores exclusivos na primeira inicialização do aplicativo emparelhando o dispositivo com a conta
- Root Detection: Verifica processos, diretórios e aplicativos no dispositivo que indicam o root
- Anti-Keylogger: Fornece teclado nativo do aplicativo
- Anti-Screen Reader: Desabilitar as capturas de tela durante a execução
- Data Encryption: Fornece armazenamento seguro criptografado protegido pela criptografia de caixa branca
- Secure Comunication: Fornce API própria de rede HTTPS e implementar certificado de cliente no arquivo de configuração para verificações

### Vulnerabilidades e mitigações
- Bypass na lógica de autentucação do usuário:
    - Anti-hooking
- Criptografia fraca
    - White-Box Cryptography
- Interceptação de dados transmitidos por porotocolo inseguro
    - Secure Communication
- Acesso não autorizado ao provedor de conteúdo do aplicativo
    - Anti-Emulator
    - Anti-Hooking
    - Anti-Debugging
- Credenciais vazadas por meio de redefinição de senha
    - Anti-Hooking
- Interceptação de credenciais por meio de registro inseguro
    - White-Box Crytography
    - Data Encryption
- Bypass na verificação de integridade do dispositivo
    - Anti-Tampening
    - Root Detection
- Código fonte expõe credenciais de acesso deixadas pelo desenvolvedor
    - Code Obfuscation
    - White-Box Cryptography
- Patch binário possibilita acesso a usuário com privilégios administrativos
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
Com as soluções definidas, é possível chegar em quais produtos, ferramentas e bibliotecas conseguem empregar os tópicos execíficos de forma eficiente. As ferramentas escolhidas pelo grupo foram exclusivamente gratuítas, pois o exercício em questão busca verificar a efíciencia desta abordagem. Isso não descarta a possibilidade de emprego de soluções pagas ou até mesmo do desenvolvimento de uma funcionalidade extra de segurança expecífica caso seja necessário.

### Free RASP
É uma biblioteca móvel produzida pela TalSec e fornecida em versão gratuita e paga. Contém diversas verificações de segurança, destinada a vetores variados de ataque. 
##### Resolve
    - Anti-Hooking
    - Anti-Emulador
    - Anti-Debugging
    - Anti Tampering
##### Implmentação
Em andamento
##### Referência
https://github.com/talsec/Free-RASP-Community

### OpenRASP
É uma solução de controle de peŕimetro. Consegue monitorar vários eventos através de integração dos seu mecanismos de defesa com o servidor do aplicativo.
##### Resolve
    - Secure Comunication
	- White-Box Cryptography
##### Implmentação
Em andamento
##### Referência
https://github.com/baidu/openrasp

### Material Dialogs
É uma blioteca que possui deversos módulos pare coleta de entradas de forma segura. Uma delas o SecureEditText
##### Resolve
    - Anti-Keylogger
##### Implementação
Em andamento
##### Referêcnia
https://github.com/afollestad/material-dialogs

### AndroidX Security
"Gerencia chaves com segurança e criptografa arquivos de preferêcnias compartilhadas"
##### Resolve
    - Data Encryption
##### Implementação
Em andamento
##### Referêcnia
https://developer.android.com/jetpack/androidx/releases/security?hl=pt-br

### ProGuard
É um ofuscador otimizador e préverficador para Java
##### Resolve
    - Code Obfuscation
##### Implementação
Em andamento
##### Referêcnia
https://github.com/Guardsquare/proguard

### RootBeer
"Esse diisposiitivo tem root?" É a pegunta que a biblioteca promete responder através de diversos métodos
##### Resolve
    - Root Detection
##### Implementação
Em andamento
##### Referêcnia
https://github.com/scottyab/rootbeer    

- **Etapas da Implemetação:**

- ## Diagrama
  <img src="../4.png">

## Resultados
- **Overview dos Relatórios:**


## Conclusão


- **Agradecimentos:**
  Agradecemos à Hakai Security pela oportunidade de participar deste desafio e aos avaliadores por seu tempo e consideração.

![image](https://github.com/MatheusRosa800/HakaiChallenge/assets/105319207/a2aa1d8e-6d24-48fa-a646-aaee07b63028) 
![OIG3-removebg-preview](https://github.com/MatheusRosa800/HakaiChallenge/assets/127846261/a48fd91e-3084-4313-a8a8-c8cd067cb7fb)



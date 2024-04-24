# Patch binário possibilita acesso a usuário com privilégios administrativos

## Descrição

Por meio da depuração do código fonte do aplicativo é possível observar que o recurso de verificação de adminitrador pode ser alterado, possibilitando acesso do invasor a ambiente indevido. Após decompilar o apk é possível acessar o arquivo xml de strings do programa. Ali é possivel verificar a existencia da verificação "is_admin". Quando alterado seu valor booleano de no para yes, recompilado e reassinado, varifica-se o acesso a uma plataforma com acesso a criação de usuários.   

## Referências 

https://mas.owasp.org/MASTG/techniques/android/MASTG-TECH-0013/

https://mas.owasp.org/MASTG/techniques/android/MASTG-TECH-0012/#bypass-custom-certificate-pinning-statically

https://mobile-security.gitbook.io/mobile-security-testing-guide/general-mobile-app-testing-guide/0x04c-tampering-and-reverse-engineering

https://www.ragingrock.com/AndroidAppRE/

https://apktool.org/

https://mas.owasp.org/MASVS/11-MASVS-RESILIENCE/

## Impacto

Com o acesso adminsitrativo no aplicativo, o atacante pode criar usuários não autorizados sem cumprir os requisitos definidos pela empresa detentora dos direitos do aplicativo.

## Prova de conceito

Afim de realizar o reconhecimento das funcioanlidades do aplicativo, pode-se decompilar o apk utilizando JADX. Através da análise estática pode-se localizar os possíveis pontos fracos da aplicação como a alteração de verificação do admin. 

![jadx_analise](.img/jadx_analise.png)


Para conseguir acesso ao arquivo que será alvo de alteração, é necessário decopilar o aplicativo. Para isso é possível utilizar a ferramenta apktool com o seguite comando:

```
apktool d InsecureBankv2.apk
```

Serão gerados diversas pastas das quais o alvo de alteração se encontra no caminho IsecureBankv2/res/values. O arquivo onde foi encontrado o possível ponto de alteração é o string.xml que poder ser aberto e editado com qualquer editor de texto. 

![string_adm](.img/string_adm.png)

Após realizar a alteração, é necessario recompilar o apk apontando o apktool para o diretório gerado na decopilação com a seguinte comando:

```
apktool b /InsecureBrankv2
```

Com o aplicativo compilado, basta gerar e realizar uma nova assinatura através da ferramenta keytool e jarsingner 

```
keytool -genkey -v -keystore patch.keystore -alias patchKeystore -keyalg RSA -keysize 2048 -validity 10000 
```

```
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore patch.keystore InsecureBankv2.apk patchKeystore
```
Ao carregar o apk modificado, no ambiente de teste pode-se perceber que a funcionalidade criar o usuário agora aparece. 

![app_admin](.img/app_admin.png)

# Mitigação

Manter a integridade da plataforma com mecanismos de Anti-Debugging e Anti-Tampering.

Impedir a instrumentação dinâmica que poderia permitir que um invasor modificasse o código em tempo de execução

Ofuscação de código e 



# Bypass na verificação de integridade do dispositivo

## Descrição

Acesso ao código fonte permite alteração e bypass na verificação de segurança para execução da aplicação em dispositivos com acesso ao root. A não verificação de autenticidade da assinatura do aplicativo possibilita executá-lo de forma alterada, isso permite que atacantes tenham acesso a diversos recursos para explorar a plataforma.

## Referências

https://sec-consult.com/blog/detail/android-mobile-root-detection-snake-oil-or-silver-bullet/

https://mas.owasp.org/MASTG/General/0x04c-Tampering-and-Reverse-Engineering/

https://support.google.com/accounts/answer/9211246?hl=en


## Impacto

A execução de um aplicativo em uma plataforma com super usuário pode permitir que o atacante acesse inumeros recursos limitados a um usuário comum, bem como ao código fonte e declarações de troca de menssagens. O bypass desta função significa a queda de uma das principais linhas de defesa do aplicativo.

## Prova de conceito

Em sua forma original o aplicativo levanta o alerta de que está sendo rodado em um dispositivo **rootado**. 

![rooted_device](rooted_device.png)

Após decompilar o código fonte com a ferramenta JadX, é possível localizar esta função por meio da funcionalidade de busca, inserindo exatemente a mesma mensagem mostrada no aplicativo.

![msg_root](msg_root.png)

Assim é possível encontrrar o função responsável pela verificação do dispositivo.

![func_root](func_root.png)

Para conseguir alterar o código fonte é necessário decompilar com a ferramenta apktool com seguinte comando:

```
apktool d InsecureBankv2.pk
```
Nos diretórios gerados é necessário localizar o código onde se encontra a declaração. Em seguida basta alterar o código, pulando a etapa de verificação para a aprovação do dispositivo.

![code_root](code_root.png)

O aplicativo pode ser recompilado com a mesma ferramenta passando a pasta gerada como alvo com o seguinte comando:

```
apktool b ~/InsecureBankv2
```

Em seguida é necessário reassinar o apk, depois de gerar uma assinatura, isso pode ser realizado com os seguintes comandos:

```
keytool -genkey -v -keystore patch.keystore -alias patchKeystore -keyalg RSA -keysize 2048 -validity 10000 
```

```
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore patch.keystore InsecureBankv2.apk patchKeystore
```

Ao instalar o aplicativo alterado e realizar o loginpodemos perceber agora que a menssagem impressa indica que não foi identificado a execução do aplicativo em um dispositivo **rootado**.

![not_rooted](not_rooted.png)


## Ação sugerida para mitigação

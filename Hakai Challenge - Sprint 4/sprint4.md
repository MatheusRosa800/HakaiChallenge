## Introdução 

Nos últimos meses, temos aplicado o conhecimento adquirido nas etapas anteriores do projeto, concentrando-nos na correlação de problemas identificados, mitigação de riscos e desenvolvimento de soluções eficazes encontradas no mercado atual. Após uma análise detalhada das vulnerabilidades e desafios enfrentados, avançamos para a fase de codificação.

Durante este período, dedicamo-nos a implementar e testar diversas técnicas de segurança em um aplicativo de teste que criamos especificamente para simular um ambiente real. Este aplicativo de teste serviu como um campo de provas para validar e refinar nossas soluções, garantindo que elas funcionem conforme o esperado no aplicativo que será disponibilizado pela HAKAI.

Através dessa abordagem prática, conseguimos integrar e avaliar funcionalidades essenciais para a segurança do aplicativo.

## Grupo Red Ribbon
- **Membros da Equipe:**
  - Matheus Rosa
  - Henrique Koji
  - Felipe Madeira
  - Pedro Augusto
  - Caio Vinícius

- **Descrição:**
A Red Ribbon é uma organização que presta serviços relacionados à aplicações mobile usando uma solução RASP SDK (Runtime Application Self-Protection).


## Soluções de segurança feitas e implantadas

- Anti-Debugger: Implementação de mecanismos que detectam tentativas de depuração, fechando o aplicativo caso sejam identificadas.

- Anti-Root: Adição de verificações para identificar dispositivos com acesso root, impedindo a execução do aplicativo em tais dispositivos e assegurando sua execução apenas em dispositivos não comprometidos.

- Criptografia de Dados Locais: Implementação de criptografia robusta para dados armazenados localmente no dispositivo, garantindo que informações sensíveis estejam protegidas contra acesso não autorizado.

- Ofuscação: Utilização de técnicas de ofuscação para proteger o código do aplicativo contra engenharia reversa, modificando os nomes de classes e métodos para dificultar a compreensão por parte de atacantes.

- Hashing: Adição de verificação de integridade dos arquivos críticos do aplicativo, como MainActivity e build.gradle, calculando e comparando hashes para detectar modificações não autorizadas.

- Anti-Emulator: Verificação de arquivos do sistema, informações da build do celular e drivers, com foco em emuladores, especialmente o Genymotion.

- Anti-USB: Implementação de medidas contra depuração USB, também conhecido como anti-ADB.

- Anti-Frida: Detecção de Frida por meio de respostas rejeitadas do D-Bus e WebSocket com assinatura.

- Anti-TraintDroid: Proteção contra a ferramenta TraintDroid, capaz de realizar engenharia reversa e ataques man-in-the-middle.

Com a implementação dessas soluções, nosso objetivo é fortalecer a segurança do aplicativo e proporcionar uma proteção eficaz contra ameaças em tempo real.


## Conclusão

Durante o Hakai Challenge, tivemos a oportunidade de aplicar e expandir nosso conhecimento em segurança de aplicativos mobile, focando na implementação de soluções de RASP (Runtime Application Self-Protection). O desenvolvimento do SDK Red Ribbon, voltado para proteger aplicativos Android, nos permitiu abordar diversas vulnerabilidades críticas de segurança, como ataques de root, hooking, debugging e manipulação de dados sensíveis.

Ao longo dos sprints, integramos ferramentas amplamente reconhecidas no mercado, como AndroidX Security, ProGuard, RootBeer, Free RASP e outras, adaptando essas soluções para mitigar os riscos que identificamos no aplicativo alvo. Todo esse processo reforçou nosso entendimento sobre os desafios de segurança mobile e o papel crucial de um sistema de RASP eficaz para proteger aplicativos em tempo real.

O aprendizado técnico obtido, juntamente com a prática de trabalhar em equipe sob prazos e metas, nos proporcionou uma experiência valiosa tanto para nosso crescimento profissional quanto acadêmico.

Em resumo, o desenvolvimento do SDK Red Ribbon representa não apenas a solução de problemas técnicos, mas também uma visão concreta de como é possível aumentar a resiliência de aplicativos mobile em um cenário de cibersegurança cada vez mais desafiador.

- **Agradecimentos:**
  
Queremos expressar nossa profunda gratidão à FIAP e à Hakai Security por proporcionarem essa oportunidade única de aprendizado e crescimento.

À FIAP, agradecemos por oferecer um ambiente acadêmico que promove a inovação, incentivando projetos desafiadores e preparando seus alunos para enfrentar problemas do mundo real.

À Hakai Security, nosso sincero agradecimento pelo Hakai Challenge. O desafio nos permitiu vivenciar de perto as complexidades do desenvolvimento de soluções de segurança, ensinando-nos lições valiosas sobre o mercado e a aplicação prática de conceitos de proteção digital.

Sentimo-nos honrados em participar dessa experiência e somos gratos por todo o apoio, orientação e feedback recebidos ao longo do caminho. Essas contribuições foram fundamentais para o sucesso do nosso projeto e o crescimento de cada membro da equipe.


![image](https://github.com/MatheusRosa800/HakaiChallenge/assets/105319207/a2aa1d8e-6d24-48fa-a646-aaee07b63028) 
![OIG3-removebg-preview](https://github.com/MatheusRosa800/HakaiChallenge/assets/127846261/a48fd91e-3084-4313-a8a8-c8cd067cb7fb)





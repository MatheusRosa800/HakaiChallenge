# Hakai Challenge
Na primeira fase, as equipes são encarregadas de desenvolver sua própria solução de RASP (Runtime Application Self-Protection). O RASP é uma tecnologia avançada de segurança que se integra diretamente ao código de um aplicativo, operando em tempo real para identificar e bloquear
ameaças.

## Grupo Red Ribbon

### Sobre nós
A Red Ribbon é uma organização que presta serviços relacionados à aplicações mobile usando uma solução RASP SDK (Runtime Application Self-Protection).

### Integrantes

- Matheus Rosa
- Henrique Koji
- Felipe Madeira
- Pedro Augusto
- Caio Vinícius

# Entrega 01
Realizamos alguns Pentests no aplicativo mobile pela Hakai (InsecureBankv2) e elaboramos relatórios detalhados que estão organizados em pastas nomeadas por técnicas do MASTG (OWASP) e mitigações do MASVS (OWASP) neste repositório. Cada pasta contém:

- Descrição da vulnerabilidade: Detalhes sobre a vulnerabilidade identificada, incluindo como ela foi explorada e potenciais impactos.
- Mitigação: Sugestões e recomendações para mitigar ou corrigir a vulnerabilidade encontrada.
- Código contra vulnerabilidade: Exemplos de código ou instruções para implementar correções ou melhorias de segurança no aplicativo.
  
Este repositório serve como registro das análises de segurança realizadas pela nossa equipe durante a fase de desenvolvimento do RASP para o desafio Hakai.

# Entrega 02
Primeiramente realizamos pesquisas de soluções RASP (Runtime Application Self-Protection) Open Source que estão no mercado e fizemos um diagrama que contem:

- Áreas inseguras no InsecureBankv2
- Principais soluções para tais vulnerabilidades
- Soluções Open Source que oferecem a resolução do problema

# Entrega 03

Na terceira fase do desafio Hakai, a equipe Red Ribbon implementou diversas soluções de segurança no aplicativo mobile. As melhorias incluem:

- Anti-Debugger: Detecção e prevenção de depuração do aplicativo.
- Anti-Root: Verificação de dispositivos com acesso root e bloqueio de execução em tais dispositivos.
- Criptografia de Dados Locais: Proteção de dados armazenados localmente por meio de criptografia robusta.
- Ofuscação: Modificação de código para dificultar engenharia reversa.
- Hashing: Verificação de integridade de arquivos críticos, como MainActivity e build.gradle.
- Certificado SSL: Implementação de criptografia para comunicações entre o aplicativo e servidores.

Essas medidas visam fortalecer a segurança do aplicativo contra ameaças em tempo real.

# Entrega 04

Na quarta e última fase do desafio Hakai, a equipe Red Ribbon finalizou a implementação de todas as soluções de segurança planejadas para o projeto RASP. As principais entregas incluem:

Finalização das Soluções de Segurança: Concluímos o desenvolvimento das proteções contra debugger, root, ADB, Frida, entre outros. Essas medidas foram aplicadas ao aplicativo, visando proteger sua integridade em tempo real.

Criação da Biblioteca Red Ribbon: Consolidamos as soluções em uma biblioteca unificada e modular, facilitando a integração com outros projetos Android. A biblioteca está pronta para ser utilizada em diferentes aplicativos como uma camada adicional de segurança.

Apresentação à Hakai Security: Apresentamos as funcionalidades da biblioteca ao time da Hakai, explicando a aplicação de cada solução de segurança e os benefícios para proteção em tempo de execução.

Essa entrega marca a conclusão do desenvolvimento do SDK RASP, demonstrando nosso compromisso com a segurança e robustez de aplicativos mobile.


<div style="display: flex;">
  <img src="2.png" alt="Texto Alternativo" width="300px">
  <img src="3.png" alt="Texto Alternativo" width="300px">
</div>

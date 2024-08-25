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

# Roadmap até aqui!

<img src=".img/roadmap.png">

## Soluções de segurança feitas e implantadas

- Anti-Debugger: Implementar mecanismos que detectam tentativas de depuração e fecham o aplicativo se essas tentativas forem identificadas.

- Anti-Root: Adicionar verificações para identificar dispositivos com acesso root e impedir a execução do aplicativo em tais dispositivos. Assegurar que o aplicativo seja executado apenas em dispositivos não comprometidos.

- Criptografia de Dados Locais: Implementar criptografia robusta para dados armazenados localmente no dispositivo, garantindo que informações sensíveis estejam protegidas contra acesso não autorizado.

- Ofuscação: Utilizar técnicas de ofuscação para proteger o código do aplicativo contra engenharia reversa. Isso envolve a modificação dos nomes de classes e métodos para tornar o código mais difícil de ser compreendido por atacantes.
  
- Hashing: Adicionar verificação de integridade dos arquivos críticos do aplicativo, como MainActivity e build.gradle, calculando e comparando hashes para detectar modificações não autorizadas.

Com a implementação dessas soluções, nosso objetivo é fortalecer a segurança do aplicativo e proporcionar uma proteção eficaz contra ameaças em tempo real.

## Para última entrega

### KeyStore
Integrar o Android Keystore no aplicativo para gerenciar chaves criptográficas de forma segura.

### Integração e Testes
Garantir que todas as funcionalidades implementadas sejam integradas corretamente na nova biblioteca Red Ribbon. Realizar testes abrangentes para validar que todos os componentes funcionem em conjunto e que não haja regressões nas funcionalidades existentes.

### Documentação
Criar uma documentação detalhada para a biblioteca Red Ribbon, incluindo instruções sobre como importar e utilizar as funções disponíveis. Incluir exemplos de uso e melhores práticas para facilitar a adoção e implementação pelos desenvolvedores.

### Desempenho e Otimização 
Avaliar o impacto das novas funcionalidades no desempenho do aplicativo. Otimizar o código e as configurações para garantir que a biblioteca Red Ribbon ofereça uma proteção robusta sem comprometer a performance.

## Conclusão


- **Agradecimentos:**
  Agradecemos a Hakai Security pela oportunidade de participar deste desafio e aos avaliadores por seu tempo e consideração.

![image](https://github.com/MatheusRosa800/HakaiChallenge/assets/105319207/a2aa1d8e-6d24-48fa-a646-aaee07b63028) 
![OIG3-removebg-preview](https://github.com/MatheusRosa800/HakaiChallenge/assets/127846261/a48fd91e-3084-4313-a8a8-c8cd067cb7fb)





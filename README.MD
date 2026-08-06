# Chanllenge-java — Scammer Detector

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![License](https://img.shields.io/badge/license-MIT-blue)
![PRs](https://img.shields.io/badge/PRs-welcome-brightgreen)

> Uma ferramenta de linha de comando em **Java** que analisa mensagens e e-mails suspeitos, identificando **palavras-chave associadas a golpes digitais** (phishing, engenharia social, fraudes via Pix, falsas promoções, etc.) e retornando uma pontuação de risco.

---

## 📑 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como Funciona](#-como-funciona)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Exemplo de Execução](#-exemplo-de-execução)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Status do Projeto](#-status-do-projeto)
- [Roadmap](#-roadmap)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)
- [Contato](#-contato)

---

## 📖 Sobre o Projeto

O **Chanllenge-java** nasceu com o objetivo de ajudar usuários a identificar mensagens de texto e e-mails com **indícios de golpe digital** antes que caiam em fraudes. A aplicação recebe um texto colado pelo usuário, normaliza o conteúdo (removendo acentuação e diferenças de caixa) e o compara com um dicionário de palavras e expressões comumente usadas em golpes, atribuindo uma pontuação de risco a cada uma.

Ao final da análise, o sistema informa se a mensagem é **suspeita de golpe** e, opcionalmente, exibe recomendações de segurança para o usuário.

---

## ✨ Funcionalidades

- 🔍 **Analisar mensagens/e-mails**: cole qualquer texto e receba uma análise automática de risco.
- 📊 **Pontuação de risco**: cada palavra-chave encontrada soma pontos de acordo com seu nível de gravidade (`golpe`, `suspeito`, `menor risco`, `apoio`).
- 🚨 **Classificação automática**: com base em um limiar de pontuação, o sistema indica se a mensagem é suspeita ou não.
- 🛡️ **Recomendações de segurança**: dicas práticas exibidas ao final da análise, caso o usuário deseje.
- 🔤 **Normalização de texto**: remove acentos e ignora diferenças entre maiúsculas/minúsculas para melhorar a detecção.

---

## 🧰 Tecnologias Utilizadas

- **[Java](https://www.oracle.com/java/)** (17 ou superior recomendado)
- API `java.util.Scanner` para entrada via terminal
- API `java.text.Normalizer` para tratamento de texto
- Estrutura de dados `HashMap` para o dicionário de palavras-chave

---

## ⚙️ Como Funciona

1. O usuário cola a mensagem/e-mail suspeito no terminal.
2. O texto é normalizado (sem acentos, em minúsculas).
3. O sistema percorre um dicionário de palavras-chave (`PalavrasChave.java`), somando a pontuação de cada termo encontrado.
4. Se a pontuação total atingir o limiar definido (`LIMIAR_SUSPEITO`), a mensagem é classificada como **suspeita de golpe**.
5. O usuário pode optar por visualizar recomendações de segurança.

---

## 🧑‍💻 Como Usar

Ao iniciar o programa, você verá o seguinte menu:

```
=========================================
             SCAMMER DETECTOR
=========================================

Cole abaixo a mensagem ou e-mail que deseja verificar.
```

Basta colar o texto suspeito e pressionar **Enter**. O sistema exibirá a pontuação de risco e a classificação da mensagem, além de perguntar se você deseja visualizar recomendações de segurança.

---

## 💻 Exemplo de Execução

**Entrada:**

```
Sua conta foi invadida! Clique aqui para confirmar seus dados e evitar o bloqueio.
```

**Saída:**

```
Estamos verificando, aguarde...

Pontuação de risco: 12
Com base na pontuação atingida, avaliamos que este email é Suspeito de Golpe!

Deseja receber recomendações de segurança?
- Sim
- Não
Escolha uma opção: sim

=========================================
      RECOMENDAÇÕES DE SEGURANÇA
=========================================
✔ Não clique em links suspeitos.
✔ Nunca informe códigos recebidos por SMS.
✔ Confirme a informação pelo site oficial.
✔ Verifique o remetente antes de responder.
✔ Em caso de dúvida, ignore a mensagem.
=========================================
```

---

## 📂 Estrutura do Projeto

```
Chanllenge-java/
├── Main.java              # Ponto de entrada da aplicação (interface via terminal)
├── ScammerDetector.java   # Lógica de análise e cálculo de pontuação de risco
├── PalavrasChave.java     # Dicionário de palavras-chave e seus pesos
└── README.md              # Documentação do projeto
```

---

## 📌 Status do Projeto

> 🚧 **Em desenvolvimento**

O projeto está em constante evolução. Novas palavras-chave, melhorias na precisão da detecção e novas funcionalidades estão sendo planejadas.

---

## 🗺️ Roadmap

- [ ] Suporte à leitura de arquivos `.txt` e `.eml`
- [ ] Interface gráfica (GUI) ou versão web
- [ ] Exportação de relatórios de análise
- [ ] Ampliação da base de palavras-chave
- [ ] Testes automatizados (JUnit)

---

## 🤝 Contribuindo

Contribuições são muito bem-vindas! Para contribuir:

1. Faça um **fork** do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Faça o **commit** das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Faça o **push** para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um **Pull Request**

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 📬 Contato

Desenvolvido por **André  Pazini e Assis Pires Neto**

- GitHub: [@PaziniHoch](https://github.com/pazinihoch)
- E-mail: pazinihoch@gmail.com
- LinkedIn: [seu-perfil](https://linkedin.com/in/seu-perfil)

- GitHub: [@Assis](https://github.com/lancelot)
- E-mail: assis.pires.netors@gmail.com
- LinkedIn: [Assis pires Neto](https://linkedin.com/in/assispiresneto)

---

<p align="center">Feito com ☕ e Java para tornar a internet um pouco mais segura.</p>

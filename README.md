# Order Observability Lab

## Visão Geral

O **Order Observability Lab** é um projeto de estudo que demonstra a implementação de **observabilidade de logs em uma arquitetura de microserviços** utilizando **Spring Boot, Kubernetes e Elastic Stack**.

O projeto simula um fluxo simples de negócio relacionado ao processamento de pedidos, permitindo observar como eventos e falhas se propagam entre serviços distribuídos e como essas informações podem ser coletadas, indexadas e analisadas por meio de ferramentas de observabilidade.

O laboratório tem como foco principal a **gestão estruturada de logs**, aplicando boas práticas utilizadas em ambientes modernos de microserviços e plataformas de observabilidade.

---

# Objetivos do Projeto

Este projeto tem como principais objetivos:

* Implementar **logging estruturado padronizado**
* Demonstrar **correlação distribuída de requisições**
* Construir um **pipeline completo de ingestão de logs**
* Permitir **análise centralizada de eventos e falhas**
* Simular cenários operacionais para estudo de diagnóstico

A solução foi projetada para reproduzir, em escala reduzida, padrões utilizados em ambientes de produção.

---

# Arquitetura do Projeto

O sistema é composto por múltiplos microserviços que processam requisições de pedidos e geram logs estruturados durante sua execução.

Fluxo principal de processamento:

```text
Client → API Gateway → Order Service → Payment Service → Notification Service
```

Cada serviço gera logs estruturados contendo o mesmo identificador de correlação, permitindo reconstruir o fluxo completo da requisição.

---

# Pipeline de Observabilidade

Os logs produzidos pelos serviços são coletados e processados por uma stack de observabilidade baseada no Elastic Stack.

Fluxo de ingestão de logs:

```text
Microservices → stdout → Filebeat → Elasticsearch → Kibana
```

Componentes utilizados:

* **Filebeat** – coleta logs dos containers
* **Elasticsearch** – indexação e armazenamento
* **Kibana** – visualização e análise de logs

---

# Estrutura do Projeto

O repositório está organizado da seguinte forma:

```text
order-observability-lab
│
├── services
│   ├── api-gateway
│   ├── order-service
│   ├── payment-service
│   └── notification-service
│
├── observability
│   ├── elasticsearch
│   ├── kibana
│   └── filebeat
│
├── infrastructure
│   ├── docker
│   └── kubernetes
│
├── docs
│   ├── architecture
│   ├── observability
│   ├── log-contract
│   └── roadmap
│
└── README.md
```

Essa estrutura visa separar e organizar:

* **código de aplicação**
* **infraestrutura**
* **configuração de observabilidade**
* **documentação**

---

# Log Contract

Todos os microserviços seguem um contrato de logs estruturados definido no projeto.

O contrato estabelece:

* estrutura padrão dos logs
* campos obrigatórios
* classificação de eventos
* estratégia de indexação
* política de log levels

Documento completo disponível em:

```
docs/log-contract/log-contract-v1.1.md
```

---

# Roadmap de Implementação

O desenvolvimento do laboratório segue um roadmap incremental composto por oito fases:

1. Fundamentos de Logging
2. Implementação do Primeiro Serviço
3. Containerização
4. Pipeline de Logs
5. Kubernetes
6. Arquitetura Distribuída
7. Observabilidade Operacional
8. Maturidade de Logs

Detalhes completos disponíveis em:

```
docs/roadmap/roadmap.md
```

---

# Tecnologias Utilizadas

O projeto utiliza as seguintes tecnologias:

* **Java 17**
* **Spring Boot**
* **Docker**
* **Kubernetes**
* **Filebeat**
* **Elasticsearch**
* **Kibana**

Essas ferramentas compõem uma stack amplamente utilizada para observabilidade em arquiteturas de microserviços.

---

# Resultados Esperados

Ao final da implementação, o laboratório permitirá:

* rastrear requisições entre múltiplos serviços
* identificar erros técnicos e eventos de negócio
* analisar latência de operações
* visualizar logs em dashboards no Kibana
* simular cenários de falha para estudo operacional

---

# Como Executar o Projeto

As instruções detalhadas de execução serão adicionadas conforme as fases do roadmap forem concluídas.

O objetivo é permitir a execução do ambiente tanto em **Docker Compose (local)** quanto em **Kubernetes**.

---

# Status do Projeto

Progresso atual:

* [x] Fase 1 — Fundamentos de Logging
* [ ] Fase 2 — Implementação do Primeiro Serviço
* [ ] Fase 3 — Containerização
* [ ] Fase 4 — Pipeline de Logs
* [ ] Fase 5 — Kubernetes
* [ ] Fase 6 — Arquitetura Distribuída
* [ ] Fase 7 — Observabilidade Operacional
* [ ] Fase 8 — Maturidade de Logs

---

# Contribuição

Este projeto é um laboratório educacional voltado para estudo de observabilidade em sistemas distribuídos.

Sugestões de melhorias e experimentações são bem-vindas.

---

# Licença

Este projeto é disponibilizado para fins educacionais.

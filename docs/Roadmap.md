# 🎯 Roadmap — Order Observability Lab (14 dias)

📌 Descrição Resumida do Roadmap

O roadmap do ``Order Observability Lab`` é um plano de 2 semanas para construir, de forma progressiva, um ambiente completo de gestão de logs em arquitetura de microserviços utilizando Spring Boot, Kubernetes e Elastic Stack.

A abordagem segue uma evolução técnica controlada:

```console
Fundação primeiro – Definir um padrão estruturado de logs (JSON), com governança clara de campos, níveis e classificação de eventos.

Implementação isolada – Desenvolver um único serviço (order-service) com logging estruturado e correlação via correlationId.

Pipeline de ingestão – Containerizar a aplicação e integrar com Elasticsearch e Kibana via Filebeat.

Clusterização – Migrar o ambiente para Kubernetes, garantindo coleta correta dos logs dos pods.

Evolução distribuída – Adicionar novos microserviços e validar correlação ponta a ponta.

Maturidade operacional – Criar dashboards, definir estratégia de indexação, aplicar ILM e simular incidentes.
```


Ao final, o projeto entrega um ambiente funcional capaz de:

```console
Rastrear requisições distribuídas apenas via logs

Diferenciar eventos de negócio e erros técnicos

Medir falhas e latência por serviço

Aplicar boas práticas reais de observabilidade
```
>[!NOTE]
>Em resumo, o roadmap conduz da base estrutural até um cenário próximo de produção, com foco em qualidade, governança e análise operacional de logs.

## Semana 1 — Fundamentos Sólidos

### 🥇 Dia 1 — Definição Arquitetural

Objetivo: Definir padrão organizacional de logs.

Entregáveis:

Documento com:

```console
    - Estrutura JSON padrão
    - Campos obrigatórios
    - Convenção de nomenclatura
    - Classificação de eventos (business | technical)
    - Política de log level
```


Decisões a tomar:

```console
    - Índice único vs índice por serviço
    - Uso de padrão próximo do ECS
    - Campos enriquecidos obrigatórios
```

Obs: Sem código ainda.

### 🥈 Dia 2 — Criar order-service (Base)

Tecnologia:

```console
    - Spring Boot
    - Java 17+
    - logstash-logback-encoder
```

Implementar:

```console
    - Endpoint /orders
    - Geração de correlationId
    - MDC configurado
    - Logs estruturados JSON
```

Validar:

```console
    - Logs aparecem corretamente no console
    - Campos corretos
    - Nenhum log “solto” sem estrutura
```

### 🥉 Dia 3 — Simulação de Cenários

Adicionar:

```console
    - Endpoint sucesso
    - Endpoint erro de negócio (ex: pagamento recusado)
    - Endpoint erro técnico (exceção simulada)
    - Log de duração da requisição
```

Aprender:

```console
    - INFO vs WARN vs ERROR
    - Separação business x technical
    - Logging de exceção com stacktrace estruturado
```
  
### 🏗️ Dia 4 — Containerização

Criar:

```console
    - Dockerfile
    - Build da imagem
    - Teste via docker run
```

Validar:

```console
    - docker logs mantém JSON válido
    - Nada quebra no ambiente containerizado
```

### 📦 Dia 5 — Elastic Stack Local (Docker Compose)

Subir:

```console
    - Elasticsearch
    - Kibana
    - Filebeat
```

Pipeline:

```console
    - Container → stdout → Filebeat → Elasticsearch
```

Configurar:

```console
    - filebeat.yml
    - output para Elasticsearch
    - index pattern no Kibana
```

Validar:

```console
    - Logs indexados
    - Busca por correlationId
    - Filtro por service.name
```

## 🟢 Semana 2 — Evolução para Arquitetura Distribuída
### ☸️ Dia 6 — Migrar para Kubernetes (1 serviço)

Instalar:

```console
Docker Desktop com Kubernetes
ou

kind
```

Criar:

```console
Deployment do order-service

Service

Filebeat como DaemonSet
```

Validar:

```console
Logs lidos de /var/log/containers

Indexação funcionando
```

### 🧩 Dia 7 — Adicionar payment-service

Implementar:

```console
Endpoint /pay

Simular:

sucesso

recusa

timeout aleatório
```

Adicionar:

```console
Propagação de correlationId via header HTTP
```

Validar:

```console
Uma requisição gera logs em 2 serviços

Correlação funciona no Kibana
```

### 🧠 Dia 8 — Adicionar notification-service

Simular:

```console
Retry

Falha intermitente
```

Aprender:

```console
Logar tentativas

Logar número de retry

Logar causa final
```

### 🔎 Dia 9 — Estratégia de Indexação

Testar:

```console
Opção A

Índice por serviço

Opção B

Índice único com campo service.name
```

Comparar:

```console
Facilidade de query

Performance

Organização

Decidir padrão final.
```

### 📊 Dia 10 — Dashboards no Kibana

Criar:

```console
Erros por serviço

Taxa de falha

Distribuição por log level

Latência média

Top erros
```

Criar visualizações:

```console
Pie por log.level

Histogram por @timestamp

Tabela por correlationId
```

### 🚨 Dia 11 — Simular Incidente

Criar cenário:

```console
payment-service começa a falhar 50% das vezes
```

Analisar:

```console
Como detectar?

Como identificar causa?

Como rastrear ponta a ponta?

Isso é treino SRE real.
```

### ⚙️ Dia 12 — Ingest Pipeline

Criar pipeline no Elasticsearch para:

```console
Normalizar campos

Remover campos inúteis

Enriquecer dados (ex: environment)

Aplicar pipeline ao índice.
```

### 🗂️ Dia 13 — ILM (Index Lifecycle Management)

Definir:

```console
Hot phase

Retenção curta (ex: 7 dias)

Política de rollover

Aplicar ao índice.
```

### 🎯 Dia 14 — Revisão Arquitetural

Perguntas críticas:

```console
Logs estão consistentes?

Existe padrão claro?

Correlação é confiável?

Separação business x technical está correta?

Seria auditável em produção?

Documentar aprendizados.
```

## 📈 Resultado ao Final

Objetivo final é ter:

```console
3 microserviços

Kubernetes rodando local

Filebeat como agente

Elasticsearch configurado corretamente

Dashboards funcionais

Estratégia de indexação definida

Política de retenção aplicada

Correlação distribuída funcionando

Isso já é nível profissional de observabilidade.
```
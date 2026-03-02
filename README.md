
# Projeto: Order Observability Lab

## Descrição resumida

O Order Observability Lab é um laboratório de microserviços desenvolvido em Java + Spring Boot, executando em Kubernetes, com foco exclusivo em gestão e observabilidade de logs utilizando Elastic Stack (Elasticsearch + Kibana) e Filebeat.

A proposta é simular um fluxo real de negócio — criação de pedidos — envolvendo múltiplos serviços que produzem logs estruturados e correlacionados.

## Arquitetura

Fluxo principal:

```bash
Client → API Gateway → Order Service → Payment Service → Notification Service
```

Pipeline de logs:

```bash
Pods → stdout (JSON estruturado) → Filebeat → Elasticsearch → Kibana
```

## Objetivo Técnico

Demonstrar na prática:

```console
Logging estruturado em JSON

Correlação distribuída via correlationId

Separação entre eventos de negócio e erros técnicos

Estratégia de indexação no Elasticsearch

Consulta e análise de logs no Kibana

Simulação de falhas (timeout, erro técnico, erro de negócio)
```

## Componentes

```console
api-gateway → Gera e propaga correlationId

order-service → Orquestra a criação do pedido

payment-service → Simula aprovação/recusa/timeout

notification-service → Simula envio de notificação
```

## Resultado Esperado

Ao final do projeto, será possível:

```console
Rastrear uma requisição ponta a ponta apenas pelos logs

Identificar falhas por serviço

Medir latência

Visualizar erros e eventos de negócio no Kibana

Aplicar boas práticas de observabilidade em ambiente distribuído
```
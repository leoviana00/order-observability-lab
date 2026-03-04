# Diagrama de Arquitetura — Order Observability Lab

## Visão Geral

O sistema simula uma arquitetura de microserviços onde cada serviço gera logs estruturados em JSON, coletados por Filebeat e enviados para Elasticsearch, sendo analisados no Kibana.

## Arquitetura do Sistema

```console

                          ┌─────────────────────────┐
                          │        CLIENT           │
                          └─────────────┬───────────┘
                                        │
                                        │ HTTP Request
                                        ▼
                          ┌─────────────────────────┐
                          │       API GATEWAY       │
                          │                         │
                          │ - Gera correlation.id   │
                          │ - Propaga headers       │
                          └─────────────┬───────────┘
                                        │
                                        │
                                        ▼
                         ┌───────────────────────────┐
                         │       ORDER SERVICE       │
                         │                           │
                         │ - Criação de pedidos      │
                         │ - Logs de eventos         │
                         └─────────────┬─────────────┘
                                       │
                    ┌──────────────────┴──────────────────┐
                    │                                     │
                    ▼                                     ▼
        ┌──────────────────────┐             ┌──────────────────────┐
        │    PAYMENT SERVICE   │             │  NOTIFICATION SERVICE│
        │                      │             │                      │
        │ - Aprovação pagamento│             │ - Envio de email     │
        │ - Timeout simulado   │             │ - Retry simulado     │
        └───────────┬──────────┘             └───────────┬──────────┘
                    │                                    │
                    └──────────────┬─────────────────────┘
                                   │
                                   ▼
                         Logs estruturados JSON

```

## Pipeline de Observabilidade

```console

                 Kubernetes Cluster
┌─────────────────────────────────────────────────────────────┐

   Pods
   │
   │ stdout logs
   ▼

┌─────────────────────┐
│      FILEBEAT       │
│  (DaemonSet)        │
│                     │
│ Lê logs de:         │
│ /var/log/containers │
└───────────┬─────────┘
            │
            │ envio
            ▼

┌─────────────────────┐
│   ELASTICSEARCH     │
│                     │
│ Indexação de logs   │
│                     │
│ logs-microservices-*│
└───────────┬─────────┘
            │
            │ consulta
            ▼

┌─────────────────────┐
│       KIBANA        │
│                     │
│ - Discover          │
│ - Dashboards        │
│ - Análise de erros  │
└─────────────────────┘

└─────────────────────────────────────────────────────────────┘
```

## Fluxo de Correlação de Logs

```console

Client Request
      │
      ▼
API Gateway
      │
      │  X-Correlation-Id: abc-123
      ▼
Order Service
      │
      │  X-Correlation-Id: abc-123
      ▼
Payment Service
      │
      │  X-Correlation-Id: abc-123
      ▼
Notification Service
```

Todos os logs compartilham:

```json
"correlation.id": "abc-123"
```

Isso permite rastrear uma requisição completa no Kibana.

## Estrutura de Componentes

```console
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
├── kubernetes
│   ├── deployments
│   ├── services
│   └── daemonsets
│
└── docs
    ├── log-contract.md
    └── roadmap.md
```

## Resultado Esperado da Arquitetura

O laboratório permitirá:

- rastrear requisições distribuídas via logs
- analisar falhas por serviço
- visualizar eventos de negócio
- estudar ingestão de logs em Kubernetes
- aplicar boas práticas de observabilidade
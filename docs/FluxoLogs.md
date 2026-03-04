# Diagrama de Fluxo de Logs — Pontos de Observabilidade

## Objetivo

Identificar em quais momentos do fluxo de requisição os logs devem ser emitidos.

Isso define eventos observáveis dentro do sistema.

## Fluxo de Requisição com Pontos de Log

```console
Client Request
     │
     ▼
┌───────────────────────────┐
│        API GATEWAY        │
│                           │
│ LOG 1 - request_received  │
│ - http.method             │
│ - http.path               │
│ - correlation.id          │
│                           │
│ LOG 2 - request_forwarded │
│ - target.service          │
│ - duration.ms             │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│       ORDER SERVICE       │
│                           │
│ LOG 3 - order_request     │
│ - orderId                 │
│ - correlation.id          │
│                           │
│ LOG 4 - payment_request   │
│ - target: payment-service │
│                           │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│      PAYMENT SERVICE      │
│                           │
│ LOG 5 - payment_attempt   │
│                           │
│ LOG 6 - payment_result    │
│ - approved | refused      │
│                           │
│ LOG 7 - payment_error     │
│ - timeout                 │
│ - provider error          │
└─────────────┬─────────────┘
              │
              ▼
┌───────────────────────────┐
│   NOTIFICATION SERVICE    │
│                           │
│ LOG 8 - notification_send │
│                           │
│ LOG 9 - notification_retry│
│                           │
│ LOG 10 - notification_ok  │
└─────────────┬─────────────┘
              │
              ▼
      Response to Client
```

## Classificação dos Logs no Fluxo

| Log                | Evento                 | Tipo      |
| ------------------ | ---------------------- | --------- |
| request_received   | entrada da requisição  | business  |
| request_forwarded  | roteamento gateway     | technical |
| order_request      | criação do pedido      | business  |
| payment_attempt    | tentativa de pagamento | business  |
| payment_result     | resultado pagamento    | business  |
| payment_error      | erro técnico           | technical |
| notification_send  | envio notificação      | business  |
| notification_retry | retry de envio         | technical |
| notification_ok    | confirmação envio      | business  |

## Exemplo de Log no Fluxo


### Evento de negócio
```json
{
  "@timestamp": "2026-03-04T14:10:11Z",
  "service.name": "order-service",
  "log.level": "INFO",
  "message": "Order created",

  "correlation.id": "abc-123",

  "event.type": "business",
  "event.name": "order_created",

  "http.method": "POST",
  "http.path": "/orders",
  "http.status_code": 201,
  "duration.ms": 45
}
```

### Evento técnico
```json
{
  "@timestamp": "2026-03-04T14:10:13Z",
  "service.name": "payment-service",
  "log.level": "ERROR",
  "message": "Timeout calling payment provider",

  "correlation.id": "abc-123",

  "event.type": "technical",
  "event.name": "payment_timeout",

  "error.type": "TimeoutException",
  "error.message": "Provider timeout"
}
```
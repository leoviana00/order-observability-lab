# 📄 Log Contract v1.1
```console
Projeto: Order Observability Lab
Versão: 1.1
Data: 2026-03-03
Escopo: Microserviços Spring Boot executando em Kubernetes com ingestão via Filebeat e indexação no Elasticsearch.
```

## 1. Objetivo

Estabelecer um padrão obrigatório de logs estruturados para todos os microserviços, garantindo:

```console
Consistência estrutural

Correlação distribuída

Separação clara entre eventos de negócio e técnicos

Compatibilidade com Elastic Stack

Governança mínima de observabilidade
```

## 2. Decisões Arquiteturais (Formalizadas)

### 2.1 Estratégia de Indexação

🔹 Ambiente Local / Laboratório

Índice único:

```console
logs-microservices-local-*
```
>[!NOTE]
>Separação lógica via campo service.name.

🔹 Ambiente Produção (Diretriz Futura)

Índice por domínio ou serviço:

```console
logs-order-service-*
logs-payment-service-*
logs-notification-service-*
```

Critério:

```console
Serviços críticos ou de alto volume devem possuir índice dedicado.
```

### 2.2 Aderência ao ECS

- O contrato adota aderência parcial ao ECS v8 do Elastic.

Política:

```console
Campos estruturais seguem nomenclatura ECS (service.name, event.type, error.*, http.*)

Não é obrigatória compatibilidade total

Campos customizados são permitidos, desde que não conflitem com ECS
```

Nível de aderência:

- ECS-Inspired (Compatibilidade Parcial)

### 2.3 Política de Enriquecimento

Logs devem conter contexto mínimo operacional.

Campos agora obrigatórios:
| Campo           | Obrigatório |
| --------------- | ----------- |
| @timestamp      | ✅           |
| service.name    | ✅           |
| service.version | ✅           |
| environment     | ✅           |
| log.level       | ✅           |
| message         | ✅           |
| correlation.id  | ✅           |
| event.type      | ✅           |

Campos Condicionalmente Obrigatórios

| Campo            | Quando obrigatório         |
| ---------------- | -------------------------- |
| http.method      | Se for requisição HTTP     |
| http.path        | Se for requisição HTTP     |
| http.status_code | Se for requisição HTTP     |
| duration.ms      | Se for operação mensurável |
| error.*          | Se houver erro             |

>[!NOTE]
>Agora o log sempre carrega contexto mínimo de ambiente e versão.

## 3. Estrutura Oficial do Log

```JSON
{
  "@timestamp": "ISO-8601",
  "service.name": "string",
  "service.version": "string",
  "environment": "local|dev|prod",

  "log.level": "DEBUG|INFO|WARN|ERROR",
  "message": "string",

  "correlation.id": "string",
  "trace.id": "string|null",

  "event.type": "business|technical",
  "event.name": "string|null",

  "http.method": "string|null",
  "http.path": "string|null",
  "http.status_code": "number|null",
  "duration.ms": "number|null",

  "error.type": "string|null",
  "error.message": "string|null",
  "error.stack_trace": "string|null"
}
```

## 4. Classificação Oficial

### 4.1 event.type = business

Eventos esperados do domínio.

Log level permitido:
- `INFO`
- `WARN` (quando indesejado porém esperado)

### 4.2 event.type = technical

Falhas técnicas.

Log level permitido:
- `ERROR`
- `WARN` (quando recuperável)

## 5. Correlação Distribuída

Header padrão:

```console
X-Correlation-Id
```

Regras:

```console
Gerado no API Gateway
Propagado entre serviços
Nunca deve ser nulo
Se ausente, deve ser gerado
```

## 6. Política de Log Level

| Nível | Uso                            |
| ----- | ------------------------------ |
| DEBUG | Diagnóstico técnico            |
| INFO  | Evento de negócio              |
| WARN  | Situação inesperada controlada |
| ERROR | Falha técnica não recuperável  |

>[!NOTE]
>Uso incorreto de nível é considerado não conformidade.

## 7. Regras de Segurança

Proibido logar:

- Senhas
- Tokens completos
- Dados financeiros sensíveis
- Informações pessoais críticas

>[!NOTE]
>Campos sensíveis devem ser mascarados.

## 8. Critérios de Conformidade v1.1

Um serviço é considerado aderente quando:

- 100% dos logs são JSON estruturado
- Campos obrigatórios sempre presentes
- correlation.id sempre propagado
- Campos enriquecidos presentes
- Nenhum log textual fora do padrão

## 📌 Evolução Futuras Previstas

- ILM obrigatório
- Ingest Pipeline para normalização
- Trace ID via OpenTelemetry
- Campos de cluster Kubernetes (pod.name, namespace)
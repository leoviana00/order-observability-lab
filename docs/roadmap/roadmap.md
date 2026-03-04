# Roadmap de Implementação

Este roadmap define as etapas necessárias para construção do **Order Observability Lab**, desde a definição do padrão de logs até a implementação completa do pipeline de observabilidade.

O desenvolvimento será realizado de forma incremental, permitindo validar cada componente antes da evolução para as próximas fases.

---

# Visão Geral das Fases

| Fase | Nome                              | Objetivo                                           |
| ---- | --------------------------------- | -------------------------------------------------- |
| 1    | Fundamentos de Logging            | Definir padrão de logs e contrato de logging       |
| 2    | Implementação do Primeiro Serviço | Implementar logging estruturado em um microserviço |
| 3    | Containerização                   | Preparar aplicação para execução em containers     |
| 4    | Pipeline de Logs                  | Implementar ingestão de logs com Elastic Stack     |
| 5    | Kubernetes                        | Executar serviços em cluster Kubernetes            |
| 6    | Arquitetura Distribuída           | Introduzir múltiplos microserviços                 |
| 7    | Observabilidade Operacional       | Criar dashboards e análises                        |
| 8    | Maturidade de Logs                | Aplicar governança de ingestão e retenção          |

---

# Fase 1 — Fundamentos de Logging

## Objetivo

Definir o padrão organizacional de logs estruturados para todos os serviços do projeto.

## Atividades

* Definir estrutura JSON de logs
* Definir campos obrigatórios e condicionais
* Definir política de log levels
* Definir classificação de eventos (business e technical)
* Formalizar decisões arquiteturais de indexação e aderência ao ECS

## Entregáveis

* Documento **Log Contract v1.1**

## Definition of Done

* Estrutura de logs definida
* Campos obrigatórios formalizados
* Exemplos de logs validados
* Documento de contrato publicado

---

# Fase 2 — Implementação do Primeiro Serviço

## Objetivo

Implementar o **Log Contract** em um microserviço inicial.

## Atividades

* Criar o `order-service` utilizando Spring Boot
* Configurar geração de logs estruturados em JSON
* Implementar geração e propagação de `correlation.id`
* Criar endpoints de teste
* Registrar eventos de negócio e técnicos

## Entregáveis

* Serviço funcional com logs estruturados

## Definition of Done

* Logs seguem o Log Contract
* Campos obrigatórios presentes
* Correlação de requisições funcionando

---

# Fase 3 — Containerização

## Objetivo

Preparar o serviço para execução em containers.

## Atividades

* Criar Dockerfile
* Construir imagem Docker
* Executar container localmente
* Validar logs via `docker logs`

## Entregáveis

* Imagem Docker do serviço

## Definition of Done

* Container executando corretamente
* Logs estruturados visíveis

---

# Fase 4 — Pipeline de Logs

## Objetivo

Implementar a infraestrutura de ingestão de logs.

## Atividades

* Subir Elasticsearch
* Subir Kibana
* Configurar Filebeat
* Criar index pattern
* Validar ingestão de logs

## Entregáveis

* Pipeline de logs funcional

## Definition of Done

* Logs indexados no Elasticsearch
* Logs consultáveis no Kibana
* Filtros por `correlation.id` funcionando

---

# Fase 5 — Kubernetes

## Objetivo

Executar o sistema em um ambiente Kubernetes.

## Atividades

* Criar cluster Kubernetes local
* Criar Deployment do `order-service`
* Criar Service Kubernetes
* Implantar Filebeat como DaemonSet

## Entregáveis

* Serviço rodando em Kubernetes

## Definition of Done

* Logs de pods coletados corretamente
* Pipeline de ingestão funcionando no cluster

---

# Fase 6 — Arquitetura Distribuída

## Objetivo

Simular um sistema distribuído com múltiplos microserviços.

## Atividades

* Criar `payment-service`
* Criar `notification-service`
* Implementar propagação de `correlation.id`
* Validar rastreamento de requisições

## Entregáveis

* Sistema com múltiplos microserviços integrados

## Definition of Done

* Requisições rastreáveis entre serviços
* Logs correlacionados no Kibana

---

# Fase 7 — Observabilidade Operacional

## Objetivo

Criar visibilidade operacional do sistema.

## Atividades

* Criar dashboards no Kibana
* Analisar erros por serviço
* Visualizar latência das requisições
* Criar consultas de investigação

## Entregáveis

* Dashboard de observabilidade

## Definition of Done

* Métricas de logs visualizáveis
* Investigação de falhas possível via Kibana

---

# Fase 8 — Maturidade de Logs

## Objetivo

Aplicar práticas avançadas de governança de logs.

## Atividades

* Criar ingest pipeline no Elasticsearch
* Implementar Index Lifecycle Management (ILM)
* Definir política de retenção de logs
* Revisar aderência ao Log Contract

## Entregáveis

* Pipeline de ingestão configurado
* Política de retenção aplicada

## Definition of Done

* Logs normalizados
* Retenção automática configurada






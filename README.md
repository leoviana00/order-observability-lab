
# Projeto: Order Observability Lab

## 1️⃣ Introdução

O Order Observability Lab é um laboratório de microserviços desenvolvido em Java + Spring Boot, executando em Kubernetes, com foco exclusivo em gestão e observabilidade de logs utilizando Elastic Stack (Elasticsearch + Kibana) e Filebeat.

A proposta é simular um fluxo real de negócio — criação de pedidos — envolvendo múltiplos serviços que produzem logs estruturados e correlacionados.


## 2️⃣ Objetivo do projeto

O Order Observability Lab tem como objetivo construir um ambiente de estudo prático para gestão e observabilidade de logs em arquiteturas de microserviços, utilizando Java Spring Boot, Kubernetes e Elastic Stack.

O projeto simula um sistema distribuído simples composto por múltiplos serviços que se comunicam entre si durante o fluxo de criação de pedidos. Cada serviço gera logs estruturados em JSON, seguindo um contrato padronizado de logging, permitindo a coleta, indexação e análise centralizada dessas informações.

A iniciativa busca demonstrar, em escala reduzida, como implementar um pipeline completo de ingestão de logs, desde a geração nos microserviços até a visualização e análise em ferramentas de observabilidade.

Mais detalhes a respeito dos [objetivos](./docs/Objetivo.md)

## 3️⃣ Arquitetura geral

Arquitetura Geral do Projeto

O Order Observability Lab é estruturado como um sistema de microserviços executando em ambiente containerizado e orquestrado por Kubernetes, com um pipeline dedicado para ingestão, indexação e análise de logs.

A arquitetura foi projetada para simular um fluxo real de negócio — criação de pedidos — permitindo observar como eventos e falhas se propagam entre serviços distribuídos e como essas informações podem ser coletadas e analisadas por meio de ferramentas de observabilidade

Mais detalhes a respeito da [arquitetura](./docs/Arquitetura.md)

## 4️⃣ Fluxo de observabilidade 

Esta seção descreve como os eventos observáveis são gerados, coletados e analisados ao longo do fluxo de processamento de uma requisição no sistema.

O objetivo é garantir que cada etapa relevante da execução de uma requisição seja registrada por meio de logs estruturados, permitindo rastreamento distribuído, diagnóstico de falhas e análise operacional.

Todos os logs seguem o Log Contract v1.1, garantindo consistência estrutural e capacidade de correlação entre serviços.

Mais detalhes a respeito do [fluxo de obsevabilidade](./docs/FluxoLogs.md)

## 5️⃣ Log Contract

O Log Contract define o padrão oficial de estrutura e conteúdo dos logs produzidos pelos microserviços do projeto.
Seu objetivo é garantir consistência, rastreabilidade e capacidade de análise dos logs dentro do pipeline de observabilidade.

Todos os serviços do sistema devem gerar logs estruturados em JSON, seguindo este contrato.

Mais detalhes do [Log Contract](./docs/LogContractV1.md)

## 6️⃣ Roadmap de implementação

Este roadmap define as etapas necessárias para construção do Order Observability Lab, desde a definição do padrão de logs até a implementação completa do pipeline de observabilidade.

O desenvolvimento será realizado de forma incremental, permitindo validar cada componente antes da evolução para as próximas fases. Mais detalhes das [objetivos, atividades, entregáveis e dependências](./docs/RoadmapDeImplementacao.md)

### Status Geral do Projeto

Utilize a checklist abaixo para acompanhar o progresso da implementação.

* [x] Fase 1 — Fundamentos de Logging
* [ ] Fase 2 — Implementação do Primeiro Serviço
* [ ] Fase 3 — Containerização
* [ ] Fase 4 — Pipeline de Logs
* [ ] Fase 5 — Kubernetes
* [ ] Fase 6 — Arquitetura Distribuída
* [ ] Fase 7 — Observabilidade Operacional
* [ ] Fase 8 — Maturidade de Logs
---

<div align="center">DevOps <a href="https://github.com/leoviana00">Leonardo Viana</a>.</div>
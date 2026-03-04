# Objetivo do Projeto

## Objetivos Técnicos

O projeto tem como principais objetivos técnicos:

* Implementar **logging estruturado padronizado** para microserviços
* Garantir **correlação distribuída de requisições** por meio de `correlation.id`
* Construir um **pipeline de ingestão de logs** utilizando Filebeat, Elasticsearch e Kibana
* Permitir **análise centralizada de logs** para diagnóstico de falhas e rastreamento de requisições
* Aplicar boas práticas de observabilidade em ambientes containerizados

---

## Resultados Esperados

Ao final da implementação, o laboratório permitirá:

* Rastrear uma requisição completa entre múltiplos serviços utilizando logs
* Identificar erros técnicos e eventos de negócio no sistema
* Analisar latência e comportamento de serviços distribuídos
* Visualizar logs e métricas operacionais em dashboards no Kibana
* Simular cenários de falha para estudo de diagnóstico operacional

---

## Escopo do Laboratório

O projeto inclui:

* Microserviços desenvolvidos em **Spring Boot**
* Execução em **containers Docker**
* Orquestração em **Kubernetes**
* Coleta de logs via **Filebeat**
* Indexação no **Elasticsearch**
* Visualização e análise no

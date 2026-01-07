# Overwatch Intelligence System

A distributed system based on **microservices architecture**, designed to manage agents, enhanced individuals (supers), and strategic operational reports.

The project simulates a **military/government intelligence environment**, where agents are operationally linked to supers, events are propagated asynchronously, and official reports are generated and stored securely.

This system was built with **scalability, decoupling, and real-world architectural practices** in mind.

---

## Project Purpose

- Manage intelligence agents and their operational status
- Register and control enhanced individuals (supers)
- Publish consolidated intelligence events using **Apache Kafka**
- Generate professional operational reports asynchronously
- Store classified reports using object storage (**MinIO**)

---

## Architecture Overview

- **Architecture:** Microservices
- **Synchronous Communication:** REST (HTTP)
- **Asynchronous Communication:** Apache Kafka
- **Persistence:** MySQL
- **File Storage:** MinIO (S3-compatible)
- **Infrastructure:** Docker & Docker Compose

### High-Level Flow

Agent Service ---> Supers Service (REST / OpenFeign)
|
| publish (Kafka Event)
v
Kafka Topic
|
v
Logistics / Report Service
|
v
MinIO (PDF Operational Reports)


---

## 🧩 Microservices Breakdown

### Agent Service — `:8081`

**Responsibilities**
- Agent lifecycle management
- Association between agents and supers
- Publishing intelligence events to Kafka

#### Endpoints

**Create Agent**


```json
{
  "name": "Nick Fury",
  "agentRole": "DIRECTOR",
  "dateOfBirth": "1950-07-04",
  "agentCode": "DIR-001",
  "superId": 2
}
```
 - #### superId represents the associated enhanced individual.

### **List Agents**

### GET /agent

---
### **Update Agent**

### PUT /agent/{agentCode}

---
### **Deactivate Agent (Soft Delete)**

### DELETE /agent/{id}

---

### **Reactivate Agent**

### POST /agent/{id}/enable

---

### **Publish Intelligence Event (Kafka)**

### POST /agent/{id}/supers/{superId}/publish

---

## **This operation**:

 - Loads the agent from the database

 - Retrieves super data via OpenFeign

 - Consolidates agent + super data

 - Publishes a structured event to Kafka

---

# Supers Service — `:8082`

# Responsibilities

* Registration of enhanced individuals
* Threat level classification
* Ability management
* Soft delete with reactivation

---
#### Endpoints

**Create Super**

### POST /supers


```json
{
  "name": "Gambit",
  "dateOfBirth": "1968-01-01",
  "superCode": "GMBT01",
  "abilities": [
    "Energy Charge",
    "Agility",
    "Card Throwing"
  ],
  "threatLevel": "HIGH"
}
```
**List Supers**

### GET /supers

---

**Get Super by ID**

### GET /supers/{id}

---

**Update Super**

### PUT /supers/{superCode}

---

**Deactivate Super (Soft Delete)**

### DELETE /supers/{superCode}

---

**Reactivate Super**

### POST /supers/{superCode}/enable

---

# Bucket Service — `:8083`(Internal) 

## Internal service used exclusively by other microservices.

### Responsibilities

* File upload
* Secure URL generation

---

#  Event-Driven Communication (Kafka)

* **Publisher**: Agent Service
* **Subscriber**: Logistics / Supers Service

## Published Events Include

* Agent data
* Super data
* Abilities
* Threat level classification

## Subscriber Responsibilities

* Consume the event
* Generate a professional operational report (PDF)
* Store the document in MinIO
* Optionally publish a follow-up event

---

#  Infrastructure (Docker)
## Apache Kafka + Zookeeper + Kafka UI

```yaml
version: '3.9'

services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.9.4
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.9.4
    environment:
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
    ports:
      - "29092:29092"

  kafka-ui:
    image: provectuslabs/kafka-ui
    ports:
      - "8080:8080"

```

# MinIO (Object Storage)

```yaml
services:
  minio:
    image: minio/minio
    ports:
      - "9000:9000"
      - "9001:9001"
    environment:
      MINIO_ROOT_USER: admin
      MINIO_ROOT_PASSWORD: admin123
```

# MySQL
```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: db_overwatch
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_USER: nickFury123
      MYSQL_PASSWORD: nickFuryShield123
    ports:
      - "3306:3306"
```

---

#  Database Initialization (Mandatory)

##  Must be executed before starting the application

## Supers Tables

```sql
create table tb_supers (
  id bigint primary key auto_increment,
  name varchar(100) not null,
  date_of_birth date,
  super_code varchar(50) unique not null,
  url_report text,
  threat_level enum('LOW','MODERATE','HIGH','SEVERE','CRITICAL'),
  active boolean
);

create table tb_super_abilities (
  super_id bigint not null,
  ability varchar(255) not null,
  foreign key (super_id) references tb_supers(id)
);
```

## Agents Table

```sql
create table tb_agent (
id bigint auto_increment primary key,
name varchar(100),
date_of_birth date,
agent_code varchar(50),
super_id bigint,
agent_role enum('AGENT','DIRECTOR','FIELD_AGENT'),
active boolean
);
```

---

# Architectural Decisions & Technical Challenges

## 1. Decoupling Domain and Messaging Models

Kafka events use dedicated representation models, avoiding entity leakage and preventing tight coupling between services.

## 2. Event-Driven Report Generation

Report creation is asynchronous, ensuring:

* Non-blocking API operations
* Scalability under load
* Clear separation of responsibilities

## 3. Soft Delete Strategy

Agents and supers are never physically removed, preserving:

* Historical integrity
* Audit capability
* Government-grade traceability

## 4. OpenFeign for Controlled Synchronous Calls
Feign is used only where strong consistency is required, avoiding unnecessary service-to-service chatter

## 5. Object Storage over File System

MinIO was chosen to simulate enterprise-grade object storage, aligning with real-world cloud architectures.

---

## Next Steps

* API documentation using Swagger / OpenAPI
* Authentication and authorization (JWT / Keycloak)
* API Gateway for centralized access control
* Advanced validation and contract testing

---

# Author

## Lucas Bandeira

Backend Developer — Java & Distributed Systems
Focused on clean architecture, scalability, and real-world solutions

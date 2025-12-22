# StreamPulse

🚀 **StreamPulse** is a **modular real-time stream analytics library for Java** designed to process continuous data streams with pluggable analytics and flexible output adapters.

Unlike typical real-time dashboards or hardcoded applications, StreamPulse is built as a **reusable library**, not just an app.

---

## ❓ Why StreamPulse Exists (Problem Statement)

Most real-time analytics projects suffer from these issues:

❌ Tightly coupled to a specific data source (crypto, sensors, logs)  
❌ Analytics logic mixed with transport logic (REST/WebSocket)  
❌ Difficult to extend or reuse  
❌ Built as demos, not libraries  

StreamPulse was created to **decouple real-time data ingestion, analytics computation, and data publishing**, enabling developers to build scalable, reusable real-time systems.

---

## 🌟 What Makes StreamPulse Unique

### 1️⃣ Library-First Architecture (Not App-First)

StreamPulse is designed as a **core Java library** that can run:
- Inside Spring Boot
- Inside microservices
- Inside batch jobs
- Without any web framework

> Spring Boot is used only as an adapter — never as a dependency of core analytics.

---

### 2️⃣ Analytics as Plug-ins (Key Differentiator)

Analytics are **independent, reusable components**.

```java
engine.register(new MovingAverage(30));
engine.register(new SpikeDetector(0.05));
engine.register(new AnomalyDetector(3.0));
```

Each analytics module:
- Works independently
- Can be added/removed without affecting others
- Receives data in real time
- Emits structured results

This design mirrors **professional stream processing engines**.

---

### 3️⃣ Data Source Agnostic

StreamPulse does **not** assume any data type.

Supported examples:
- IoT sensors
- Financial ticks
- Application logs
- Game events
- System metrics

Anything that can be converted into a `DataPoint` can be analyzed.

---

### 4️⃣ Output Agnostic (Multiple Consumption Models)

Analytics results can be consumed through:

• Java API  
• REST endpoints  
• WebSocket streams  
• Message brokers (Kafka – planned)

This makes StreamPulse usable in:
- Dashboards
- Microservices
- Alerting systems
- Data pipelines

---

### 5️⃣ Event-Driven & Real-Time by Design

StreamPulse operates on **continuous streams**, not static datasets.

Features:
- Sliding window calculations
- Stateful analytics
- Event-driven processing
- Low-latency propagation

---

## 🧠 Core Concepts

### 📦 DataPoint
A universal representation of a streaming event.

```java
DataPoint(symbol, value, timestamp)
```

---

### ⚙️ Stream Engine
Central coordinator for data flow.

Responsibilities:
- Accept incoming data
- Forward data to analytics
- Collect analytics results
- Forward results to outputs

---

### 📊 Analytics Module
Encapsulates a single analytics logic.

Examples:
- Moving Average
- Spike Detection
- Anomaly Detection
- Trend Detection (future)

---

### 📡 Output Adapter
Publishes analytics results.

Examples:
- WebSocket publisher
- REST API provider
- Java callback
- Kafka publisher (future)

---

## 🏗️ Architecture Overview

```
[ Data Source ]
       ↓
[ Stream Engine ]
       ↓
[ Analytics Pipeline ]
       ↓
[ Output Adapters ]
```

This separation allows independent scaling and evolution.

---

## 📁 Project Structure

```
streampulse
│
├── pom.xml
├── README.md
│
├── streampulse-core
│   └── analytics engine & models (Pure Java)
│
├── streampulse-spring
│   └── REST & WebSocket adapters
│
└── examples
    └── Real-time dashboards & demos
```

---

## 🛠️ Technology Stack

- Java 21
- Maven
- Spring Boot (Adapters only)
- WebSockets (STOMP)
- JSON serialization

---

## 🎯 Use Cases

✔ Real-time monitoring systems  
✔ IoT analytics platforms  
✔ Financial tick analysis  
✔ Live dashboards  
✔ Alerting & anomaly detection  
✔ Data engineering pipelines  

---

## 📈 Scalability & Extensibility

StreamPulse is designed to:
- Add new analytics without code changes
- Add new output channels easily
- Scale horizontally via event streams
- Support future persistence layers

---

## 🧭 Roadmap

- Kafka & RabbitMQ adapters
- Persistence support
- Custom analytics DSL
- Performance benchmarking
- Maven Central publishing

---

## 👨‍💻 Project Vision

StreamPulse aims to demonstrate **production-grade system design**, not just feature implementation.

This project focuses on:
- Clean architecture
- Extensibility
- Real-time systems
- Library-quality design

---

## 🧑‍💻 Author

Built as a learning-driven yet professional-grade real-time analytics library.

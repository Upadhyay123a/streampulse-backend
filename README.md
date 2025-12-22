# StreamPulse

🚀 **StreamPulse** is a **library-first, modular real-time stream analytics framework for Java**. It is designed to process continuous data streams with pluggable analytics, flexible output adapters, and zero coupling between core logic and frameworks.

Unlike typical "real-time apps" or dashboards, StreamPulse is built as a **reusable analytics engine** that can power many kinds of systems.

---

## 🧠 Why StreamPulse Exists (Problem Statement)

Most real-time analytics projects suffer from the same architectural problems:

❌ Analytics logic tightly coupled to REST/WebSockets
❌ Hardcoded data sources (crypto, sensors, logs)
❌ Difficult to extend or reuse analytics
❌ Built as demos, not as reusable libraries

**StreamPulse solves this by cleanly separating:**

* Data ingestion
* Stream processing
* Analytics computation
* Result publishing

This enables **clean reuse, testing, and extension**.

---

## 🌟 What Makes StreamPulse Unique

### 1️⃣ Library-First Architecture (Core Before Frameworks)

StreamPulse is designed as a **pure Java analytics library**.

* Core logic has **no dependency on Spring or Web**
* Frameworks are added only via adapters
* Can run inside:

  * Spring Boot
  * Microservices
  * Batch jobs
  * Standalone Java apps

> Frameworks are optional — analytics are not.

---

### 2️⃣ Pluggable Analytics (Plugin Model)

Analytics are independent, reusable modules:

```java
engine.register(new MovingAverage(30));
engine.register(new SpikeDetector(0.05));
engine.register(new AnomalyDetector(3.0));
```

Each analytics module:

* Is stateful
* Operates on live streams
* Can be added/removed dynamically
* Emits structured results

This mirrors professional stream engines (Flink, Spark Streaming — simplified).

---

### 3️⃣ Data Source Agnostic

StreamPulse does **not assume any domain**.

Supported examples:

* IoT sensor readings
* Financial tick data
* Application logs
* Game events
* System metrics

Anything convertible into a `DataPoint` can be analyzed.

---

### 4️⃣ Output Agnostic (Multiple Consumption Models)

Analytics results can be consumed through:

• Java callbacks
• REST APIs
• WebSocket streams
• Message brokers (Kafka — planned)

This makes StreamPulse usable for:

* Dashboards
* Alerting systems
* Data pipelines
* Monitoring tools

---

### 5️⃣ Event-Driven & Real-Time by Design

StreamPulse operates on **continuous event streams**, not static datasets.

Features:

* Sliding window analytics
* Stateful processing
* Low-latency propagation
* Event-driven design

---

## 🧠 Core Concepts

### 📦 DataPoint

A universal representation of a streaming event:

```java
DataPoint(key, value, timestamp)
```

---

### ⚙️ Stream Engine

Central coordinator for data flow.

Responsibilities:

* Accept incoming data
* Forward data to analytics
* Maintain state
* Collect analytics results
* Dispatch results to outputs

---

### 📊 Analytics Module

Encapsulates one analytics algorithm.

Examples:

* Moving Average
* Spike Detection
* Anomaly Detection
* Trend Detection (future)

---

### 📡 Output Adapter

Publishes analytics results.

Examples:

* WebSocket publisher
* REST endpoints
* Java listeners
* Kafka producer (future)

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

Each layer evolves independently.

---

## 📁 Final Folder Structure

```
streampulse-backend
│
├── pom.xml
├── README.md
│
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── streampulse
│       │           │
│       │           ├── StreamPulseApplication.java   # TEMP (demo runner)
│       │           │
│       │           ├── api                          # Public contracts
│       │           │     ├── StreamEngine.java
│       │           │     ├── Analytics.java
│       │           │     └── ResultListener.java
│       │           │
│       │           ├── engine                       # Core engine implementation
│       │           │     └── DefaultStreamEngine.java
│       │           │
│       │           ├── model                        # Core data models
│       │           │     ├── DataPoint.java
│       │           │     └── AnalyticsResult.java
│       │           │
│       │           ├── analytics                    # Built-in analytics
│       │           │     ├── MovingAverage.java
│       │           │     ├── SpikeDetector.java
│       │           │     └── AnomalyDetector.java
│       │           │
│       │           ├── output                       # Result dispatching
│       │           │     └── ResultPublisher.java
│       │           │
│       │           ├── adapter                      # Framework-specific code
│       │           │     ├── rest
│       │           │     │     └── PriceRestController.java
│       │           │     └── websocket
│       │           │           └── WebSocketPublisher.java
│       │           │
│       │           └── util                         # Utilities
│       │                 └── StatsUtils.java
│       │
│       └── resources
│           └── application.properties
│
└── future-modules (planned)
    ├── streampulse-core
    ├── streampulse-spring
    └── examples
```

---

## 🛠️ Technology Stack

* Java 21
* Maven
* Spring Boot (Adapters only)
* WebSockets (STOMP)
* JSON serialization

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

* Add analytics without core changes
* Add output channels easily
* Scale via event streams
* Support persistence in future

---

## 🧭 Roadmap

* Kafka & RabbitMQ adapters
* Persistence layer
* Analytics DSL
* Performance benchmarks
* Maven Central publishing

---

## 👨‍💻 Project Vision

StreamPulse is built to demonstrate **production-grade system design**, not just feature implementation.

Focus areas:

* Clean architecture
* Extensibility
* Real-time systems
* Library-quality code

---

## 🧑‍💻 Author

Built as a learning-driven yet professional-grade real-time analytics library.

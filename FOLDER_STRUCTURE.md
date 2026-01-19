# StreamPulse Backend - Project Folder Structure

## Complete Directory Layout

```
streampulse-backend/
│
├── pom.xml                                    # Maven project configuration file
├── README.md                                  # Project readme documentation
├── FOLDER_STRUCTURE.md                        # This file - Complete folder structure documentation
│
├── src/                                       # Source code directory
│   │
│   ├── main/                                  # Main application code
│   │   │
│   │   ├── java/                              # Java source files
│   │   │   └── com/
│   │   │       └── streampulse/
│   │   │           │
│   │   │           ├── StreamPulseApplication.java          # Main Spring Boot application class
│   │   │           │
│   │   │           ├── adapter/                             # Adapter layer for external integrations
│   │   │           │   ├── rest/
│   │   │           │   │   ├── IngestRequest.java          # REST request model for data ingestion
│   │   │           │   │   └── PriceRestController.java    # REST controller for price endpoints
│   │   │           │   │
│   │   │           │   └── websocket/
│   │   │           │       ├── WebSocketConfig.java        # WebSocket configuration
│   │   │           │       └── WebSocketPublisher.java     # WebSocket message publisher
│   │   │           │
│   │   │           ├── analytics/                           # Analytics algorithms and processing
│   │   │           │   ├── AnomalyDetector.java            # Detects anomalies in data streams
│   │   │           │   ├── MovingAverage.java              # Moving average calculation
│   │   │           │   └── SpikeDetector.java              # Detects price spikes
│   │   │           │
│   │   │           ├── api/                                 # Core API interfaces
│   │   │           │   ├── Analytics.java                  # Analytics interface
│   │   │           │   ├── ResultListener.java             # Listener for analysis results
│   │   │           │   └── StreamEngine.java               # Stream processing engine interface
│   │   │           │
│   │   │           ├── config/                              # Configuration classes
│   │   │           │   └── StreamPulseConfig.java          # Application configuration
│   │   │           │
│   │   │           ├── core/                                # Core business logic
│   │   │           │   └── StreamPulse.java                # Core StreamPulse class
│   │   │           │
│   │   │           ├── engine/                              # Stream processing engine implementation
│   │   │           │   └── DefaultStreamEngine.java        # Default implementation of StreamEngine
│   │   │           │
│   │   │           ├── model/                               # Data models
│   │   │           │   ├── AnalyticsResult.java            # Model for analytics results
│   │   │           │   └── DataPoint.java                  # Model for individual data points
│   │   │           │
│   │   │           ├── output/                              # Output/Publishing layer
│   │   │           │   └── ResultPublisher.java            # Publishes results to various channels
│   │   │           │
│   │   │           ├── store/                               # Data storage layer
│   │   │           │   ├── InMemoryResultStore.java        # In-memory storage implementation
│   │   │           │   └── ResultStore.java                # Storage interface
│   │   │           │
│   │   │           └── util/                                # Utility functions
│   │   │               └── StatsUtils.java                 # Statistical calculation utilities
│   │   │
│   │   └── resources/                         # Application resources
│   │       ├── application.properties         # Spring Boot configuration properties
│   │       │
│   │       └── static/                        # Static web assets
│   │           └── ws-test.html               # WebSocket testing HTML page
│   │
│   └── test/                                  # Test code (if present)
│
└── target/                                    # Build output directory (Maven compiled files)
    │
    ├── classes/                               # Compiled Java classes
    │   ├── application.properties             # Compiled resources
    │   ├── com/                               # Compiled Java packages
    │   │   └── streampulse/
    │   │       ├── adapter/
    │   │       │   ├── rest/                  # Compiled REST adapter classes
    │   │       │   └── websocket/             # Compiled WebSocket adapter classes
    │   │       ├── analytics/                 # Compiled analytics classes
    │   │       ├── api/                       # Compiled API classes
    │   │       ├── config/                    # Compiled configuration classes
    │   │       ├── core/                      # Compiled core classes
    │   │       ├── engine/                    # Compiled engine classes
    │   │       ├── model/                     # Compiled model classes
    │   │       ├── output/                    # Compiled output classes
    │   │       ├── store/                     # Compiled store classes
    │   │       └── util/                      # Compiled utility classes
    │   │
    │   └── static/                            # Compiled static resources
    │       └── ws-test.html
    │
    ├── maven-archiver/                        # Maven build metadata
    │   └── pom.properties
    │
    ├── maven-status/                          # Maven compiler status
    │   └── maven-compiler-plugin/
    │       └── compile/
    │           └── default-compile/
    │               ├── createdFiles.lst       # List of created files
    │               └── inputFiles.lst         # List of input files
    │
    └── test-classes/                          # Compiled test classes (if tests present)

```

## File Categories and Purposes

### Configuration Files
- **pom.xml** - Maven dependency management and build configuration
- **application.properties** - Spring Boot application settings
- **FOLDER_STRUCTURE.md** - This documentation

### Core Application Files

#### Entry Point
- **StreamPulseApplication.java** - Main Spring Boot application entry point

#### Adapter Layer (External Integration)
- **PriceRestController.java** - REST API endpoints for price data
- **IngestRequest.java** - Request model for data ingestion
- **WebSocketConfig.java** - WebSocket server configuration
- **WebSocketPublisher.java** - WebSocket message publishing

#### Analytics Layer
- **AnomalyDetector.java** - Anomaly detection algorithm
- **SpikeDetector.java** - Price spike detection
- **MovingAverage.java** - Moving average calculation

#### API Layer (Interfaces)
- **Analytics.java** - Analytics interface definition
- **StreamEngine.java** - Stream processing engine interface
- **ResultListener.java** - Result listener callback interface

#### Business Logic
- **StreamPulse.java** - Core business logic
- **DefaultStreamEngine.java** - Default stream processing implementation

#### Data Models
- **DataPoint.java** - Individual data point model
- **AnalyticsResult.java** - Analytics result model

#### Data Storage
- **ResultStore.java** - Storage interface
- **InMemoryResultStore.java** - In-memory storage implementation

#### Output & Publishing
- **ResultPublisher.java** - Result publishing mechanism

#### Utilities
- **StatsUtils.java** - Statistical utility functions

#### Configuration
- **StreamPulseConfig.java** - Application configuration class

### Static Resources
- **ws-test.html** - WebSocket testing client (HTML page)

### Build Output
- **target/** - Compiled classes and build artifacts
  - Compiled Java class files (.class)
  - Packaged resources
  - Maven metadata

---

## Quick Navigation

| Layer | Location | Purpose |
|-------|----------|---------|
| **REST API** | `adapter/rest/` | HTTP endpoints for price data |
| **WebSocket** | `adapter/websocket/` | Real-time data streaming |
| **Analytics** | `analytics/` | Data analysis algorithms |
| **Core Business** | `core/` | Main StreamPulse logic |
| **Stream Engine** | `engine/` | Data processing pipeline |
| **Storage** | `store/` | Result persistence |
| **Configuration** | `config/` | Application settings |
| **Data Models** | `model/` | Entity definitions |
| **Utilities** | `util/` | Helper functions |

---

## Technology Stack

- **Language**: Java 21 (LTS)
- **Framework**: Spring Boot 3.3.4
- **Build Tool**: Apache Maven
- **Key Features**:
  - REST API with Spring Web
  - WebSocket support
  - Real-time data streaming
  - Analytics and anomaly detection


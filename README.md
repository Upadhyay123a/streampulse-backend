# streampulse-backend
Real-Time Data Analyzer Using WebSockets
streampulse-backend
│
├── pom.xml
├── README.md
│
└── src
    └── main
        ├── java
        │   └── com
        │       └── streampulse
        │           │
        │           ├── StreamPulseApplication.java
        │           │
        │           ├── config
        │           │     └── WebSocketConfig.java
        │           │
        │           ├── controller
        │           │     └── PublishController.java
        │           │
        │           ├── model
        │           │     └── PriceUpdate.java
        │           │
        │           ├── analytics
        │           │     ├── AnalyticsService.java
        │           │     └── AnalyticsUpdate.java
        │           │
        │           └── service
        │                 └── PriceGeneratorService.java
        │
        └── resources
            └── application.properties

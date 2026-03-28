You need to design and implement a logger library that applications can use to log messages to a sink.

1. Message:
- A message has the following attributes:
- Content: A string representing the actual log message.
- Level: The severity level of the message.
- Sink: The destination (like file, database, console, etc.) where the message is logged.
- Namespace: Identifies the part of the application that sent the message.
2. Sink:
- A sink is the destination for logging messages.
- A sink is:
  - Tied to one or more message levels (e.g., ERROR, DEBUG).
  - Shared: Multiple message levels can use the same sink.
3. Logger Library Requirements:
- Setup:
  - Requires configuration during sink setup.
  - Accepts messages from clients (applications).
  - Routes messages to the appropriate sink based on message levels.
  - Supports the following message levels in order of priority: FATAL, ERROR, WARN, INFO, DEBUG.
- Allows configuration to log messages above a certain level:
  - Example: If the logging level is configured as INFO, only WARN, INFO, and DEBUG messages will be logged.
- Enriches messages with additional information (like timestamps) before sending to the sink.
4. Sending Messages:
- Message level determines the sink:
  - You don't need to specify the sink when sending a message; it's tied to the level.
- Input required:
  - Message content.
  - Level.
  - Namespace.
5. Logger Configuration:
- Purpose:
  - Defines all the details required to use the logger library.
  - Allows configuration of one or more sinks for an application.
  - Associates each message level with a specific sink.
- Implementation:
  - Can be considered as key-value pairs for configuration.
- Example Configuration Details:
  - Time format (e.g., ISO 8601).
  - Logging level (e.g., INFO, WARN).
  - Sink type (e.g., file, console, database).
  - Details required for the sink (e.g., file location).


Example:
```
Configuration Example:
- Time Format: YYYY-MM-DD HH:mm:ss
- Logging Level: INFO
- Sink Type: File
- File Location: /var/logs/application.log
```

  Key Functionalities:

1. Sink Setup:
- Associate sinks with message levels during configuration.
2. Message Routing:
- Route messages based on their level to the appropriate sink.
3. Log Enrichment:
- Add metadata (e.g., timestamps, namespace) to messages automatically.
4. Priority-Based Filtering:
- Log only messages above the configured logging level.

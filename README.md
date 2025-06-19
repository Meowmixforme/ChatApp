# ChatApp

A simple Java-based client-server chat application with a graphical user interface (GUI). This project demonstrates the use of sockets for network communication and Java Swing for building desktop applications.

---

## Features

- **Multi-user chat:** Multiple clients can connect to the server and chat in real-time.
- **Unique usernames:** Each client must choose a unique username to join the chat.
- **Broadcast messaging:** Messages sent by one client are broadcast to all connected clients.
- **Simple GUI:** User-friendly interface built with Java Swing.
- **Basic protocol:** Server enforces username uniqueness and manages client connections.

---

## Project Structure

```
src/
├── Client/
│   └── Client.java
└── Server/
    └── Server.java
```

- `Client/Client.java` – The client application with a Swing interface for chatting.
- `Server/Server.java` – The server application handling multiple clients via threads.

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Running the Server

1. Open a terminal and navigate to the `src/Server` directory.
2. Compile the server code:
   ```sh
   javac Server.java
   ```
3. Run the server:
   ```sh
   java Server
   ```
   The server will listen for client connections on port `9800`.

### Running a Client

1. Open another terminal and navigate to the `src/Client` directory.
2. Compile the client code:
   ```sh
   javac Client.java
   ```
3. Run the client:
   ```sh
   java Client
   ```
   - When prompted, enter the IP address of the server (use `localhost` if running locally).
   - Enter a unique username when prompted.

4. You can launch multiple clients to simulate a multi-user chat.

---

## How It Works

- **Server:** Listens for incoming connections, manages usernames, and broadcasts messages to all clients.
- **Client:** Connects to the server, negotiates a unique username, and allows the user to send/receive messages through the chat window.

---

## Educational Value

This project demonstrates:
- Java socket programming (TCP client-server model)
- Multi-threading for handling concurrent clients
- Swing-based GUI development
- Simple communication protocol design

---

## License

MIT License  
Copyright (c) 2025 Meowmixforme


# httpecho

**httpecho** is a simple HTTP server and client application written in Java. This project demonstrates basic HTTP communication by providing a basic HTTP server capable of handling GET requests, along with a client application to simulate these requests.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)
- [License](#license)

## Features

- **HTTP Server**: 
  - Listens on port `8080`.
  - Handles GET requests.
  - Supports custom error handling (404 Not Found, 405 Method Not Allowed).
  
- **HTTP Client**:
  - Simulates client requests using direct socket programming.
  - Sends GET requests to the HTTP server.
  - Easily configurable with custom headers and data.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- A Java IDE like IntelliJ IDEA, Eclipse, or any text editor of your choice.

### Installation

1. **Clone the repository**:

   ```sh
   git clone https://github.com/om1cael/httpecho.git
   cd httpecho
   ```

2. **Compile the server and client**:
     ```sh
     javac *.java
     ```

## Usage

### Starting the HTTP Server

1. Run the HTTP server:

   ```sh
   java HTTPServer
   ```

2. The server will be accessible at `http://localhost:8080`. You can test its functionality by accessing resources like `http://localhost:8080/index.html` in your web browser, or by using the HTTP client.

### Using the HTTP Client

1. To simulate a `GET` request:
   ```sh
   java HTTPClient
   ```

## Configuration

- The HTTP server and client come with default settings:
  - The server listens on port `8080` but can be configured in the `HTTPServer` class.
  - The client sends requests to `http://localhost:8080` but can be adjusted accordingly.

## Contributing

Contributions to `httpecho` are welcome! Whether it’s a bug fix, new feature, or documentation improvement, feel free to submit a pull request. Please follow the coding style and contribute responsibly.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

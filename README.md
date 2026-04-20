# Food Delivery Microservices Project

## Overview
This project is a microservices-based architecture for a food delivery platform. The goal is to provide a scalable, flexible, and organized way to manage the various components of a food delivery service.

## Services
1. **User Service**  
   Manages user accounts, authentication, and profiles.

2. **Restaurant Service**  
   Handles restaurant information, menus, and restaurant-specific functionalities.

3. **Order Service**  
   Manages the order lifecycle including placing orders, order tracking, and order history.

4. **Payment Service**  
   Processes payments and manages payment history.

5. **Notification Service**  
   Sends notifications to users regarding order status, promotions, etc.

## Tech Stack
- **Programming Language**: Node.js
- **Framework**: Express.js
- **Database**: MongoDB
- **Containerization**: Docker
- **Orchestration**: Kubernetes
- **Message Broker**: RabbitMQ

## Microservices Communication
- Inter-service communication through REST API or gRPC.
- Utilize message queues for asynchronous processing.

## Project Structure

├── user-service
├── restaurant-service
├── order-service
├── payment-service
├── notification-service
└── docker-compose.yml



## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/hanaharraghi/foodD_Micro.git
   cd foodD_Micro
Build the Docker images:
bash
docker-compose build
Run the services:
bash
docker-compose up
APIs Documentation
Refer to each service's folder for the specific API documentation. Swagger can be utilized for auto-generating API docs.

Testing
Write unit tests for individual services.
Use Postman for integration testing.
Contribution
Fork the repository, create a new branch, and submit a pull request to contribute.
License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
For any inquiries, please reach out to hanaharraghi@example.com.

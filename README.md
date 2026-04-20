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

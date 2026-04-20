FEEDBACK-SERVICE

Ordre de lancement :
1. Eureka Server
2. Config Server
3. API Gateway
4. user-service
5. menu-service
6. feedback-service

Endpoints :
- GET    http://localhost:8084/feedbacks
- GET    http://localhost:8084/feedbacks/1
- GET    http://localhost:8084/feedbacks/user/1
- GET    http://localhost:8084/feedbacks/menu/1
- POST   http://localhost:8084/feedbacks
- DELETE http://localhost:8084/feedbacks/1

Via Gateway :
- GET    http://localhost:8082/feedbacks
- GET    http://localhost:8082/feedbacks/1

Exemple JSON POST :
{
  "userId": 1,
  "menuId": 2,
  "comment": "Très satisfait",
  "rating": 5
}

Remarque :
- Le service charge 2 feedbacks de test au démarrage.
- Cette version stocke userId et menuId sans appel REST aux autres services.
- Le port du gateway dans ce README est 8082.

REACT FRONT - VITE + REACT

Ce frontend React communique avec ton API Gateway.

DEMARRAGE
1. Dézipper le projet
2. Ouvrir un terminal dans le dossier
3. Installer les dépendances :
   npm install
4. Lancer :
   npm run dev

URL
- Front : http://localhost:5173
- Gateway par défaut dans l'app : http://localhost:8082

SERVICES A LANCER AVANT
1. eureka-server
2. config-server
3. api-gateway
4. user-service
5. menu-service
6. feedback-service

IMPORTANT - CORS
Si les appels sont bloqués depuis le navigateur, il faut activer CORS dans l'API Gateway.

FONCTIONS
- Charger users
- Ajouter user
- Charger menus
- Charger menus disponibles
- Ajouter menu
- Charger feedbacks
- Ajouter feedback

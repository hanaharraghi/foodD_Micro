BLOG-SERVICE - NestJS + MongoDB

Demarrage:
1. Avoir MongoDB lance localement
2. Dezipper le projet
3. Ouvrir le dossier
4. Installer:
   npm install
5. Lancer:
   npm run start:dev

URL:
- Service: http://localhost:3001
- MongoDB: mongodb://127.0.0.1:27017/blog_db

Endpoints:
- GET    http://localhost:3001/blogs
- GET    http://localhost:3001/blogs/:id
- POST   http://localhost:3001/blogs
- PATCH  http://localhost:3001/blogs/:id
- DELETE http://localhost:3001/blogs/:id

Exemple JSON POST:
{
  "title": "Premier article",
  "content": "Contenu du blog",
  "author": "Haneene",
  "status": "PUBLISHED"
}

import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
  url: 'http://localhost:8089',
  realm: 'microservices',
  clientId: 'frontend-client'
})

export default keycloak
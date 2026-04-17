import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
  url: 'http://localhost:8180',
  realm: 'Job_Board_Realm',
  clientId: 'Gateway',
})

export async function initKeycloak() {
  try {
    const authenticated = await keycloak.init({
      onLoad: 'login-required',
      pkceMethod: 'S256',
      checkLoginIframe: false,
    })

    if (!authenticated) {
      console.log('Keycloak user is not authenticated.')
    }

    return authenticated
  } catch (error) {
    console.log('Keycloak init failed:', error)
    return false
  }
}

export function getAccessToken() {
  return keycloak.token ?? null
}

export default keycloak

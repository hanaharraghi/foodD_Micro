import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './styles.css'
import keycloak from './keycloak'

keycloak
  .init({
    onLoad: 'login-required',
    checkLoginIframe: false
  })
  .then((authenticated) => {
    if (!authenticated) {
      window.location.reload()
      return
    }

    ReactDOM.createRoot(document.getElementById('root')).render(
      <React.StrictMode>
        <App keycloak={keycloak} />
      </React.StrictMode>
    )
  })
  .catch((error) => {
    console.error('Keycloak init error', error)
  })
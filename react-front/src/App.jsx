import React, { useEffect, useState } from 'react'

const pretty = (data) => JSON.stringify(data, null, 2)

export default function App({ keycloak }) {
 const [gatewayUrl, setGatewayUrl] = useState(
  localStorage.getItem('gatewayUrl') || 'http://localhost:8082'
)
  const [status, setStatus] = useState('Prêt')

  const [users, setUsers] = useState([])
  const [menus, setMenus] = useState([])
  const [feedbacks, setFeedbacks] = useState([])
  const [deliveries, setDeliveries] = useState([])
  const [orders, setOrders] = useState([])
  const [blogs, setBlogs] = useState([])

  const [userForm, setUserForm] = useState({ name: '', email: '' })

  const [menuForm, setMenuForm] = useState({
    name: '',
    description: '',
    price: '',
    category: '',
    available: true
  })

  const [feedbackForm, setFeedbackForm] = useState({
    userId: '',
    menuId: '',
    comment: '',
    rating: ''
  })

  const [deliveryForm, setDeliveryForm] = useState({
    orderId: '',
    customerName: '',
    address: '',
    status: '',
    transportType: ''
  })

  const [orderForm, setOrderForm] = useState({
    orderNumber: '',
    customerName: '',
    menuName: '',
    totalPrice: '',
    status: ''
  })

  const [blogForm, setBlogForm] = useState({
    title: '',
    content: '',
    author: '',
    status: ''
  })

  useEffect(() => {
    localStorage.setItem('gatewayUrl', gatewayUrl)
  }, [gatewayUrl])

  async function request(path, options = {}) {
    const url = gatewayUrl + path

    try {
      setStatus('Appel : ' + url)

      if (keycloak?.token && keycloak.isTokenExpired(30)) {
        await keycloak.updateToken(30)
      }

      const token = keycloak?.token
      const method = options.method || 'GET'

      const headers =
        method === 'GET' || method === 'HEAD'
          ? {
              ...(options.headers || {}),
              ...(token ? { Authorization: `Bearer ${token}` } : {})
            }
          : {
              'Content-Type': 'application/json',
              ...(options.headers || {}),
              ...(token ? { Authorization: `Bearer ${token}` } : {})
            }

      const response = await fetch(url, {
        ...options,
        method,
        headers
      })

      const text = await response.text()
      let data
      try {
        data = text ? JSON.parse(text) : {}
      } catch {
        data = text
      }

      if (!response.ok) {
        throw new Error(typeof data === 'string' ? data : JSON.stringify(data))
      }

      setStatus('Succès')
      return data
    } catch (error) {
      setStatus('Erreur : ' + error.message)
      throw error
    }
  }

  async function loadUsers() {
    const data = await request('/users')
    setUsers(data)
  }

  async function loadMenus() {
    const data = await request('/menus')
    setMenus(data)
  }

  async function loadFeedbacks() {
    const data = await request('/feedbacks')
    setFeedbacks(data)
  }

  async function loadAvailableMenus() {
    const data = await request('/menus/available')
    setMenus(data)
  }

  async function loadDeliveries() {
    const data = await request('/deliveries')
    setDeliveries(data)
  }

  async function loadOrders() {
    const data = await request('/orders')
    setOrders(data)
  }

  async function loadBlogs() {
    const data = await request('/blogs')
    setBlogs(data)
  }

  async function handleCreateUser(e) {
    e.preventDefault()
    const data = await request('/users', {
      method: 'POST',
      body: JSON.stringify(userForm)
    })
    setUsers((prev) => [...prev, data])
    setUserForm({ name: '', email: '' })
  }

  async function handleCreateMenu(e) {
    e.preventDefault()
    const payload = {
      ...menuForm,
      price: parseFloat(menuForm.price),
      available: menuForm.available === true || menuForm.available === 'true'
    }

    const data = await request('/menus', {
      method: 'POST',
      body: JSON.stringify(payload)
    })

    setMenus((prev) => [...prev, data])
    setMenuForm({
      name: '',
      description: '',
      price: '',
      category: '',
      available: true
    })
  }

  async function handleCreateFeedback(e) {
    e.preventDefault()
    const payload = {
      ...feedbackForm,
      userId: parseInt(feedbackForm.userId, 10),
      menuId: parseInt(feedbackForm.menuId, 10),
      rating: parseInt(feedbackForm.rating, 10)
    }

    const data = await request('/feedbacks', {
      method: 'POST',
      body: JSON.stringify(payload)
    })

    setFeedbacks((prev) => [...prev, data])
    setFeedbackForm({
      userId: '',
      menuId: '',
      comment: '',
      rating: ''
    })
  }

  async function handleCreateDelivery(e) {
    e.preventDefault()

    const payload = {
      ...deliveryForm,
      orderId: parseInt(deliveryForm.orderId, 10)
    }

    const data = await request('/deliveries', {
      method: 'POST',
      body: JSON.stringify(payload)
    })

    setDeliveries((prev) => [...prev, data])
    setDeliveryForm({
      orderId: '',
      customerName: '',
      address: '',
      status: '',
      transportType: ''
    })
  }

  async function handleCreateOrder(e) {
    e.preventDefault()

    const payload = {
      ...orderForm,
      totalPrice: parseFloat(orderForm.totalPrice)
    }

    const data = await request('/orders', {
      method: 'POST',
      body: JSON.stringify(payload)
    })

    setOrders((prev) => [...prev, data])
    setOrderForm({
      orderNumber: '',
      customerName: '',
      menuName: '',
      totalPrice: '',
      status: ''
    })
  }

  async function handleCreateBlog(e) {
    e.preventDefault()

    const payload = {
      ...blogForm,
      status: blogForm.status || 'PUBLISHED'
    }

    const data = await request('/blogs', {
      method: 'POST',
      body: JSON.stringify(payload)
    })

    setBlogs((prev) => [data, ...prev])
    setBlogForm({
      title: '',
      content: '',
      author: '',
      status: ''
    })
  }

  return (
    <div className="app">
      <header className="hero">
        <h1>React Front - Microservices</h1>
        <p>Frontend React connecté à l'API Gateway</p>
        <p>
          Connecté : <strong>{keycloak?.tokenParsed?.preferred_username || 'inconnu'}</strong>
        </p>
        <button onClick={() => keycloak.logout()}>Logout</button>
      </header>

      <main className="container">
        <section className="card config-card">
          <h2>Configuration Gateway</h2>
          <div className="row">
            <input
              value={gatewayUrl}
              onChange={(e) => setGatewayUrl(e.target.value)}
              placeholder="http://localhost:8082"
            />
          </div>
          <p className="status">{status}</p>
        </section>

        <section className="grid">
          <div className="card">
            <h2>Users</h2>
            <div className="actions">
              <button onClick={loadUsers}>Charger les users</button>
            </div>
            <form onSubmit={handleCreateUser} className="form">
              <input
                value={userForm.name}
                onChange={(e) => setUserForm({ ...userForm, name: e.target.value })}
                placeholder="Nom"
                required
              />
              <input
                type="email"
                value={userForm.email}
                onChange={(e) => setUserForm({ ...userForm, email: e.target.value })}
                placeholder="Email"
                required
              />
              <button type="submit">Ajouter user</button>
            </form>
            <pre className="result">{pretty(users)}</pre>
          </div>

          <div className="card">
            <h2>Menus</h2>
            <div className="actions two">
              <button onClick={loadMenus}>Charger les menus</button>
              <button onClick={loadAvailableMenus}>Menus disponibles</button>
            </div>
            <form onSubmit={handleCreateMenu} className="form">
              <input
                value={menuForm.name}
                onChange={(e) => setMenuForm({ ...menuForm, name: e.target.value })}
                placeholder="Nom du menu"
                required
              />
              <input
                value={menuForm.description}
                onChange={(e) => setMenuForm({ ...menuForm, description: e.target.value })}
                placeholder="Description"
                required
              />
              <input
                type="number"
                step="0.1"
                value={menuForm.price}
                onChange={(e) => setMenuForm({ ...menuForm, price: e.target.value })}
                placeholder="Prix"
                required
              />
              <input
                value={menuForm.category}
                onChange={(e) => setMenuForm({ ...menuForm, category: e.target.value })}
                placeholder="Catégorie"
                required
              />
              <select
                value={String(menuForm.available)}
                onChange={(e) => setMenuForm({ ...menuForm, available: e.target.value })}
              >
                <option value="true">Disponible</option>
                <option value="false">Indisponible</option>
              </select>
              <button type="submit">Ajouter menu</button>
            </form>
            <pre className="result">{pretty(menus)}</pre>
          </div>

          <div className="card">
            <h2>Feedbacks</h2>
            <div className="actions">
              <button onClick={loadFeedbacks}>Charger les feedbacks</button>
            </div>
            <form onSubmit={handleCreateFeedback} className="form">
              <input
                type="number"
                value={feedbackForm.userId}
                onChange={(e) => setFeedbackForm({ ...feedbackForm, userId: e.target.value })}
                placeholder="User ID"
                required
              />
              <input
                type="number"
                value={feedbackForm.menuId}
                onChange={(e) => setFeedbackForm({ ...feedbackForm, menuId: e.target.value })}
                placeholder="Menu ID"
                required
              />
              <input
                value={feedbackForm.comment}
                onChange={(e) => setFeedbackForm({ ...feedbackForm, comment: e.target.value })}
                placeholder="Commentaire"
                required
              />
              <input
                type="number"
                min="1"
                max="5"
                value={feedbackForm.rating}
                onChange={(e) => setFeedbackForm({ ...feedbackForm, rating: e.target.value })}
                placeholder="Note"
                required
              />
              <button type="submit">Ajouter feedback</button>
            </form>
            <pre className="result">{pretty(feedbacks)}</pre>
          </div>

          <div className="card">
            <h2>Deliveries</h2>
            <div className="actions">
              <button onClick={loadDeliveries}>Charger les deliveries</button>
            </div>
            <form onSubmit={handleCreateDelivery} className="form">
              <input
                type="number"
                value={deliveryForm.orderId}
                onChange={(e) =>
                  setDeliveryForm({ ...deliveryForm, orderId: e.target.value })
                }
                placeholder="Order ID"
                required
              />
              <input
                value={deliveryForm.customerName}
                onChange={(e) =>
                  setDeliveryForm({ ...deliveryForm, customerName: e.target.value })
                }
                placeholder="Nom client"
                required
              />
              <input
                value={deliveryForm.address}
                onChange={(e) =>
                  setDeliveryForm({ ...deliveryForm, address: e.target.value })
                }
                placeholder="Adresse"
                required
              />
              <input
                value={deliveryForm.status}
                onChange={(e) =>
                  setDeliveryForm({ ...deliveryForm, status: e.target.value })
                }
                placeholder="Status"
                required
              />
              <input
                value={deliveryForm.transportType}
                onChange={(e) =>
                  setDeliveryForm({ ...deliveryForm, transportType: e.target.value })
                }
                placeholder="Type transport"
                required
              />
              <button type="submit">Ajouter delivery</button>
            </form>
            <pre className="result">{pretty(deliveries)}</pre>
          </div>

          <div className="card">
            <h2>Orders</h2>
            <div className="actions">
              <button onClick={loadOrders}>Charger les orders</button>
            </div>
            <form onSubmit={handleCreateOrder} className="form">
              <input
                value={orderForm.orderNumber}
                onChange={(e) => setOrderForm({ ...orderForm, orderNumber: e.target.value })}
                placeholder="Numéro commande"
                required
              />
              <input
                value={orderForm.customerName}
                onChange={(e) => setOrderForm({ ...orderForm, customerName: e.target.value })}
                placeholder="Nom client"
                required
              />
              <input
                value={orderForm.menuName}
                onChange={(e) => setOrderForm({ ...orderForm, menuName: e.target.value })}
                placeholder="Nom menu"
                required
              />
              <input
                type="number"
                step="0.1"
                value={orderForm.totalPrice}
                onChange={(e) => setOrderForm({ ...orderForm, totalPrice: e.target.value })}
                placeholder="Prix total"
                required
              />
              <input
                value={orderForm.status}
                onChange={(e) => setOrderForm({ ...orderForm, status: e.target.value })}
                placeholder="Status"
                required
              />
              <button type="submit">Ajouter order</button>
            </form>
            <pre className="result">{pretty(orders)}</pre>
          </div>

          <div className="card">
            <h2>Blogs</h2>
            <div className="actions">
              <button onClick={loadBlogs}>Charger les blogs</button>
            </div>
            <form onSubmit={handleCreateBlog} className="form">
              <input
                value={blogForm.title}
                onChange={(e) => setBlogForm({ ...blogForm, title: e.target.value })}
                placeholder="Titre"
                required
              />
              <input
                value={blogForm.content}
                onChange={(e) => setBlogForm({ ...blogForm, content: e.target.value })}
                placeholder="Contenu"
                required
              />
              <input
                value={blogForm.author}
                onChange={(e) => setBlogForm({ ...blogForm, author: e.target.value })}
                placeholder="Auteur"
                required
              />
              <input
                value={blogForm.status}
                onChange={(e) => setBlogForm({ ...blogForm, status: e.target.value })}
                placeholder="Status"
              />
              <button type="submit">Ajouter blog</button>
            </form>
            <pre className="result">{pretty(blogs)}</pre>
          </div>
        </section>
      </main>
    </div>
  )
}
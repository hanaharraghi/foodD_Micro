import { useEffect, useMemo, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import { categories, foodItems, restaurants, timeline } from './data/mockData'
import { Button, Card, FoodItemCard, Input, Modal, RestaurantCard } from './components/ui'
import { Page } from './components/layout'
import keycloak from './lib/keycloak'
import hafoodImage from './assets/hafood.PNG'
import margheritaPizzaImage from './assets/Margherita Pizza.PNG'
import pepperoniPizzaImage from './assets/Pepperoni Pizza.PNG'

function resolveRestaurantImage(name) {
  const normalizedName = String(name ?? '').toLowerCase()
  if (normalizedName.includes('hafood')) return hafoodImage
  return null
}

function resolveFoodImage(name) {
  const normalizedName = String(name ?? '').toLowerCase()
  if (normalizedName.includes('margherita')) return margheritaPizzaImage
  if (normalizedName.includes('pepperoni')) return pepperoniPizzaImage
  return null
}

function useRestaurantsData() {
  const [restaurantsData, setRestaurantsData] = useState([])

  useEffect(() => {
    const fetchRestaurants = async () => {
      const token = keycloak.token

      if (!token) {
        console.log('Missing access token. Restaurants request skipped.')
        return
      }

      try {
        const response = await fetch('http://localhost:3000/menu', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        })

        const data = await response.json()
        console.log('API Data:', data)
        setRestaurantsData(Array.isArray(data) ? data : [])
      } catch (error) {
        console.log('Failed to fetch restaurants:', error)
      }
    }

    fetchRestaurants()
  }, [])

  return restaurantsData
}

export function LoginPage() {
  return (
    <Page title="Welcome Back" subtitle="Keycloak-ready authentication">
      <section className="auth-center">
        <Card title="Login" subtitle="OAuth-ready">
          <Input label="Email" placeholder="you@smartfood.com" />
          <Input label="Password" type="password" placeholder="••••••••" />
          <Button>Sign in</Button>
          <Button variant="secondary">Continue with Google</Button>
          <p className="muted">
            Need an account? <Link to="/auth/register">Register</Link>
          </p>
        </Card>
      </section>
    </Page>
  )
}

export function RegisterPage() {
  return (
    <Page title="Create Account" subtitle="Modular account setup">
      <section className="auth-center">
        <Card title="Register">
          <Input label="Full Name" placeholder="Your name" />
          <Input label="Email" placeholder="you@smartfood.com" />
          <Input label="Password" type="password" placeholder="Create password" />
          <Button>Create account</Button>
          <Button variant="secondary">Continue with Keycloak</Button>
        </Card>
      </section>
    </Page>
  )
}

export function HomePage() {
  const restaurantsApi = useRestaurantsData()
  const liveRestaurants = useMemo(
    () =>
      restaurantsApi.map((restaurant) => ({
        id: restaurant.id,
        name: restaurant.name ?? 'Unnamed restaurant',
        status: 'Open',
        category: restaurant.description ?? 'Fusion',
        time: '20-30 min',
        rating: '4.7',
        image: resolveRestaurantImage(restaurant.name),
      })),
    [restaurantsApi],
  )

  return (
    <Page title="Discovery" subtitle="Search restaurants and foods">
      <Input label="Search" placeholder="Search restaurants or food items..." />
      <div className="chip-row">{categories.map((c) => <span key={c} className="chip">{c}</span>)}</div>
      <div className="grid grid-2">
        {liveRestaurants.length > 0
          ? liveRestaurants.slice(0, 2).map((r) => (
              <Link key={r.id} to={`/restaurant/${r.id}`}>
                <RestaurantCard restaurant={r} />
              </Link>
            ))
          : restaurants.slice(0, 2).map((r) => <RestaurantCard key={r.id} restaurant={r} />)}
      </div>
      <h2>Recommended Food</h2>
      <div className="grid grid-2">{foodItems.slice(0, 2).map((i) => <FoodItemCard key={i.id} item={i} />)}</div>
    </Page>
  )
}

export function RestaurantsPage() {
  const restaurantsApi = useRestaurantsData()
  const liveRestaurants = useMemo(
    () =>
      restaurantsApi.map((restaurant) => ({
        id: restaurant.id,
        name: restaurant.name ?? 'Unnamed restaurant',
        status: 'Open',
        category: restaurant.description ?? 'Fusion',
        time: '20-30 min',
        rating: '4.7',
        image: resolveRestaurantImage(restaurant.name),
      })),
    [restaurantsApi],
  )

  return (
    <Page title="Restaurant List" subtitle="Marketplace catalog">
      <div className="grid grid-2">
        {liveRestaurants.length > 0
          ? liveRestaurants.map((r) => (
              <Link key={r.id} to={`/restaurant/${r.id}`}>
                <RestaurantCard restaurant={r} />
              </Link>
            ))
          : restaurants.map((r) => <RestaurantCard key={r.id} restaurant={r} />)}
      </div>
    </Page>
  )
}

export function RestaurantDetailsPage() {
  const { id } = useParams()
  const restaurantsApi = useRestaurantsData()
  const [menuItems, setMenuItems] = useState([])

  useEffect(() => {
    const selectedRestaurant = restaurantsApi.find((restaurant) => String(restaurant.id) === String(id))
    const foods = selectedRestaurant?.menus?.flatMap((menu) =>
      Array.isArray(menu.foods)
        ? menu.foods.map((food) => ({
            id: food.id ?? `${menu.id}-${food.name}`,
            name: food.name ?? 'Unnamed food',
            category: menu.name ?? selectedRestaurant.name ?? 'Food item',
            price: Number(food.price ?? 0),
            image: resolveFoodImage(food.name),
          }))
        : [],
    )

    setMenuItems(foods ?? [])
  }, [restaurantsApi, id])

  return (
    <Page title="Restaurant Details" subtitle="Menu | Reviews | Info tabs">
      <div className="banner">Restaurant Banner Placeholder</div>
      <div className="tabs"><span className="active">Menu</span><span>Reviews</span><span>Info</span></div>
      <div className="content-layout">
        <Card title="Menu Categories" className="sidebar-card">
          <p>Bowls</p><p>Burgers</p><p>Pizza</p><p>Desserts</p>
        </Card>
        <div className="grid">{menuItems.map((i) => <FoodItemCard key={i.id} item={i} />)}</div>
      </div>
    </Page>
  )
}

export function MenuStructurePage() {
  return (
    <Page title="Menu Service Structure" subtitle="Restaurant → Menu → Food Items">
      <div className="hierarchy">
        <Card title="Restaurant Entity"><p className="muted">name, location, openingHours</p></Card>
        <div className="arrow">↓</div>
        <Card title="Menu Entity"><p className="muted">restaurantId, categories, availability</p></Card>
        <div className="arrow">↓</div>
        <Card title="Food Item Entity"><p className="muted">menuId, title, price, tags, stock</p></Card>
      </div>
    </Page>
  )
}

export function CartCheckoutPage() {
  const [open, setOpen] = useState(true)
  return (
    <Page title="Cart & Checkout" subtitle="API-driven order orchestration">
      <div className="grid grid-2">
        <Card title="Order Summary"><p>2x Smoked Salmon Bowl</p><p>1x Truffle Burger</p><h3>Total: $41.00</h3></Card>
        <Card title="Checkout">
          <Input label="Address" placeholder="Delivery address" />
          <Input label="Payment" placeholder="Card placeholder" />
          <Button>Place Order</Button>
        </Card>
      </div>
      <Button variant="secondary" onClick={() => setOpen((v) => !v)}>Toggle Floating Cart</Button>
      <Modal open={open} title="Floating Cart Panel"><p>3 items in cart</p><Button>Go to checkout</Button></Modal>
    </Page>
  )
}

export function TrackingPage() {
  return (
    <Page title="Order Tracking" subtitle="Real-time delivery visibility">
      <div className="grid grid-2">
        <Card title="Delivery Status Timeline">{timeline.map((t, i) => <p key={t}>{i + 1}. {t}</p>)}</Card>
        <Card title="Map Placeholder"><div className="map">Live map area</div></Card>
      </div>
      <Card title="Driver Status"><p>Driver: Alex M.</p><p>ETA: 8 minutes</p></Card>
    </Page>
  )
}

export function ProfilePage() {
  return (
    <Page title="User Profile" subtitle="Account, addresses, order history">
      <div className="grid grid-2">
        <Card title="Orders History"><p>#1042 - Delivered</p><p>#1038 - Delivered</p><p>#1031 - Cancelled</p></Card>
        <Card title="Account Settings"><Input label="Phone" placeholder="+216 ..." /><Input label="Language" placeholder="English" /></Card>
      </div>
      <Card title="Saved Addresses"><p>Home - Lac 2, Tunis</p><p>Office - Berges du Lac</p></Card>
    </Page>
  )
}

export function FeedbackPage() {
  return (
    <Page title="Feedback" subtitle="Ratings and reviews">
      <Card title="Rate Your Order">
        <p className="stars">★★★★★</p>
        <Input label="Review" placeholder="Share your experience" />
        <Button>Submit Review</Button>
      </Card>
    </Page>
  )
}

export function DesignSystemPage() {
  return (
    <Page title="Design System" subtitle="Colors, typography, spacing tokens">
      <div className="grid grid-3">
        <Card title="Colors"><p>Primary: #FF6B35</p><p>Background: #F7F8FA</p><p>Surface: #FFFFFF</p></Card>
        <Card title="Typography"><p>H1 32/40</p><p>H2 24/32</p><p>Body 14/22</p></Card>
        <Card title="Spacing"><p>4, 8, 12, 16, 24, 32</p><p>Radius: 12</p><p>Shadow: soft-md</p></Card>
      </div>
    </Page>
  )
}

export function ComponentsPage() {
  return (
    <Page title="Reusable Components" subtitle="Navbar, sidebar, cards, forms, modal">
      <div className="grid grid-2">
        <Card title="Buttons"><Button>Primary</Button> <Button variant="secondary">Secondary</Button></Card>
        <Card title="Form Input"><Input label="Email" placeholder="component preview" /></Card>
        <RestaurantCard restaurant={restaurants[0]} />
        <FoodItemCard item={foodItems[0]} />
      </div>
    </Page>
  )
}

import { Navigate, Route, Routes } from 'react-router-dom'
import { Navbar, Sidebar } from './components/layout'
import {
  CartCheckoutPage,
  ComponentsPage,
  DesignSystemPage,
  FeedbackPage,
  HomePage,
  LoginPage,
  MenuStructurePage,
  ProfilePage,
  RegisterPage,
  RestaurantDetailsPage,
  RestaurantsPage,
  TrackingPage,
} from './pages'

function App() {
  return (
    <div className="app-shell">
      <Navbar />
      <div className="layout">
        <Sidebar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/auth/login" element={<LoginPage />} />
          <Route path="/auth/register" element={<RegisterPage />} />
          <Route path="/restaurants" element={<RestaurantsPage />} />
          <Route path="/restaurant/:id" element={<RestaurantDetailsPage />} />
          <Route path="/menu-structure" element={<MenuStructurePage />} />
          <Route path="/cart" element={<CartCheckoutPage />} />
          <Route path="/tracking" element={<TrackingPage />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/feedback" element={<FeedbackPage />} />
          <Route path="/design-system" element={<DesignSystemPage />} />
          <Route path="/components" element={<ComponentsPage />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>
    </div>
  )
}

export default App

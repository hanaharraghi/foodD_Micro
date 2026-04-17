import { Link, NavLink } from 'react-router-dom'

export function Navbar() {
  const links = [
    ['/', 'Home'],
    ['/restaurants', 'Restaurants'],
    ['/restaurant/1', 'Details'],
    ['/cart', 'Cart'],
    ['/tracking', 'Tracking'],
    ['/profile', 'Profile'],
    ['/feedback', 'Feedback'],
  ]

  return (
    <header className="navbar">
      <Link to="/" className="brand">
        SmartFood
      </Link>
      <nav className="row wrap gap-sm">
        {links.map(([to, label]) => (
          <NavLink key={to} to={to} className="nav-link">
            {label}
          </NavLink>
        ))}
      </nav>
    </header>
  )
}

export function Sidebar() {
  return (
    <aside className="sidebar">
      <h3>Platform Pages</h3>
      <NavLink to="/auth/login" className="side-link">
        Login
      </NavLink>
      <NavLink to="/auth/register" className="side-link">
        Register
      </NavLink>
      <NavLink to="/design-system" className="side-link">
        Design System
      </NavLink>
      <NavLink to="/components" className="side-link">
        Components
      </NavLink>
      <NavLink to="/menu-structure" className="side-link">
        Menu Service
      </NavLink>
    </aside>
  )
}

export function Page({ title, subtitle, children }) {
  return (
    <main className="page">
      <div className="page-head">
        <h1>{title}</h1>
        <p className="muted">{subtitle}</p>
      </div>
      {children}
    </main>
  )
}

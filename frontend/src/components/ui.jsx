import { Star } from 'lucide-react'

export function Button({ children, variant = 'primary', className = '', ...props }) {
  return (
    <button className={`btn btn-${variant} ${className}`.trim()} {...props}>
      {children}
    </button>
  )
}

export function Input({ label, ...props }) {
  return (
    <label className="input-wrap">
      <span>{label}</span>
      <input className="input" {...props} />
    </label>
  )
}

export function Card({ title, subtitle, children, className = '' }) {
  return (
    <section className={`card ${className}`.trim()}>
      {title ? <h3>{title}</h3> : null}
      {subtitle ? <p className="muted">{subtitle}</p> : null}
      {children}
    </section>
  )
}

export function RestaurantCard({ restaurant }) {
  return (
    <Card className="restaurant-card">
      {restaurant.image ? (
        <img src={restaurant.image} alt={restaurant.name} className="thumb-image" />
      ) : (
        <div className="thumb" />
      )}
      <div className="row between">
        <h3>{restaurant.name}</h3>
        <span className={`pill ${restaurant.status === 'Open' ? 'open' : 'closed'}`}>{restaurant.status}</span>
      </div>
      <p className="muted">{restaurant.category}</p>
      <div className="row between">
        <span>{restaurant.time}</span>
        <span className="row gap-sm">
          <Star size={16} />
          {restaurant.rating}
        </span>
      </div>
    </Card>
  )
}

export function FoodItemCard({ item }) {
  return (
    <Card className="food-card">
      {item.image ? (
        <img src={item.image} alt={item.name} className="food-image" />
      ) : null}
      <div className="row between">
        <div>
          <h3>{item.name}</h3>
          <p className="muted">{item.category}</p>
        </div>
        <strong>${item.price.toFixed(2)}</strong>
      </div>
      <Button>Add to cart</Button>
    </Card>
  )
}

export function Modal({ open, title, children }) {
  if (!open) return null
  return (
    <div className="modal-backdrop">
      <div className="modal">
        <h3>{title}</h3>
        {children}
      </div>
    </div>
  )
}

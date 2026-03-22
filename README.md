# Golf Charity Subscription Platform

This project is a full-stack web application that combines golf performance tracking with charitable contributions. It was developed as part of the Digital Heroes Trainee Selection Process with a focus on creating a clean and meaningful user experience rather than a traditional sports-style interface.

---

## Access Credentials

### Registered Subscriber

* Email: [user@example.com](mailto:user@example.com)
* Password: user123
* Purpose:

  * Enter Stableford scores
  * View personal charity contributions
  * Manage rolling score history

### Administrator

* Email: [admin@digitalheroes.co.in](mailto:admin@digitalheroes.co.in)
* Password: admin123
* Purpose:

  * Access admin dashboard
  * View total prize pools
  * Manage users
  * Run draw simulations

---

## Technical Overview

### Core Score Logic (Rolling 5 System)

* Users submit Stableford scores (range: 1–45)
* The system stores only the latest 5 entries
* When a new score is added, the oldest score is automatically removed
* This ensures that the data always reflects recent performance

### Charity Integration

* 10% of every subscription is allocated to charity
* Users can select their preferred charity
* The dashboard displays real-time contribution updates
* This keeps the platform focused on impact and user engagement

---

## Deployment Decision

### Why Render instead of Vercel?

| Feature                       | Render    | Vercel              |
| ----------------------------- | --------- | ------------------- |
| Java (Spring Boot) Support    | Strong    | Limited             |
| Long-running backend          | Supported | Serverless-oriented |
| Docker Deployment             | Supported | Not native          |
| Database Connection Stability | Reliable  | Can face timeouts   |

Render was chosen because it provides better support for Java-based applications, stable long-running services, and more reliable database connectivity.

---



* [x] User Panel: Login, score entry, dashboard working
* [x] Admin Panel: Statistics and draw management working
* [x] Database: Connected to Supabase PostgreSQL
* [x] Responsive Design: Works on mobile and desktop

---

## Key Highlights

* Clean and user-focused interface
* Real-time charity contribution tracking
* Efficient rolling score logic
* Scalable Docker-based deployment

---

## Future Improvements

* Integration with payment gateways
* Advanced performance analytics
* Mobile application version
* Support for multiple charities

---

## License

This project is developed for evaluation and learning purposes.

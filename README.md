# CRUD Application - Jakarta EE 10 + Angular 19

Enterprise-grade CRUD application built with Java 21, Jakarta EE 10, and Angular 19, following Clean Architecture principles.

## Architecture

### Backend (Java 21 + Jakarta EE 10)
The backend follows Clean Architecture principles with the following layers:

- **Domain Layer**: Contains entities (Usuario, Perfil, Aplicacion, Seccion, Autoriz) and repository interfaces
- **Application Layer**: Contains use cases with business logic
- **Infrastructure Layer**: JPA/Hibernate repository implementations
- **Web Layer**: REST API endpoints using JAX-RS

### Frontend (Angular 19)
- Standalone components
- Services for API communication
- Reactive forms with CRUD operations
- Routing and navigation

## Database Tables

1. **usuario**: User management
2. **perfil**: User profiles/roles
3. **aplicacion**: Applications
4. **seccion**: Sections within applications
5. **autoriz**: Authorization rules (relates profiles to sections with permissions)

## Prerequisites

- Java 21 or higher
- Maven 3.8+
- Node.js 18+ and npm
- Open Liberty Server (configured in the project)

## Project Structure

```
├── domain/              # Domain entities and repository interfaces
├── application/         # Use cases and business logic
├── infrastructure/      # JPA implementations
├── web/                 # REST API and web resources
└── frontend/            # Angular 19 application
```

## Building and Running

### Backend

1. Build the project:
```bash
mvn clean install
```

2. Run with Liberty Maven Plugin:
```bash
cd web
mvn liberty:dev
```

The backend API will be available at: `http://localhost:9080/api`

### Frontend

1. Install dependencies:
```bash
cd frontend
npm install
```

2. Run the development server:
```bash
npm start
```

The frontend will be available at: `http://localhost:4200`

## API Endpoints

All endpoints are prefixed with `/api`

### Usuarios
- GET `/usuarios` - Get all users
- GET `/usuarios/{id}` - Get user by ID
- POST `/usuarios` - Create new user
- PUT `/usuarios/{id}` - Update user
- DELETE `/usuarios/{id}` - Delete user

### Perfiles
- GET `/perfiles` - Get all profiles
- GET `/perfiles/{id}` - Get profile by ID
- POST `/perfiles` - Create new profile
- PUT `/perfiles/{id}` - Update profile
- DELETE `/perfiles/{id}` - Delete profile

### Aplicaciones
- GET `/aplicaciones` - Get all applications
- GET `/aplicaciones/{id}` - Get application by ID
- POST `/aplicaciones` - Create new application
- PUT `/aplicaciones/{id}` - Update application
- DELETE `/aplicaciones/{id}` - Delete application

### Secciones
- GET `/secciones` - Get all sections
- GET `/secciones/{id}` - Get section by ID
- GET `/secciones/aplicacion/{aplicacionId}` - Get sections by application
- POST `/secciones` - Create new section
- PUT `/secciones/{id}` - Update section
- DELETE `/secciones/{id}` - Delete section

### Autorizaciones
- GET `/autorizaciones` - Get all authorizations
- GET `/autorizaciones/{id}` - Get authorization by ID
- GET `/autorizaciones/perfil/{perfilId}` - Get authorizations by profile
- GET `/autorizaciones/seccion/{seccionId}` - Get authorizations by section
- POST `/autorizaciones` - Create new authorization
- PUT `/autorizaciones/{id}` - Update authorization
- DELETE `/autorizaciones/{id}` - Delete authorization

## Technologies Used

### Backend
- Java 21
- Jakarta EE 10
- MicroProfile 6.1
- JPA/Hibernate
- JAX-RS
- Open Liberty Server
- Maven

### Frontend
- Angular 19
- TypeScript
- RxJS
- Standalone Components
- HttpClient

## Development

### Running Tests
```bash
# Backend tests
mvn test

# Frontend tests
cd frontend
npm test
```

### Building for Production
```bash
# Backend
mvn clean package

# Frontend
cd frontend
npm run build
```

## License

This project is created for educational purposes.

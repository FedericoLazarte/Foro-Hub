# Foro.Hub API

Foro.Hub is a REST API designed for managing discussion topics and their corresponding answers. The API provides functionalities to create, read, update, and delete topics, manage answers, and handle user authentication securely using JWT. Additionally, it integrates Flyway for database version control and migrations.

---

## Features

### Topics
- **Create a Topic**: Add a new topic to the forum, specifying details like title, message, author, and course.
- **View Topics**: Retrieve a paginated list of the latest active topics or view detailed information about a specific topic.
- **Update a Topic**: Modify existing topic details.
- **Close a Topic**: Mark a topic as inactive to prevent further answers.
- **Delete a Topic**: Permanently remove a topic and its associated answers.

### Answers
- **Add an Answer**: Post an answer to an active topic.
- **View Answers**: Retrieve answers for a specific active topic, with pagination support.
- **Update an Answer**: Modify the content of an existing answer (only if its topic is active).
- **Delete an Answer**: Remove an answer by its ID.

### User Authentication
- **Login**: Authenticate users and generate a JWT for secure access to the API.
- **Authorization**: Protect endpoints with role-based security, ensuring only authenticated users can interact with sensitive data.

### Database Versioning with Flyway
The application uses **Flyway** for managing database schema migrations, ensuring consistent database structure and easy updates.

---

## Endpoints Overview

### Authentication
| Endpoint      | Method | Description               | Protected |
|---------------|--------|---------------------------|-----------|
| `/login`      | POST   | Authenticate a user.      | No        |

### Topics
| Endpoint               | Method | Description                         | Protected |
|------------------------|--------|-------------------------------------|-----------|
| `/topics`              | POST   | Create a new topic.                 | Yes       |
| `/topics`              | GET    | Get a list of active topics.        | Yes       |
| `/topics/{id}`         | GET    | View details of a specific topic.   | Yes       |
| `/topics/{id}`         | PUT    | Update topic details.               | Yes       |
| `/topics/{id}`         | DELETE | Delete a topic.                     | Yes       |
| `/topics/close/{id}`   | DELETE | Close a topic (mark as inactive).   | Yes       |
| `/topics/{id}/answers` | GET    | Get answers for a specific topic.   | Yes       |

### Answers
| Endpoint      | Method | Description                         | Protected |
|---------------|--------|-------------------------------------|-----------|
| `/answers`    | POST   | Add a new answer to a topic.        | Yes       |
| `/answers/{id}`| PUT   | Update an existing answer.          | Yes       |
| `/answers/{id}`| DELETE| Delete an answer.                  | Yes       |

---

## Technologies Used

- **Spring Boot**: Framework for building RESTful APIs.
- **Spring Security**: Manages authentication and authorization using JWT.
- **Flyway**: Handles database schema migrations.
- **Hibernate/JPA**: Manages database interactions.
- **PostgreSQL**: Relational database used to store data.
- **Swagger (SpringDoc)**: Provides API documentation and testing interface.

---

## Database Migrations with Flyway

Flyway is configured to ensure database schema changes are versioned and applied consistently.

### Configuration
Add migration files to the `src/main/resources/db/migration` directory. For example:
- `V1__create-topics-table.sql`
- `V2__create-answers-table.sql`
- `V3__add-status-column-to-topics.sql`


---

## Running the Project

### Prerequisites
- Java 17+
- PostgreSQL
- Maven
- Flyway CLI (optional)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/foro-hub.git
   cd foro-hub
   ```
2. Configure `application.properties` with your database settings.
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the Swagger API documentation at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Contact
- **Author**: Federico Lazarte
- **Email**: [fedelazarte2015@gmail.com](mailto:fedelazarte2015@gmail.com)

---

Feel free to reach out for any questions or contributions!

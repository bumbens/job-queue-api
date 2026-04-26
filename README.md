# Job Queue API

## Description
Spring Boot REST API designed to handle the queue of tasks. 
It utilises JPA to establish a connection with the PostgreSQL database, add, remove and update tasks in the database.

## Tech Stack
- Java 17
- Spring Boot 4
- PostgreSQL
- JPA / Hibernate
- JUnit 5 / Mockito

## How to Run
1. Clone the repository
```bash
   git clone https://github.com/bumbens/job-queue-api.git
   cd job-queue-api
```

2. Create a PostgreSQL database
```sql
   CREATE DATABASE taskqueue;
```

3. Configure the application
```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
```
   Edit `application.properties` and fill in your PostgreSQL credentials.

4. Run the application
```bash
   ./gradlew bootRun
```

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /jobs | Get all jobs |
| GET | /jobs/{id} | Get job by ID |
| POST | /jobs | Create a new job |
| PUT | /jobs/{id} | Update a job |
| DELETE | /jobs/{id} | Delete a job |

### Example POST /jobs body
```json
{
    "name": "Fix bug",
    "description": "Fix the login bug",
    "priority": "HIGH",
    "dueDate": "2026-05-01"
}
```

## Concurrency
- The status of tasks is automatically updated from `Pending`, through `In Progress` to `Completed`. This process is simulated by `Thread.sleep(2000)`. The implementation is designed to execute in a concurrent environment:
- the `processJob()` method responsible for updating task's status is declared as `synchronized` to avoid `race conditions` and establish proper `Happens-before` relationship
- the `@Scheduled` annotation uses `fixedDelay` argument to ensure that each access `Happens-before` the previous one.


# Bug Tracker API

## Overview

This project is a RESTful CRUD API built with Java Spring Boot, for a bug tracking system, which reports them in the form of tickets. It utilizes Maven for dependency management, Flyway migrations for a PostgreSQL database, and incorporates Spring Security for authentication and authorization with JWT tokens.

This api was primarily designed to work with the [bug tracker web application](https://github.com/renan-aquino/bug-tracker). You can also access the Docker Container repository that has everything you'll need by clicking [here](https://github.com/renan-aquino/bug-tracker-docker).

## Features

- **Authentication and Authorization:** Secure endpoints using Spring Security and JWT tokens.  
- **Create:** Add new records to the database.  
- **Read:** Retrieve information from the database.  
- **Update:** Modify existing records in the database.  
- **Delete:** Remove records from the database.  


## Prerequisites

Ensure you have the following installed before running the application:

- Java Development Kit (JDK)  
- Maven  
- PostgreSQL

## Installation and Setup

1. Clone the repository and go to its directory:

2. Configure your database connection in `src/main/resources/application.properties`.

3. Build and run the application:
```shell
mvn spring-boot:run
```

## Authentication and Authorization

-   The API uses Spring Security for authentication and authorization.
-   JWT tokens are utilized for secure communication between the client and the server.
-   To authenticate, send a POST request to `/auth/login` with valid credentials. (More info below)
-   Include the received token in the Authorization header for subsequent requests.

 To register a new user, send a POST request to `/auth/register` with the following json body structure:
```json
{
	"name": "[insert name]",
	"login": "[insert username]",
	"password": "[insert password]"
}
```

Then, you can login, sending a POST request to `auth/login` with the registered user credentials:
```json
{
	"login": "[insert username]",
	"password": "[insert password]"
}
```
  
The response should return a token and a user, as shown in the example below:
```json
{
	"token": "[JWT token]",
	"user": {
		"name": "[user name]"
	}
}
```

Finally, you can use the token for authorization headers. The value should look like this: `'Bearer [JWT token]'`.

## API Endpoints

-   **Tickets:**
   
    - Create ticket: `POST /ticket`
	    - Request Body:
		     ```json
		    {
				"title": "[insert title]",
				"text": "[insert text for the first message of the ticket]"
			}
		    ```
    - Get all tickets:  `GET /ticket`  
    - Get ticket by id:  `GET /ticket/{id}`  
    - Get a list of tickets by status (OPEN or CLOSED):  `GET /ticket/status/{status}`   
    - Check if a ticket exists:  `GET /ticket/check/{id}`
    - Change the status of a ticket:  `PATCH /ticket/status/{status}`
    - Delete ticket:  `DELETE /ticket/{id}`

-   **Messages:**

    - Create message: `POST /message`
     	 - Request Body:
		     ```json
		    {
				"message": "[insert message]",
				"ticketId": [insert ticket id]
			}
		    ```

    - Get list of messages by ticket id:  `GET /message/{ticketId}`  

-   **Users:**
 
    - Get user information:  `GET /user/{id}`  



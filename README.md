# ZTW Books

ZTW Books is a Spring Boot application for managing a Library made for Advanced Web Technologies Course at Wrocław University of Science and Technology. It includes functionalities for managing books, authors, readers, and rentals.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [License](#license)

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/ztw-bookstore.git
    ```
2. Navigate to the project directory:
    ```sh
    cd ztw-books-backend/books
    ```
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```
4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access it at `http://localhost:8080`.

## API Endpoints

### Books

- **GET /get/books**: Retrieve all books
- **GET /get/book/{id}**: Retrieve a book by ID
- **POST /add/book**: Add a new book
- **PUT /update/book/{id}**: Update a book by ID
- **DELETE /delete/book/{id}**: Delete a book by ID

### Authors

- **GET /get/authors**: Retrieve all authors
- **GET /get/author/{id}**: Retrieve an author by ID
- **POST /add/author**: Add a new author
- **PUT /update/author/{id}**: Update an author by ID
- **DELETE /delete/author/{id}**: Delete an author by ID

### Readers

- **GET /get/readers**: Retrieve all readers
- **GET /get/reader/{id}**: Retrieve a reader by ID
- **POST /add/reader**: Add a new reader
- **PUT /update/reader/{id}**: Update a reader by ID
- **DELETE /delete/reader/{id}**: Delete a reader by ID

### Rentals

- **GET /get/rentals**: Retrieve all rentals
- **GET /get/rental/{id}**: Retrieve a rental by ID
- **POST /rent/book**: Rent a book
- **PUT /return/book/{id}**: Return a book

## License

This project is licensed under the MIT License.

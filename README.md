# VGSI Engineering Coding Exercise: House API
# Jeremy Colon

## Overview

This project is a small REST API with three endpoints for managing a set of houses.
This was accomplished using Java 8, Spring Boot, Spring Data JPA, Spring HATEOAS and H2 database.
Testing was accomplished using JUnit, Mockito, and Spring Boot Test.

Running the application will load the data in the `houses.csv` file into the H2 DB
which makes that data available in memory.

## Starting the service

From the root project directory, run
```./gradlew bootRun```
This will start a web server on localhost:8080

## Endpoints

GET /api/houses
Return a list of all houses. Example response:
```json
[
    {
        "id": 1,
        "ownerFirstName": "John",
        "ownerLastName": "Smith",
        "streetAddress": "Broad St",
        "city": "Hudson",
        "state": "MA",
        "zip": 1749,
        "propertyType": "Single Family",
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8080/api/houses/1"
            }
        ]
    },
    {
        "id": 2,
        "ownerFirstName": "Fred",
        "ownerLastName": "Mack",
        "streetAddress": "Central St",
        "city": "Hudson",
        "state": "MA",
        "zip": 1749,
        "propertyType": "Multi Family",
        "links": [
            {
                "rel": "self",
                "href": "http://localhost:8080/api/houses/2"
            }
        ]
    }
]
```

GET /api/houses/{id}
Returns a single house for the given ID. Example response:
```json
{
    "id": 1,
    "ownerFirstName": "John",
    "ownerLastName": "Smith",
    "streetAddress": "Broad St",
    "city": "Hudson",
    "state": "MA",
    "zip": 1749,
    "propertyType": "Single Family",
    "_links": {
        "houses": {
            "href": "http://localhost:8080/api/houses"
        }
    }
}
```

PUT /api/houses/{id}
Updates a house by the given ID and returns the updated house. Example request body:
```json
{
    "ownerFirstName": "John",
    "ownerLastName": "Smith",
    "streetAddress": "Broad St",
    "city": "Hudson",
    "state": "MA",
    "zip": 1749,
    "propertyType": "Single Family"
}
```

Example response:
```json
{
    "id": 1,
    "ownerFirstName": "John",
    "ownerLastName": "Smith",
    "streetAddress": "Broad St",
    "city": "Hudson",
    "state": "MA",
    "zip": 1749,
    "propertyType": "Single Family",
    "_links": {
        "house": {
            "href": "http://localhost:8080/api/houses/1"
        }
    }
}
```

## Testing

From the root project directory, run
```./gradlew clean test```

## Future Enhancements

- More comprehensive error handling
  - Handle poorly formatted body for PUT request
- Add logging of requests and responses
- Use a more robust database to save the data to disk and persist when the web server stops
- Add some sort of security to access the endpoints.
- Write some performance tests for each API endpoint. Could use JMeter for example.

## Notes on security

Currently there is zero security to this API,
so anybody could access the data being served up. Potentially use public-private key encryption
and only grant access to the API to requests that supply a known key.

Additionally, the data source `houses.csv` could easily be edited by anyone, potentially inserting
malicious data or breaking the conventions of a CSV file so the application is unable to load the data at all.
A much better solution would be to use a database which is password protected instead of a flat file.


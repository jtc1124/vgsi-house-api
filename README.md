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
{
    "houseList": [
        {
            "id": 1,
            "ownerFirstName": "Jack",
            "ownerLastName": "Smith",
            "streetAddress": "South St",
            "city": "Hudson",
            "state": "MA",
            "zip": 1749,
            "propertyType": "Single Family"
        },
        {
            "id": 2,
            "ownerFirstName": "Fred",
            "ownerLastName": "Mack",
            "streetAddress": "Central St",
            "city": "Hudson",
            "state": "MA",
            "zip": 1749,
            "propertyType": "Multi Family"
        }
    ],
    "count": 2,
    "_links": {
        "self": [
            {
                "href": "http://localhost:8080/api/houses/1"
            },
            {
                "href": "http://localhost:8080/api/houses/2"
            }
        ]
    }
}
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
- Improve the links in the GET /houses response

## API Design Choices

I tried to leverage Spring and its sub-projects as much as possible. While that helped reduce
boilerplate code and helped me get everything up and running rather quickly, there is
some weirdness that has caused, specifically with adding the links to the API response body
for the GET /houses endpoint to get all houses. I used Spring HATEOAS for link generation
which may have been to my detriment, since the formatting of links is not exactly what was specified
in the project requirements. I probably would research another link generation library or just written
that part myself if I could redo this.

For data persistence, I used an H2 in-memory database since that seemed like the lowest barrier
to entry for data persistence as opposed to setting up a mySQL DB (or some other on disk DB)
which would require installing extra components or running extra scripts to get this project up and running.


## Notes on security

Currently there is zero security to this API,
so anybody could access the data being served up. Potentially use public-private key encryption
and only grant access to the API to requests that supply a known key.

Additionally, the data source `houses.csv` could easily be edited by anyone, potentially inserting
malicious data or breaking the conventions of a CSV file so the application is unable to load the data at all.
A much better solution would be to use a database which is password protected instead of a flat file.


# kafka-consumer

# CQRS

This project was created to practice CQRS implementation. It uses Kafka as message broker. As it was developed only to exercise it is simple. It has a producer from where data is sent to a message broker(Kafka) and a consumer where data is modified and becomes ready to be stored in a NoSQL database (MongoDB). After, user can retrieve data from the database using an API. Queries can be done by student email, as it could not repeat in database, or not, the later method will retrieve all data from the database. 

Kafka has been used to handle messages asynchronously and a NoSQL database has been chosen due to its scalability and between the inumerous possibilities MongoDB has been chosen due to its capacity to store documents.

![structure](https://raw.githubusercontent.com/Sogolonmvj/kafka-producer/main/structure.drawio.png?token=GHSAT0AAAAAABUJUDCJMPELYBD7SGIN47DSYTVN45A)

## Endpoints documentation

### API (Reader) <https://github.com/Sogolonmvj/reader.git>

> Base Url: <http://localhost:8080/api/v1>

#### Retrieve All Students

Request:

| Method | Endpoint |               Example
| ------ | -------- | ---------------------------------------
| GET    | students | <http://localhost:8080/api/v1/students>

#### Retrieve A Student

Request:

| Method |    Endpoint     |                   Example
| ------ | --------------- | ----------------------------------------------------
| GET    | student/{email} | <http://localhost:8080/api/v1/student/test@test.com>

Example of the data retrieved after an email query was done. The email searched for was ```sh surname@xxxx.com```.

```sh  
    {
        "id": "6274c2a53683033f2e7bd22a",
        "firstName": "StudentName",
        "lastName": "StudentSurname",
        "email": "surname@xxxx.com",
        "gender": "MALE",
        "address": {
            "country": "England",
            "city": "London",
            "postCode": "5211"
        },
        "favouriteSubjects": [
            "Mathematics",
            "Physics",
            "Computer Science",
            "Cybersecurity"
        ],
        "totalSpentInBooks": 6000,
        "created": "2022-05-06T03:39:33.097"
    }
```

### Producer (Kafka-producer) <https://github.com/Sogolonmvj/kafka-producer.git>

> Base Url: <http://localhost:8081/producer/v1>

#### Create A Student

Request:

| Method |   Endpoint   |                  Example
| ------ | ------------ | ----------------------------------------------
| POST   | student/save | <http://localhost:8081/producer/v1/student/save>

Example of the data sent by producer:

```sh
    {
        "firstName": "StudentName",
        "lastName": "StudentSurname",
        "email": "surname@xxxx.com",
        "gender": "MALE",
        "address": {
            "country": "England",
            "city": "London",
            "postCode": "5211"
        },
        "favouriteSubjects": [
            "Mathematics",
            "Physics",
            "Computer Science",
            "Cybersecurity"
        ],
        "totalSpentInBooks": 6000
    }
```

### Consumer (Kafka-consumer) <https://github.com/Sogolonmvj/kafka-consumer.git>

> No endpoints are present for this application

Here data is treated before getting stored in database.

Example of the data after being treated by the consumer application and be stored in database.

```sh  
    {
        _id: ObjectId('6274c2a53683033f2e7bd22a'),
        firstName: 'StudentName',
        lastName: 'StudentSurname',
        email: 'surname@xxxx.com',
        gender: 'MALE',
        address: {
            country: 'England',
            city: 'London',
            postCode: '5211'
        },
        favouriteSubjects: [
            'Mathematics',
            'Physics',
            'Computer Science',
            'Cybersecurity'
        ],
        totalSpentInBooks: '6000',
        created: ISODate('2022-05-06T06:39:33.097Z'),
        _class: 'com.vieira.sogolon.consumer.model.Student'
    }
```

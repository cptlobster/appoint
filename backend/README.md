# Appoint Backend
This is the backend for the Appoint appointment scheduler service. This is a Scalatra servlet that handles all of the business logic, providing a REST API that the frontend interacts with.

## Running

### Using SBT

The `sbt-war` plugin provides the ability to launch the backend on its own. You will need to have a suitable JDK (>= Java 21) and SBT installed on your machine.
```sh
$ sbt
> warStart
```

Open [http://localhost:8080/](http://localhost:8080/) in your browser.

### Using Docker

```sh
docker build -t appoint-backend .
docker run -p 8080:8080 appoint-backend
```

Open [http://localhost:8080/](http://localhost:8080/) in your browser.

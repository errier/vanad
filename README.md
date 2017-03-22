# Vanad demo application

# Maven
Run application
```bash
mvn spring-boot:run
```
Run tests
```bash
mvn test
```
> May sometimes throw Exception:
>
> "Database is already closed (to disable automatic closing at VM shutdown, add ";DB_CLOSE_ON_EXIT=FALSE" to the db URL)".
>
> I don't know how to fix this: I already applied the proposed solution to the application.properties file.

# Docker
Build Docker image
```bash
docker build -f Dockerfile -t vanad .
```

Run Spring boot application
```bash
docker run -tdi --name=vanad vanad mvn spring-boot:run # Run as daemon
docker logs -f vanad # See Spring application booting
docker stop vanad # Stop container
docker start vanad # Start container
```

# Bash commands (using curl)
## Create entity
```bash
./create.sh http://rest-host:port
```
e.g.
```bash
./create.sh http://node9.codenvy.io:56576
```

## Get entity
```bash
./get.sh http://rest-host:port id-of-entity
```
e.g.
```bash
./get.sh http://node9.codenvy.io:56576 123
```

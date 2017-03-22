# Vanad demo application
# Docker
Build Docker image
```bash
docker build -f Dockerfile -t vanad
```

Run Spring boot application
```bash
docker run -ti --name=vanad vanad mvn spring-boot:run
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

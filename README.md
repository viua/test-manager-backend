gradlew bootRun --args='--spring.profiles.active=dev'
gradlew bootRun --args='--DATABASE_HOST=192.168.99.100'


curl http://localhost:8080/api/tm/test

curl --request POST \
     --header "Content-Type: application/json" \
     --data '{"name":"dManchester City"}' \
     http://localhost:8080/api/tm/test

curl --request PUT \
     --header "Content-Type: application/json" \
     --data '{"name":"dManchester City", "status": "pass" }' \
     http://localhost:8080/api/tm/test/5

./gradlew clean build
docker build -t test-manager:1.0 -f src/main/docker/Dockerfile .
./src/main/docker/deploy.sh
./src/main/docker/remove.sh

./gradlew clean build && docker build -t test-manager:1.0 -f src/main/docker/Dockerfile .

./gradlew clean build && docker build -t test-manager:1.0 -f src/main/docker/Dockerfil

-Dspring.profiles.active=dev 
SPRING_PROFILES_ACTIVE=dev

./src/main/docker/remove.sh && \
docker image rm test-manager:1.0 && \
./gradlew clean build && \
docker build -t test-manager:1.0 -f src/main/docker/Dockerfile .
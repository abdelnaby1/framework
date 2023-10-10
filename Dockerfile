#  docker run -it -v ${PWD}/result:/home/selenium-docker/test-output abdelnaby/selenium
# docker run -v ${PWD}/result:/home/selenium-docker/test-output -e BROWSER=chrome -e HUB_HOST=192.168.1.12 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=4 abdelnaby/selenium
FROM bellsoft/liberica-openjdk-alpine:17.0.8
# install curl and jq

RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker


# Add the required files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh


# environment variables
# browser
# hub host
# test suite
# thread count


# run the tests
#ENTRYPOINT java -cp 'libs/*' \
#        -Dselenium.grid.enabled=true \
#        -Dselenium.gird.hubHost=${HUB_HOST} \
#        -Dbrowser=${BROWSER} \
#        org.testng.TestNG \
#        -threadcount=${THREAD_COUNT} \
#        test-suites/${TEST_SUITE}

# start runner.sh

ENTRYPOINT sh runner.sh

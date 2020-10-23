#!/bin/bash

build() {
  ./gradlew clean bootJar
  echo "Building docker image..."
  echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin
  docker build -t ${APP_NAME}:${TRAVIS_COMMIT} .
  docker images
  docker tag badabin ${DOCKER_USERNAME}/${APP_NAME}:${TAG}
  docker push ${DOCKER_USERNAME}/${APP_NAME}:${TAG}
}

case "${TRAVIS_BRANCH}" in
    "development")
        TAG=${TRAVIS_BUILD_NUMBER}
        build
        ;;
    "master")
        TAG=latest
        build
        ;;

    *)
        exit 0
        ;;
esac

FROM maven:3-openjdk-11 as build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn package -DskipTests

FROM eclipse-temurin:11-jre-alpine
RUN mkdir /project
RUN addgroup --system adben && adduser -S -s /bin/false -G adben adben
COPY --from=build /usr/src/project/target/JavaCoffeeShop.jar /project/
RUN chown -R adben:adben /project
WORKDIR /project
USER adben
ENTRYPOINT java -jar JavaCoffeeShop.jar
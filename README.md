# Backend login limit attempt

## Build

Run `mvn clean package` to build the project. The build artifacts will be stored in the `target/` directory.

## Running

- Run `java -jar .\target\BE-student-management-0.0.1-SNAPSHOT.jar`
- Then execute http://localhost:8080/api/swagger-ui/index.html to test APIs.
  - ![jwt](/src/main/resources/static/img_3.png?raw=true "jwt")
- To Access h2-console: http://localhost:8080/api/h2-console

- Generate JWT.
  - ![jwt](/src/main/resources/static/img.png?raw=true "jwt")
- Add JWT in each subsequent request as:
  - ![jwt](/src/main/resources/static/img_1.png?raw=true "jwt")
- Create subject as:
  - ![jwt](/src/main/resources/static/img_2.png?raw=true "jwt")

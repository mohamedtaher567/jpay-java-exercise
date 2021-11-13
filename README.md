# jpay-java-exercise
jpay java exercise
# Backend Orientation
- Backend is written in java using Spring Boot framework.
- Unit tests are written using JUnit Test 5. Mockito is used. Test Coverage: 97.0%


# Fronend Orientation
- Frontend is written using React.js framework.
- No tests are written for the frontend app.

# Building and Running Natively:
- Build backend:
	- `cd Backend`
	- `mvn clean package spring-boot:repackage`
	- You will now find the uber jar file `Backend/target/jpay-0.0.1-SNAPSHOT.jar`
- Run backend:
	- `cd target`
	- You can edit application.properties to set `spring.datasource.url=jdbc:sqlite:/var/sample.db` replacing `/var/sample.db` with your data file path
	- then run `java -jar jpay-0.0.1-SNAPSHOT.jar`
	- Now your spring boot app should be up and accessible to you using `localhost:8080`
- Run frontend:
	- Go to the project directory.
	- `cd Frontend`
	- npm start
	- Now you should be having your react app up and accessible to you using `localhost:3000`

# Building and Running using Docker:
- Without docker-compose.yml
	- `cd Backend`
	- build backend docker image:
	while you still in Backend directory
	`docker build -t jpay:backend .`
	- run docker container for backend
	- put your data file to a certain directory.
	example:
	 `C:/data_path/`
	- rename the data file to `sample.db`
	- hit command to run docker container, bind port and mount data directory 
	- don't forget to replace `C:\\data_path\\` with your data path, that one containeing `sample.db` file
	- if you're running on windows don't forget to escape '\\' instead of '\' to escape backward slash.
	example:
	`docker run -p 8080:8080 -v C:\\data_path\\:/var/ jpay:backend`, you can change the accessible port, `-p xxxx:8080` instead.
	- now backend is running on docker and accessible to your local machine via `localhost:8080`, or `localhost:xxxx`
	- go back to project directory
	- `cd Fronend`
	- build frontend: `docker build -t jpay:frontend .`
	- run docker container for frontend: `docker run  -p 3000:3000 -e REACT_APP_API_BASE_URL=http://localhost:8080 jpay:frontend`, You can replace here value of variable `REACT_APP_API_BASE_URL` with url you want that represents backend url (don't forget protocol prefix, http:// or https://).
	- you can change the accessible port, `yyyy:3000` instead.
	- now react app is up and accessible to you via `localhost:3000` or `localhost:yyyy`.
	- Go to browser and access react app.

- With docker-compose.yml
	- //TODO

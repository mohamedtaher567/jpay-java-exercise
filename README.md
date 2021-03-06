# jpay-java-exercise
jpay java exercise
# Backend Orientation
- Backend is written in java using Spring Boot framework.
- Unit tests are written using JUnit Test 5. Mockito is used. Test Coverage: 93.9%.
- A new table is created in the database using flyway SQL migration.
- The new table is for phone numbers `phone_numbers`. To hold phone numbers data and make it easy for filtering and pagination.
- The table is populated using another flyway java migration.

# Frontend Orientation
- Frontend is written using React.js framework.
- No tests are written for the frontend app.

# Building and Running Natively:
- Build backend:
	- `cd Backend`
	- `mvn clean package spring-boot:repackage`
	- You will now find the uber jar file `Backend/target/jpay-0.0.1-SNAPSHOT.jar`
- Run backend:
	- `cd target`
	- You can edit application.properties to set `spring.datasource.url=jdbc:sqlite:/data/sample.db` replacing `/data/sample.db` with your relative data file path.
	- Also replace flyway url with your relative data directory, i.e. `spring.flyway.url=jdbc:sqlite:data/sample.db`
	- Then run `java -jar jpay-0.0.1-SNAPSHOT.jar`. 
	- Now your spring boot app should be up and accessible to you using `localhost:8080`.
- Run frontend:
	- Go to the project directory.
	- `cd Frontend`
	- `npm start`
	- Now you should be having your react app up and accessible to you using `localhost:3000`.

# Building and Running using Docker:
- With `docker-compose.yml`
	- Go to project directory.
	- Open `docker-compose.yml`.
	- Edit the `${data_path}`, replace it with the directory containing a file named `sample.db` that has your data.
	- Example
		```
		    volumes:
				- C:\\data\\:/data/ # mount local directory containing sample.db file with /var/ direcyory in container.
		```
	- Run command `docker-compose up`.
	- After it finishes building/starting, application would be accessible via `http://localhost:3000`.
	
- Without `docker-compose.yml`
	- Build backend docker image:
		- `cd Backend`
		- Use command: `docker build -t jpay:backend .`.
	- Run docker container for backend:
		- Locate the data file and use its directory (${data_path}) in the running command:
		`docker run -p 8080:8080 -v ${data_path}:/data/ jpay:backend`.
		- Example:
			`docker run -p 8080:8080 -v C:\\data_path\\:/data/ jpay:backend`
		- Local port can be changed also, `-p xxxx:8080` instead.
		- If you're running on windows, don't forget to escape back slash (`\`).
	- Now backend is running on docker and accessible to your local machine via `localhost:8080`, or `localhost:xxxx`.
	- Go back to project directory.
	- Build frontend docker image:
		- `cd Fronend`
		- Use command: `docker build -t jpay:frontend .`.
	- Run docker container for frontend: 
		- `docker run  -p 3000:3000 -e REACT_APP_API_BASE_URL=http://localhost:8080 jpay:frontend`
		- Variable `REACT_APP_API_BASE_URL`'s value can be changed to url you want that represents backend url (don't forget protocol prefix, `http://` or `https://`).
		- Local port can be changedalso, `-p yyyy:3000` instead.
	- Now react app is up and accessible to you via `localhost:3000` or `localhost:yyyy`.
	- Go to browser and access react app via `localhost:3000`.

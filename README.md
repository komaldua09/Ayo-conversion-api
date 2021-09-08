# Ayo Conversion API
This API converts Metric to imperial and vice versa for the following 5 categories:

    1. Distance - Km <-> Miles
    2. Area - sq Km <-> sq Miles
    3. Volume - Litres <-> Gallon
    4. Weight - Kg <-> Pound
    5. Temperature - F <-> C

The postman collection is located under test/postman directory

###Application start
Build the project using
```mvn clean install```# Ayo Conversion API
This API converts Metric to imperial and vice versa for the following 5 categories:

    1. Distance - Km <-> Miles
    2. Area - sq Km <-> sq Miles
    3. Volume - Litres <-> Gallon
    4. Weight - Kg <-> Pound
    5. Temperature - F <-> C

The postman collection is located under test/postman directory

###Application start
Build the project using
```mvn clean install```

To run the application: 
```mvn spring-boot:run```

The Junits can be found under test/java directory

###Accessing swagger
http://localhost:8080/swagger-ui/

###Build docker image
```docker build -t ayo-conversion:1.0 .```

###Run docker image
```docker run ayo-conversion:1.0```
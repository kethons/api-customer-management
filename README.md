# Customer Management REST API example application

This is a bare-bones example of a customer application providing a REST
API to create and fetch customer details.

## Run the app

    Run as a SpringBoot app. It uses H2 in memory dd for persistence. 
    Visit http://localhost:8080/swagger-ui.html for API swagger specification.
    Visit http://localhost:8080/h2-console/  for H2 console.
    Note: On H2 console, value for JDBC URL needs to be figured out from the spring boot logs during app launch. Search for log statement like below - 
    H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:8792fdb1-49d7-4ba2-a27e-12da2f2b4c39'

# REST API

The REST API to the example customer management app is described below.

## Create a new customer

### Request

`POST /customers/`

    curl --location --request POST 'http://localhost:8080/customers' \
    --header 'Content-Type: application/json' \
	--header 'Accept: application/json' \
	--data-raw '{
    "firstName":"Ketki",
    "lastName":"Kumari",
    "dateOfBirth":"2014-12-14"
	}'
    

### Response

    HTTP/1.1 201 Created
    Status: 201 Created
    
    {
    "firstName": "Ketki",
    "lastName": "Kumari",
    "dateOfBirth": "2014-12-14",
    "customerId": "2h5GsjWkGEzqYv7PshDzu2tQRrS1Ca",
    "links": [
        {
            "rel": "self",
            "href": "http://localhost:8080/customers/2h5GsjWkGEzqYv7PshDzu2tQRrS1Ca"
        }
    ]
}

## Try to create the same customer again

### Request

`POST /customers/`

	curl --location --request POST 'http://localhost:8080/customers' \
	--header 'Content-Type: application/json' \
	--header 'Accept: application/json' \
	--data-raw '{
    "firstName":"Ketki",
    "lastName":"Kumari",
    "dateOfBirth":"2014-12-14"
	}'

### Response

    HTTP/1.1 400 Bad Request
    Status: 400 Bad Request
    
    {
    "timestamp": "2022-05-11T01:20:19.156+00:00",
    "message": "Customer already exists"
    }


## Get a specific customer details

### Request

`GET /customer/{id}`

    curl --location --request GET 'http://localhost:8080/customers/2h5GsjWkGEzqYv7PshDzu2tQRrS1Ca'

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
    
    {
    "firstName": "Ketki",
    "lastName": "Kumari",
    "dateOfBirth": "2014-12-14",
    "customerId": "2h5GsjWkGEzqYv7PshDzu2tQRrS1Ca"
}

## Get a non-existent customer

### Request

`GET /customer/{id}`

    curl --location --request GET 'http://localhost:8080/customers/xxx'

### Response

     HTTP/1.1 404 Not Found
     Status: 404 Not Found
    
    {
    "timestamp": "2022-05-11T01:18:03.736+00:00",
    "message": "Customer with provided id is not found"
    }


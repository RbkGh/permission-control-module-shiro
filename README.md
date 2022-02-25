# Permission Control Module Service
Demonstration
---
![](https://github.com/RbkGh/permission-control-module-shiro/raw/master/image/img.png)
## Development Task
```text
Using SpringBoot + Mybatis + Shiro + JWT to build a login and permission control module
for a simple ecommerce API. Mysql & Redis might be required to store and
 cache the data & session
APIs:
  Login API
  Product List API
  Product Post API
  
  
| Roles    | Permission 
| -------- | ----------------------- 
| Buyer    |  The buyer is able to
|          |  1. Login
|          |  2. View the list of products from all sellers
----------------------------------------
| Seller   |  The seller is able to
|          |  1. Login
|          |  2. Add a new product
|          |  3. View only the products belonging to him

```
# Run tests
### Run Project On Command line 
1. docker-compose up <br>  
2. ./mvnw test  [do this on mac after starting docker which brings mysql up] windows should be : mwnw test
3. ./mvnw spring-boot:run <br>
Application will be live on http://localhost:8080

* _Tests encompass an 86% test coverage_

* _TestContainers were used to build the tests so that integration tests could be easier_

# Manual Testing 

### Initially Created User Credentials, Password , Role And Permission
| username  | password | role   | permission                 |
| --------  | -------- | -----  | ----------                 |
| rodney    | password | buyer  | products:view              |
| max       | password | seller | products:view,edit         |
| bea       | password | seller | products:view,edit         |



## Login
**if your token is reported to be forbidden, either you restarted the server or your token has expired, so you need to just do a new request to /login**
> Login user by name rodney and password of password
```text
Request:

POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "rodney",
  "password": "password"
}

Response : Returns token in header of response

HTTP/1.1 200 
Access-Control-Allow-Methods: GET,POST,OPTIONS,PUT,DELETE
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU3NzEwMDIsInVzZXJuYW1lIjoicm9kbmV5In0.ocAqCeXbuR4D7-6nC66SnDSFhvGL-2gRTSZkNo2I3Fg
Content-Length: 0
Date: Fri, 25 Feb 2022 06:31:42 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 724ms; Content length: 0 bytes


```

## View All Products For Sale 
> request to view all products as buyer
```text

Request:

GET http://localhost:8080/products
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU3NzE3MTcsInVzZXJuYW1lIjoicm9kbmV5In0.Hud_GzMzrR3d6ZoLDpq2qKueIPzqIr_ZmIc29Dgpx9I
Content-Type: application/json

Response:

GET http://localhost:8080/products

HTTP/1.1 200 
Access-Control-Allow-Methods: GET,POST,OPTIONS,PUT,DELETE
Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Thu, 24-Feb-2022 06:44:17 GMT
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Feb 2022 06:44:18 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Success",
  "data": [
    {
      "id": 1,
      "username": "max",
      "productname": "Samsung 21inch TV",
      "productprice": 300,
      "productqty": 30
    },
    {
      "id": 2,
      "username": "max",
      "productname": "LG 21inch TV",
      "productprice": 440,
      "productqty": 4
    },
    {
      "id": 3,
      "username": "max",
      "productname": "Yamaha Blender",
      "productprice": 320,
      "productqty": 30
    },
    {
      "id": 4,
      "username": "bea",
      "productname": "RBL Guitar",
      "productprice": 5000,
      "productqty": 20
    },
    {
      "id": 5,
      "username": "bea",
      "productname": "Home Sofa",
      "productprice": 45000,
      "productqty": 23
    },
    {
      "id": 6,
      "username": "max",
      "productname": "Sony Sound System",
      "productprice": 234500,
      "productqty": 40
    },
    {
      "id": 7,
      "username": "max",
      "productname": "Sony Sound System",
      "productprice": 234500,
      "productqty": 40
    }
  ]
}

```

### View Products Listed By User With Seller Role
> To do this, we must make a post request to /login with either max or bea to get the jwt, then use that token to access this endpoint
```text
Request: 

GET http://localhost:8080/products/user/max
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU3NzIwNDEsInVzZXJuYW1lIjoibWF4In0.eQZs7CInBHuaLNH-YWKT4weJNF_jQwQH86lT6_GjUbA

Response:
GET http://localhost:8080/products/user/max

HTTP/1.1 200 
Access-Control-Allow-Methods: GET,POST,OPTIONS,PUT,DELETE
Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Thu, 24-Feb-2022 06:49:56 GMT
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Feb 2022 06:49:56 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Success",
  "data": [
    {
      "productOwner": "max",
      "productName": "Samsung 21inch TV",
      "productPrice": 300,
      "productQty": 30
    },
    {
      "productOwner": "max",
      "productName": "LG 21inch TV",
      "productPrice": 440,
      "productQty": 4
    },
    {
      "productOwner": "max",
      "productName": "Yamaha Blender",
      "productPrice": 320,
      "productQty": 30
    },
    {
      "productOwner": "max",
      "productName": "Sony Sound System",
      "productPrice": 234500,
      "productQty": 40
    },
    {
      "productOwner": "max",
      "productName": "Sony Sound System",
      "productPrice": 234500,
      "productQty": 40
    }
  ]
}

Response code: 200; Time: 214ms; Content length: 487 bytes

```
_Note: Even if Max uses his token but passes in bea in the endpoint, only his products alone will return_

### Create Product
> Again, only a user with buyer role can create a product , so currently only bea and max can create a product
```text

Request: 

POST http://localhost:8080/products
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU3NzI1MDgsInVzZXJuYW1lIjoiYmVhIn0.T0AAvr-MGEhzrg54hMXKY4AqUHUrs6mYOMeNNOXrUTg
Content-Type: application/json

{
  "productName": "Gas Cookser",
  "productPrice" : 3305,
  "productQty" : 4322
}

Response:

Access-Control-Allow-Methods: GET,POST,OPTIONS,PUT,DELETE
Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Thu, 24-Feb-2022 06:57:52 GMT
Location: http://localhost:8080/products/products/0
Content-Length: 0
Date: Fri, 25 Feb 2022 06:57:52 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 201; Time: 237ms; Content length: 0 bytes

```

### Create Product As A Buyer , this should fail and return a forbidden error
> first, we make a login request using rodney as the username , now we will get a buyer token,let's try to use that token to create a product to see
```text
Request : 

POST http://localhost:8080/products
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDU3NzI4NTEsInVzZXJuYW1lIjoicm9kbmV5In0.1uJcPG_MTh17RM9XDhw78tmc1Uwfdqqul_fZPSHHpEM
Content-Type: application/json

{
  "productName": "Gold",
  "productPrice" : 9305,
  "productQty" : 20
}


Response : 

Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 25 Feb 2022 07:03:25 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "message": "Forbidden",
  "data": null
}

Response code: 403; Time: 278ms; Content length: 35 bytes

```

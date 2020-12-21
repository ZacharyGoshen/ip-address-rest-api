# IP Address Rest API

**How to use**
---

* Run `ip-address-rest-api/mvnw spring-boot:run` to start the application

* Open a separate command line and send any of the following requests

**List IP Addresses**
---
Return JSON data of all IP addresses

* **URL**
  
  /ip-addresses
  
* **Method**

  `GET`

* **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

    * **Code:** 200 <br />
      **Content:** `[[{"id":1,"address":"10.0.0.0","status":"acquired"},{"id":2,"address":"10.0.0.1","status":"available"},{"id":3,"address":"10.0.0.2","status":"available"},{"id":4,"address":"10.0.0.3","status":"acquired"}]]`

* **Error Response:**

* **Sample Call:**
  
  curl -X GET "localhost:8080/ip-addresses" 

**Create IP Addresses**
---
Add all IP addresses within a CIDR block and set their status as "available"

* **URL**

  /ip-addresses

* **Method**

  `POST`

* **URL Params**

  **Required**
  
  cidr-block=[string]

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />

* **Error Response:**

* **Sample Call:**
  
  curl -X POST "localhost:8080/ip-addresses?cidr-block=10.0.0.0/24"

**Update IP Address Status**
---
Set the status of an IP address to "available" or "acquired"

* **URL**

  /ip-addresses/:id

* **Method**

  `PATCH`

* **URL Params**

  **Required**

  status=[available || acquired]

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />

* **Error Response:**

* **Sample Call:**

  curl -X PATCH "localhost:8080/ip-addresses/2?status=acquired" 
### Motion Coding Assignment

#### Problem Description: Redacted for Security reasons

#### Build and Execute Instructions

1. Install Docker 
2. `docker build -t motion-exercise-anand:latest .` to build the source image
3. `docker-compose up` to bring up the application and backing mongodb database. 
4. The api is now available at http://locahost:8080/products
5. swagger-ui, which shows api documentation is available at http://locahost:8080/swagger-ui.html

#### Assumptions: 
1. Every Product has a set of base attributes that are always present. 
2. Every product also has a set of type specific attributes, which are also mandatory. 
3. If any field is absent, the request is rejected. 
4. The API only allows a single product to be added at a time. 
5. The current iteration assumes the supported product types are `coffee` and `fish`. Additional types have to be explicitly added to the list of supported types for ease. 
6. All products are stored in a single table ( collection ) in a Mongodb database. This means that the products have to be differentiated through the presence of type specific attributes, for e.g productType=coffee for Coffee etc. . 


#### Architecture Notes: 

##### Language: 
This application uses Java with Spring as the backing framework to develop Rest APIs and Database integrations. 
The choice to go with Java + Spring is the ability for Java to model Real world Objects nicely in a type safe manner. 
Spring provides excellent support in defining middleware such as endpoints, validations, database support with very little
boilerplate, yet providing hooks into being extendible. 

##### Datastore: 
The backing datastore for this application is MongoDB, which was chose for its support for schemaless structures, which fits 
well with the problem description here. 

##### Validation: 
The assumption made here is every attribute is mandatory for each Producttype. The example Product type this application supports
are `Coffee` and `Fish`. Additional Product types can be added easily by extending the `Product` class, which defines the base variables
shared by every Product , as laid out by the problem description. 

The annotation based validation by Spring/Java provides a easy way for flexible validation criteria should the rules change.


##### Tests: 
Unit and Integration tests are located under `src/test/main/` and should be self explanatory

#### Example API Requests: 

- Add Coffee
``` 
curl -X POST -H "Content-Type: application/json" --data "{\"productType\": \"coffee\", \"userId\":\"1\", \"typeDescription\": \"coffee from brazil\", \"unitMass\": 10.9,  \"createTimeMillis\": \"1212312312313\", \"expiryDateMillis\": \"1231231311\",  \"cuppingScore\" : 99.1, \"variety\": \"Arabica\"}" http://localhost:8080/products/create

{"productType":"coffee","id":"5c3d85668ee484586a0e0bdf","userId":"1","productType":null,"typeDescription":"coffee from brazil","unitMass":10.9,"createTimeMillis":1212312312313,"expiryDateMillis":1231231311,"cuppingScore":99.1,"variety":"Arabica"}


``` 
- Add Fish

```
 curl -X POST -H "Content-Type: application/json" --data "{\"productType\": \"fish\", \"userId\":\"1\", \"typeDescription\": \"tuna fish\", \"unitMass\": 10.9,  \"createTimeMillis\": \"1212312312313\", \"expiryDateMillis\": \"1231231311\",  \"vesselId\": \"v123\",  \"catchDateMillis\": \"2131231\"}" http://localhost:8080/products/create
 
 {"productType":"fish","id":"5c3d880ba7b11b0001bfd84e","userId":"1","productType":null,"typeDescription":"tuna fish","unitMass":10.9,"createTimeMillis":1212312312313,"expiryDateMillis":1231231311,"vesselId":"v123","catchDateMillis":2131231
```

- API call missing mandatory field

In this case, a fish is missing `vesselId`. 
``` 
curl -X POST -H "Content-Type: application/json" --data "{\"productType\": \"fish\", \"userId\":\"1\", \"typeDescription\": \"tuna fish\", \"unitMass\": 10.9,  \"createTimeMillis\": \"121231231313\", \"expiryDateMillis\": \"1231231311\",  \"catchDateMillis\": \"2131231\"}" http://localhost:8080/products/create

{"timestamp":1547536503730,"status":400,"error":"Bad Request","errors":[{"codes":["NotNull.product.vesselId","NotNull.vesselId","NotNull.java.lang.String","NotNull"],"arguments":[{"codes":["product.vesselId","vesselId"],"arguments":null,"defaultMessage":"vesselId","code":"vesselId"}],"defaultMessage":"must not be null","objectName":"product","field":"vesselId","rejectedValue":null,"bindingFailure":false,"code":"NotNull"}],"message":"Validation failed for object='product'. Error count: 1","path":"/products/create"}

```

- List of supported product types

``` 
 curl  http://localhost:8080/products/types
 
 ["coffee","fish"]
 
```

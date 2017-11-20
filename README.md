# Client side load balancing using Ribbon and Feign

This demo project is used to demonstrate a client side load balancing setup using Netflix's Ribbon along with Feign. Tools used in this demonstation include:

- Ribbon
- Feign
- Consul
- MongoDB



### Project:
The demo is about a pet service that stores basic information about a pet (specifically name and a unique id). This project contains two sub projects:

`pet-service`: Is the service that interacts with MongoDB to get, add and delete pet information. When the service starts up it will register with consul.

`pets-client`: The client service that uses the Feign HTTP pets client. It uses consul to discover which services are active for load-balencing.

### Usage

1. Configure properties in `pet.service`
    - Set `Consul` properties to point to a running Consul agent
    - Set `MongoDB` properties to point to a running MongoDB node
2. Open up `pet-service` and run (do this for each instance of the service you want running):
    - `SERVER_PORT=<SOME_PORT> mvn spring-boot:run`. Note: Select different ports for each service running
    - Validate that you can see your service registered at `http://localhost:8500/ui/#/dc1/services`
3. Open up `pets-client` in a terminal and run:
    - `SERVER_PORT=<SOME_PORT> mvn spring-boot:run`. Note: Select different ports for each service running
4. Make a request to the `pets-client` port


#### Available routes:
1. GET /ping - Pings a node and returns the port of the node it used
2. GET /pets - Returns the list of all pets
3. GET /pets/{id} - Returns the pet information given an id
4. POST /pets/register - BODY = (`{ "name": "SOME_NAME" }`) Adds a new pet
5. DELETE /pets/adopt/{id} - Removes a pet from the list


#### Changing routes:
Change load balancing rules in `application.yml` in the **pets-client** project. For routing configurations see[Ribbon configurations](https://github.com/Netflix/ribbon/wiki/Working-with-load-balancers) 

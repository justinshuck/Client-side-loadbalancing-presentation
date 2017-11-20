# Why client side load balancing?
With the growth of microservice oriented architecture, the move towards client side load balancing becomes more appealing for various reasons.

In a traditional DNS Load balancing setup, Traffic sent to a dedicated service to determine where to send the traffic using rules such as round-robbin. Can be done using Hardware such as F5 or software services such as Amazon ELB.
Some drawbacks:
- Single point of failure
- Bottleneck as all microservice traffic will need to go through the load balancer.

![Old](https://storage.googleapis.com/cdn.thenewstack.io/media/2015/09/loadbalancers.001.png)

In a client side load balancing setup. The client is in charge of determining where to route the traffic to.

![New](https://storage.googleapis.com/cdn.thenewstack.io/media/2015/09/loadbalancers.003.png)

***


# Ribbon:
Ribbon is a client side IPC library. Some of its key features include:
- Load balancing
- Static server listing & Service discovery integration
- Fault tolerance
- Configurable load balancing rules, including:
    - Round robin
    - Availibility
    - Weighted response time


**Named-Component** - Ribbon load balancers are comprised of an ensemble of named components. Ensemble is named and given to application developers to use.


Ribbon supports programmatic configuration for load balancing and fallback as well as property based configuration
Ribbon LoadBalancers have specific components to them:
- `Rule` - *Determines which server to return*
- `Ping` - *Runs in the background to ensure if the server is alive*
- `ServerList` - *Static or dynamic list of servers. If using a dynamic list a background thread periodically runs, on a user configured value, to refresh and filter the list*

**Some of the rules include:**

*RandomRule* - Randomly returns a server from the list

*BestAvailableRule* - Uses the first server in the list that has the least amount of concurrent connections

*WeightedResponseTimeRule* - Adjusts the priority based off of the average response time of requests. This will periodically adjust the weight on a configed value

**

Repo: https://github.com/Netflix/ribbon

***


# Feign:
Feign is a declaritive HTTP client builder that was built by Adrian Cole when he was working at Neflix on another project. Although it wasn't heavily used in netflix, it was moved to a community project in 2016.

Feign processes anotations into template requests. Before the request is made the parameters are applied. Feign has a Ribbon client that overrides the URL resolution of the Feign client. Thus all you would need to do is pass the Ribbon client name as the Feign `client`.


Repo: https://github.com/OpenFeign/feign



### References & Links:

- https://github.com/Netflix/ribbon/wiki/Getting-Started
- https://github.com/Netflix/ribbon/wiki/Working-with-load-balancers
- https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html
- https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
- https://thenewstack.io/baker-street-avoiding-bottlenecks-with-a-client-side-load-balancer-for-microservices/


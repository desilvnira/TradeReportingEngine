# TradeReportingEngine
Welcome to the TradeReportingEngine. This is a Java project made using springboot for storing (parsing local XML files using document builders and writing them to the DB) and reading trades (exposing an endpoint that queries the DB in an object orientated way) from an in memory H2 DB

In order to run the program you must first have Java 17 installed and an API platform service such as Postman to interact with the exposed endpoints.

Start off by cloning the repo to a local space and opening the project in you preferred IDE (Intellij). 

Run the TradeReportingEngine class, this will start a spring server and spin up a local in memory H2 DB which you can write to using the /setTrades endpoint. One thing to note is that the DB will not persist once the server has been stopped so writing and reading from the db can only happen while the spring backend server is up and running. 

There are two exposed endpoints, one to POST the trades to the DB by parsing the XML files stored within the repo, the other to GET the data in the DB after it has some filtering logic run on it. The following are the endpoints which can be run on an app like Postman

POST: http://localhost:8080/setTrades
GET: http://localhost:8080/getTrades

## Decisions and Design

One of the important decisions was to only include the data that had some logical relevance to task at hand in the DB. The XML files have a lot of information about the trade but for this specific task there was only 4 parameters that were needed. If more parameters were needed, the XML parser could be altered to grab more elements by the tag name via the XMLParserService and write them to the DB. 

The design patterns in based off of the conventional MVC pattern where the dao extends JPA repository giving all the CRUD functionality to the service layer which handles all the business logic that needs to be applied on the data before feeding back to the controller which marks as the interaction point between the client and the server.

I have a separate service layer for the XML parsing as this will be a core functionality with this application which relies on XML files as the data source before they are parsed and written to the DB. This allows for future changes for the business when it comes to which data to use and not use from these XML files. There is also an idea to have this in a separate utils package as this is quite a generic job that could be shared...

I have also left the POST endpoint for setTrades exposed for future implementation where there is a client side application which can POST over new XML files which is more relaisitic than having them stored in the server side repo.

The logic about what trades to return are handled at the dao layer through native queries as the JPA repository has very basic CRUD functions. I also dont want to use the popular findAll() function on the DB before running business logic as this may not be a scalable solution in the long run with the increase in data. So the logic has been abstracted to the dao where any custom queries can reside depending on the business needs.



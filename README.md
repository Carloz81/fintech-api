# FINTECH-API
Repo for a demo project

##Requirements
* The API will expose an endpoint which accepts the user information (customerID, initialCredit).
* Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.
* Also, if initialCredit is not 0, a transaction will be sent to the new account.
* Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

##Constraints
For storing the information, the data can be saved in memory and not actually persisted to an external database.
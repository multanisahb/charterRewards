# Charter Rewards
Displays reward information for charter customers.

# Endpoints Suppoerted
```
 GET http://localhost:8080/charter/rewards/customer/{{customerId}}
```
- Example
```
 GET http://localhost:8080/charter/rewards/customer/1
```
# Steps To Run locally
## Step 1: Setup Postgres
```
 docker-compose -f docker-compose.yml up
```

## Step 2: Build Application
```
 mvn clean install
```

## Step 3: Run Application
- java command OR IDE run spring boot settings project

## Step 4: Tear Down Database (Restart)
```
./tearDownDatabase.sh
```
- Repeat steps 1 - 3



## PACKAGE DELIVERY APPLICATION

**It has 4 endpoints**
1. package/cost
2. package/costWithPackageObjects
3. package/time
4. package/addVehicles


### `package/cost`
* Given packages input this endpoint will calculate cost for all packages.
* input format
  * {package_id} {weight} {distance} {offer_code}
* output format
  ```
  [
    {
      "packageId": value,
      "discount": value,
      "cost": value
    }
    ...
  ]
  ```


### `package/costWithPackageObjects`
* Given packages input this endpoint will calculate cost for all packages.
* input format
    ```
  [
     {
       "packageId": value,
       "weight": value,
       "distance": value,
       "offerCode": value
     }
    ...
  ]
  ```
* output format
  ```
  [
    {
      "packageId": value,
      "discount": value,
      "cost": value
    }
    ...
  ]
  ```

### `package/addVehicles`
* Adds vehicle to package delivery system
* input format
  `{vehicles_number} {max_speed} {max_capacity}`
* output format
  ```
  [
    {
      "speed": value,
      "capacity": value,
      "availableIn": value,
      "available": value
    }
    ...
  ]
  ```


### `package/time`
* Calculates delivery time along with cost
* input format
    ```
  [
     {
       "packageId": value,
       "weight": value,
       "distance": value,
       "offerCode": value
     }
    ...
  ]
  ```
* output format
  ```
  [
    {
      "costReport": {
         "discount": 0,
         "cost": 1475,
         "apackage": {
            "packageId": "PKG2",
            "weight": 75,
            "distance": 125,
            "offerCode": "OFR008"
         }
      }
      "deliveryTime": value
    }
    ...
  ]
  ```

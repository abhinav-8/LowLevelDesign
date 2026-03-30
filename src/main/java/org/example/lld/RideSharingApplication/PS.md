## Problem Statement:

The application allows users to share rides on a route.

* Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
* Users can search and select one from multiple available rides on a route with the same source and destination.

* Usecases:
  - Application should allow to register users:
  ```create_user(name:"John",gender:M,age:26)```

  * Users should be able to add vehicle (a user can have multiple vehicles):
  ```create_vehicle(name:"John", vehicle:"swift", regNo:"KA-09-32321")```

  * Users should be able offer rides:
  ```offer_ride(name: "John", vehicle: "swift", seats_available: 3, origin: Bangalore, destination: Mysore, start: 'March 21, 2024, 10 AM', duration: 3h)```

  * Users should be able to select rides based on origin, destination, and selection preference.
  ```select_ride(name:"smith",origin:bangalore,destination:mysore,seats_requierd:2,earliest_ending_ride)```

  * Users can have preferences like:

    - Earliest ending ride or lowest duration ride
    - Preferred Vehicle (Activa/Polo/XUV)
    - Most Vacant.
  
  * Users should be able to get all the rides offered and taken by the user.
// Use meaningful and pronounceable variable names

// Bad
String yyyymmdstr = new SimpleDateFormat("YYYY/MM/DD").format(new Date());

// Good 
String currentDate = new SimpleDateFormat("YYYY/MM/DD").format(new Date());

// Use the same vocabulary for the same type of variable

// Bad 
 
getUserInfo();
getClientData();
getCustomerRecord();
 

// Good 
 
getUser();
 

// Bad 
 
// What the heck is 86400000 for?
setTimeout(blastOff, 86400000);

 

// Good 
 
// Declare them as capitalized `const` globals.
public static final int MILLISECONDS_IN_A_DAY = 86400000;

setTimeout(blastOff, MILLISECONDS_IN_A_DAY);

 

// Use explanatory variables
// Bad 
 
String address = "One Infinite Loop, Cupertino 95014";
String cityZipCodeRegex = "/^[^,\\\\]+[,\\\\\\s]+(.+?)\\s*(\\d{5})?$/";

saveCityZipCode(address.split(cityZipCodeRegex)[0],
                address.split(cityZipCodeRegex)[1]);
 

// Good 
 
  String address = "One Infinite Loop, Cupertino 95014";
  String cityZipCodeRegex = "/^[^,\\\\]+[,\\\\\\s]+(.+?)\\s*(\\d{5})?$/";

  String city = address.split(cityZipCodeRegex)[0];
  String zipCode = address.split(cityZipCodeRegex)[1];

  saveCityZipCode(city, zipCode);

 


// Avoid Mental Mapping
// Bad 
 
String [] l = {"Austin", "New York", "San Francisco"};

for (int i = 0; i < l.length; i++) {
    String li = l[i];
    doStuff();
    doSomeOtherStuff();
    // ...
    // ...
    // ...
    // Wait, what is `$li` for again?
    dispatch(li);
 }
 

// Good 

 
String[] locations = {"Austin", "New York", "San Francisco"};

for (String location : locations) {
    doStuff();
    doSomeOtherStuff();
    // ...
    // ...
    // ...
    dispatch(location);
 }
 


// Don't add unneeded context

// Bad 

 
class Car {
  public String carMake = "Honda";
  public String carModel = "Accord";
  public String carColor = "Blue";
}

void paintCar(Car car) {
  car.carColor = "Red";
}
 

// Good 
 
class Car {
  public String make = "Honda";
  public String model = "Accord";
  public String color = "Blue";
}

void paintCar(Car car) {
  car.color = "Red";
}
 

// Functions should do one thing
// Bad 
 
public void emailClients(List<Client> clients) {
    for (Client client : clients) {
        Client clientRecord = repository.findOne(client.getId());
        if (clientRecord.isActive()){
            email(client);
        }
    }
}
 

// Good 
 
public void emailClients(List<Client> clients) {
    for (Client client : clients) {
        if (isActiveClient(client)) {
            email(client);
        }
    }
}

private boolean isActiveClient(Client client) {
    Client clientRecord = repository.findOne(client.getId());
    return clientRecord.isActive();
}
 

// Function names should say what they do

// Bad 
 
private void addToDate(Date date, int month){
    //..
}

Date date = new Date();

// It's hard to to tell from the method name what is added
addToDate(date, 1);
 
// Good 
 
private void addMonthToDate(Date date, int month){
    //..
}

Date date = new Date();
addMonthToDate(1, date);
 
// Don't use flags as function parameters (Avoid Passing Booleans to Functions)
 
// Bad 
 
  // ❌ Avoid using boolean flags in functions
  distance(pointA, pointB, true)

 

// Good 

 

  // ✅ Instead, split the logic in two separate functions
  distanceInKilometers(pointA, pointB)
  distanceInMiles(pointA, pointB)

 

// Only comment things that have business logic complexity.
Comments are an apology, not a requirement. Good code *mostly* documents itself.

// Bad 
 
// Creating a List of customer names 
List<String> customerNames = Arrays.asList('Bob', 'Linda', 'Steve', 'Mary'); 

// Using Stream findFirst() 
Optional<String> firstCustomer = customerNames.stream().findFirst(); 

// if the stream is empty, an empty 
// Optional is returned. 
if (firstCustomer.isPresent()) { 
    System.out.println(firstCustomer.get()); 
} 
else { 
    System.out.println("no value"); 
} 
 


// Good 
 
List<String> customerNames = Arrays.asList('Bob', 'Linda', 'Steve', 'Mary'); 

Optional<String> firstCustomer = customerNames.stream().findFirst(); 

if (firstCustomer.isPresent()) { 
    System.out.println(firstCustomer.get()); 
} 
else { 
    System.out.println("no value"); 
} 
 

// Don't Use a Comment When You Can Use a Function or a Variable
// The best comment is no comment

// Bad 
 
//Check to see if order is eligible to ship
if((order.isPaid & order.isLabeled) && CUSTOMER_FLAG) {
  // ...
}
 


// Good 
 
if(order.isEligibleToShip()) {
  // ...
}
 
// Don't leave commented out code in your codebase
Version control exists for a reason. Leave old code in your history.

// Bad 
 
doStuff();
// doOtherStuff();
// doSomeMoreStuff();
// doSoMuchStuff();
 


// Good 
 
doStuff();
 

// Don't have journal comments


// Bad 
 
/// 
 * 2021-03-06: Renamed clean to cleanCode (DL)
 * 2020-01-03: Changed return value (LB)
 * 2019-05-12: Added clean method (DL)
 */
 cleanCode(String code) {
   return null;
 }
 

// Good 
 
 cleanCode(String code) {
   return null;
 }
 
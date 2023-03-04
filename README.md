![alt-text](https://github.com/vatsalasonal/IssProject/blob/main/demo.gif)

Background:
This application was written for JP Morgan interview as a proof of concept to demonstrate the familiarty with android application development.

Goals:
Following were the broad things that was asked to demo:
1. Read about the ISS Location here: http://api.open-notify.org/iss-now.json
2. Obtain the device’s GPS coordinates, if available, and calculate distance to ISS
3. Read about the astronauts currently on board of the ISS here: http://api.open-notify.org/astros.json
4. Display the current distance to ISS and a list of the astronauts.
5. Refresh the location every 5 seconds and display the new information on the screen.
6. Store all read locations and display a list of the known trajectory (list of location/times - EST) of the ISS based on the persisted data. 7. BONUS - display location(s) of ISS on map.

Non-Goals:
UI design wasn't given importance in this project.

Build Instructions:
1. Android Studio IDE setup: Download https://developer.android.com/studio?gclid=Cj0KCQiA0oagBhDHARIsAI-BbgeI_2ZvTWQ2-Y8DgVKo98aaO9yLTYhoTw8-fuC2oybh1JxDAgNgu4saAmcrEALw_wcB&gclsrc=aw.ds the android studio from here along with necessary AVD creation(Pixel XL API23).
2. Just import the project and start build command along with Run.

Test:
Tested the project using JUnit, Mockito and Roboelectric.

Technlogies used:
Retrofit Implementation with Dagger2 was used for dependency injection and data binding.

**REST Client** in our case is the Retrofit library that is used on the client side (Android) to make HTTP request to REST API.
A **REST API** defines a set of functions which developers can perform requests and receive responses via HTTP protocol such as GET and POST.

**RxJava** is used to manage network request and response.

License:
Use however you see fit

Developed with love and passion by Vatsala sonal <vsonal1985@gmail.com>

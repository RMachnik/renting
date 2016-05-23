# Renting-backend app #
This is backend application for rental automation.

App can be build using command `mvn package` or `mvn install`.

### How do I start it? ###
This is simple java app that can be started from `boot/target` using this command `java -jar boot-${version}.jar`.

### Configuration ###

Each module should contain `moduleName.properties` to fulfill basics.
All configuration properties can be override in `application.properties`. This file is inside boot jar.

### Dependencies ###
All app dependencies and libs are inside boot jar.

You need to have installed following apps to be able to build and run Renting-backend.

* java 1.8
* maven

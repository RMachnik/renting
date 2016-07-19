# Renting app #
This is backend application for renting automation.

Application will be based on java se and spring-boot.

App can be build using command `mvn package` or `mvn install`.

### How do I start it? ###
This is simple java app that can be started from `boot/target` using this command `java -jar boot-${version}.jar`.
Application starts on http://localhost:8080/

### Configuration ###

Each module should contain `moduleName.properties` to fulfill basics.

All configuration properties can be override in `application.properties`. This file is inside or outside boot jar.

### Modules ###

* boot - module that is responsible for setting up all boot configuration for running app
* core - module that is responsible for setting up all all configs and components that are needed for boot
* common - module for common things like utils, validators
* domain - module with domain implementation, domain objects should encapsulate all repository calls and do what 
is needed to create such fully functional object. All objects should be immutable! 
Domain objects should't have any spring dependencies. 
Domain objects should be created based on interfaces that will sit in repositories-api, creation of objects should rely on abstraction.
* repository-api - interface of repository, we might want to change underlying repo for different db or sth else 
* repository-db - implementation of repository-api based on spring-data
* repository-static - static implementation of repositories, mainly for testing domain
that's why we need such repository-api, all repositories should return some dto implementations.
* rest - module with all rest endpoints
* mail - module responsible for sending emails

### Dependencies ###
All app dependencies and libs are inside boot jar.

You need to have installed following apps to be able to build and run Renting-backend.

* java 1.8
* maven
* lombok plugin for Intellij - https://projectlombok.org/features/index.html
To fully enable lombok support in your IDE please use http://stackoverflow.com/a/14582541/1370062

### Aruba hosting panel ###
* https://admin.dc3.arubacloud.pl/Login.aspx
* credential FPL-54844/vDRlU435#v

### VPS servers ###

Jenkins:

* server ip: 89.36.217.76
* credential root/qwe_A7asd

App:

* server ip: 93.186.253.7
* credential root/rentingApp1
* app folder: /root/App/running
* deployment script: /root/App/start.sh
* app url: 93.186.253.7:8080

To deploy app jenkins is doing scp on app box and putting jar into `/root/App/new` location. Then it is starting `start.sh` command.

### Jenkins ###
* url: http://89.36.217.76:8080
* credential root/qwe_A7asd

### Email ### 
email: renting.mgr@gmail.com
pass: rentingApp1


ElSubOnline
=============================

Description
-----------------------------
JAVA EE auction web application

Compilation
-----------------------------
This is a maven project so executing `mvn package` should do.

Execution
-----------------------------
The chosen container is JBoss but it "should" work in other containers.
It can be deployed/undeployed on jboss 7.1.1 with maven.
Just execute `mvn install` or `mvn clean` respectively.
Alternatively you can put the generated ear file onto whichever folder you are supposed to in order to deploy the application (i.e. "standalone/deployments").

For more information visit [maven web site](http://maven.apache.org/).

Notes:
Jboss needs must be started with JMS support (i.e. -Djboss.server.default.config=standalone-full.xml)
Also there is a directory called "deployments" with all you need to deploy this application:
* mysql jdbc driver.
* datasource configuration (elsubonline-ds.xml).
* message queues configuration (elsubonline-hornetq-jms.xml).

Author
----------------------------
Jean Pierre Ducassou <jpducassou@gmail.com>

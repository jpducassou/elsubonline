ElSubOnline
=============================

Description
-----------------------------
JAVA EE auction web application.
The source of this application is available on:

* [bitbucket](https://bitbucket.org/jpducassou/elsubonline)
* [github](https://github.com/jpducassou/elsubonline)

See the "license" section below to know in which terms it is released.

Licence
----------------------------
This software is released under GNU GENERAL PUBLIC LICENSE v2. See LICENSE.txt.
Third party software bundled are covered by their respective licenses (i.e. mysql jdbc driver).

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

**Notes:**
Jboss must be started with JMS support (i.e. -Djboss.server.default.config=standalone-full.xml)
Also there is a directory called "deployments" with all you need to deploy this application:

* The MySQL jdbc driver.
* A datasource configuration file (elsubonline-ds.xml).
* A message queue configuration file (elsubonline-hornetq-jms.xml).
* A MySQL dump sample with some data as example. This includes an administrator user whose credentials are:
	* username: elsub.uy@gmail.com
	* password: 1234

	Remember that to be able to load this dump you must create elsubonline database and grant access to a mysql user. (See elsubonline-ds.xml)

Author
----------------------------
Jean Pierre Ducassou <jpducassou@gmail.com>

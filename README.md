# Project name

Perform-back-spring 

# Description

Perform-back-spring is a REST API which was designed with springboot to be part of a set of separate bricks and micro services which communicate with each other as needed. This set of bricks and micro services are a part of the architecture of an application named perform MMA, it manages authentication and security of the Perform MMA application.

Click on the following link for more details on Perform MMA web application =>
<a href="https://docs.google.com/presentation/d/e/2PACX-1vSzRUSTdUaM2xpnRheKOzhWK3UeApCFwF-Qn_Nl0KEetrUcBNHhpZ1nt6GdtnWPDutZzQquVGiIEMkr/pub?start=false&loop=false&delayms=3000">Perform MMA presentation</a> <br>

click on the following link to test the Perform MMA web application =>
<a href="https://perfmma.surge.sh/#/ngr-home">Perform MMA</a> <br>

The other bricks and micro services constituting the Perform MMA application:

* <a href="https://gitlab.com/perform_project/perform-front">perform-front</a>
* <a href="https://gitlab.com/perform_project/perform-robot">perform-robot</a>
* <a href="https://gitlab.com/perform_project/perform-back-nodejs">perform-back-nodejs</a>

# Installation

You need to install :
 
* <a href="https://www.oracle.com/fr/java/technologies/javase/jdk11-archive-downloads.html">Java 11</a>
* <a href="https://maven.apache.org/docs/3.8.7/release-notes.html">Apache Maven 3.8.7</a>
* <a href="https://dev.mysql.com/downloads/mysql/">Mysql 8</a>
	
I haven't tested with other versions but the ones above worked for me

Clone the project and open it with your favorite text editor, I used eclipse.

# Local utilisation

Set the following environment variables : 

* DBURI => your database URI
* DBPORT => your database port
* DBNAME => your database name
* DBUSER => your database username
* DBPASS => your database password

* JWTSECRET => your JWT secret for spring security

* FRONTURI => your front URI for CORS permissions

Run the following commands : 

* mvn clean install
* mvn package -B

# Contact

Email : yannickkamdemkouam@yahoo.fr

# Project status

Developpement in progress

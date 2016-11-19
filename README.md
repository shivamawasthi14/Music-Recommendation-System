# Music-Recommendation-System
Expert System Project

System Requirements:
--------------------
	- Any Operating System
	- Java 8 JDK installed and path variables set for JAVA_HOME
	- Eclipse IDE for Java (Neon Version)
	- MySQL 5.7 
	- Java FX Plugin should be installed in Eclipse
	- MySQL Connector/J (Present in the [Main Project Folder] submitted)

Installation Instruction:
-------------------------
	1) Install all of the above softwares.
	2) Open Eclipse.
	3) Import the [Main Project Folder] into the eclipse environment.
	4) Go to Project Properties -> Java Build Path -> Add Libraries -> Add External Library, browse and select for the MySQL Connector/J provided with the project folder.
	5) Open MySQL and run the script provided in the [Main Project Folder]/SQL/main.sql. It should create a database named music_rs.
	6) In [Main Project Folder] open the database.properties file. This file contains the login information of MySQL. 
		- user:[MYSQL User Name}
		- password: [MYSQL User Password}
		- database: ../music_rs {No need to Change this)
	7) Now go to src/rules and open MainController.java.
	8) Right Click inside the editor and select Run -> Java Application.
	9) Now the GUI should open up.

Using The Expert System:
------------------------
	1) First Click SignUp in the Window Opened.
	2) Enter the fields asked and register. Then enter those values in the login screen on prompt.
	3) There are 6 tabs. 3 tabs for Songs and 3 tabs for MusicArtists.
	4) Initially the Recommendation tabs for both show some popular songs/artist information.
	5) Go to AllSongs tab and start liking some songs by selecting the song and pressing the like button.
	6) Now move to the RecommendedSongs Tab and press the refresh button. This will cause to show recommended songs which are based on your liked songs.
	7) Similary, Go to All Artists Tab and do the similar action to see artist recommendations.
	8) You can view the songs and artists that have been liked in the Liked tabs.
	9) Finally, press the logout button to quit.

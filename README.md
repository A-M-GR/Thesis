# Thesis
BSc  Thesis Andreas Maragkakis

#App
The App file contains all the files of my android studio app project. 
Inside the different classes, you should change the following line in order to connect with your XAMPP's database.
connection = DriverManager.getConnection("url of your database", "DB_Username", "DB_Password");
To create a Username and Password you should create a new user inside your database using the phpMyAdmin tool.

Moreover, you should get a new Google Maps API key in order to use the map screen. 
This URL will be very helpful
https://developers.google.com/maps/documentation/android-sdk/overview

#Website

The website file contains all the files (HTML, CSS, PHP) of my website project. 
This website uses XAMPP services. This file should be inside the "htdocs" file of the XAMPP. 
Moreover inside the "Connection.php" file, you can connect your website with your database. 

#Database
I create a database using the PHPmyAdmin tool of the XAMPP. 
Inside the database, I create two tables: main_table and user_problem_text.Main_table has 2 columns 
Username and password and the user_problem_text has also two columns named problem_text and location.
Moreover inside the "Connection.php" file, you can connect your website with your database. 


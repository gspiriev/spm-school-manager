				     School Plan Manager

What is it?
------------------------------

School plan manager is a small application for managing personal teacher schedules
in Bulgarian musical high schools.


Installation
------------------------------

Extract the archive in a folder of your choosing. Use the bat file in root folder to run under Windows or the .sh to run under Unix/Linux.

Data storage
------------------------------

You can change the type of data storage in the file application.properties. Available options are Hibernate ORM, JDBC or file reading.  

How does it work?
------------------------------

If you want to add or remove students, musical pieces or dates click on Edit Data. Please use only whole numbers when adding dates, student ability or grade.

Dates are the non-school days during the school year.

Student ability and musical piece complexity are whole numbers between 1 and 10, used to determine the time a student may need to learn and perform the piece. Be sure to check if any students' ability is less than all musical piece complexity for his/her grade! That would eventually mean the student can't learn any of the musical pieces. 

To remove an entry click on the row, then click remove.

To create schedule click on Create Schedule, then enter the starting year for the disposition. Text file will be created in the root folder under the name AnnualLessonDisposition. 


# SampleContentProviders
Basic example project to learn work with ContentProviders

SQLite is a lightweight version of a SQL database engine. From the sqlite.org "SQLite is a self-contained, high-reliability, embedded, full-featured, public-domain, SQL database engine". Android Framework is capable of using it.

ContentProvider is an abstraction layer used to access data layer and mostly SQLite. ContentProvider makes it possible to access your data layer from any application running on the same Android device.

The application can use ContentProviders as an abstraction layer to access its own data layer as well.

If you are going to implement an SQLite project which is exposed to be used with ContentProvider, there are 2 crucial classes you have to implement:
1- A child class which is extended from "SQLiteOpenHelper"
2- A child class which is extended from "ContentProvider"

***************

With this project, you can see how to implement an SQLite project and how to use it via ContentProviders. Unit tests can help you understand if it is working as intented.

You can test via the simple GUI:
1- Give a movie id and movie name
2- Save them
3- Clear screen by using the Clear button
4- Enter the same movie id and tap Get Saved button
5- See the Movie Name section is filled with saved information
6- Tap the Delete Saved button after entering a movie id which will be deleted 
7- Repeat 4
8- See the Toast message which indicates no record found.

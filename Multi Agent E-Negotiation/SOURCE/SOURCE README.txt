MULTI AGENT E-NEGOTIATION SOURCE CODE README
Farshad Muhammad 18/08/13
Student ID: 100792339

NOTE: AFTER IMPORTING THE PROJECTS AND TRYING TO RUN THEM ECLIPSE MAY GIVE A PROMPT SAYING THERE ARE ERRORS IN THE BUILD, IGNORE THIS PROMPT AND HIT PROCEED AND THE PROGRAM SHOULD RUN AS INTENDED.

The source code for both the server and client of the system is packaged into eclipse IDE projects. Please download and install eclipse and import the projects into eclipse to run. The E-Negotiation Server has 3 major external dependencies and the SQLite database called the E-Negotiatiodb. These external dependies are the sqlitejdbc jar, the activation jar and the email jar. These jars and the database will be provided both in the source folders and copies will be placed into a dependancy folder in the zip should there be problems so they can be added into the build path of the project.

To run using the source code follow these steps:
1) Open eclipse IDE, make sure JDK version 7.0 or higher is installed
2) Under "File", select import.
3) In the import popup menu, under general select "Existing Projects Into Workspace", hit "Next"
4) In the "Select Root Directory" section hit browse, now locate and select the "E-Negotiation Server" folder and press "Okay".
5) Hit "Finish".
6) The Server and all its dependencies should now be imported into eclipse and the source code should be easily navigated.
7) Repeat this process for the client which is in the folder "E-Negotiation".
8) Run the "Main" java classes in both the programs (Run the Main in Server before Client)

Please Do not move files around or delete files from these folders as they may ruin dependencies.
There maybe certain errors that have not been completely gracefully handled that may show up in the eclipse console while running, ignore these errors unless they cause a crash as they should not hinder the program from its intended functionality.

NOTE: An SQLITE browser maybe necessary to make changes to certain aspects of the program that require the database such as adding new accounts or changing e-mail addresses. This can be done o nthe "Users" table in the E-Negotiationdb file.

RUN AS LOCAL HOST

NOTE: Projects need to be imported into Eclipse before these steps can be performed 
 
2) Run the Main class in the E-Negotiation Server project first and wait for the "SERVER IS RUNNING" window to show, keep this window open as closing it will terminate the server. If this window does not show the port the server is trying to run on is likely blocked (currently the server port is hard coded to be port 80 which should be open on most networks).

3) Run the Main class in the E-Negotiation project which is the client. A message prompt asking for the server IP should appear, enter 'localhost' in this prompt without the quotes, you should now be in the loging window.

4) New accounts at the moment must be hard coded into the database, there are some test accounts ready to be used for running the program,
   these account usernames are (please note that these usernames are case sensitive:
   a) Farshad Muhammad
   b) John Hughes
   c) Andrew Hughes
   d) Lu Bao
   e) Si Wang Mu

The e-mails corresponding with these accounts are set to my e-mail address for now (these are used for offline notificiations). They can be    changed by opening the database and changing values for e-mail in the User table. Any SQLite browser should be able to easily achieve this.

NOTE: The client and server may need to be restarted if events do not follow this flow. They have gone through rigorous testing and seem to work but problems may occur.

Steps for running the programs are provided below:

RUN ON NETWORK

NOTE: Projects need to be imported into Eclipse before these steps can be performed 

2) Run the Main class in the E-Negotiation Server project first and wait for the "SERVER IS RUNNING" window to show, keep this window open as closing it will terminate the server. If this window does not show the port the server is trying to run on is likely blocked (currently the server port is hard coded to be port 80 which should be open on most networks).

3) Run the Main class in the E-Negotiation project on a separate computer, A message prompt asking for the server IP should appear, enter the IP address of the server exactly, invalid IP address will cause the program to terminate and it must be restarted. If the client still does not connect and no loginwindow appears after the prompt, this means that the IP address of the server or port 80 is blocked on the network, see your local network manager for more information.

4) New accounts at the moment must be hard coded into the database, there are some test accounts ready to be used for running the program,
   these account usernames are (please note that these usernames are case sensitive:
   a) Farshad Muhammad
   b) John Hughes
   c) Andrew Hughes
   d) Lu Bao
   e) Si Wang Mu

The e-mails corresponding with these accounts are set to my e-mail address for now (these are used for offline notificiations). They can be changed by opening the database and changing values for e-mail in the User table. Any SQLite browser should be able to easily achieve this.

NOTE: The client and server may need to be restarted if events do not follow this flow. They have gone through rigorous testing and seem to work but problems may occur.
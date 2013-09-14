MULTI AGENT E-NEGOTIATION SYSTEM EXECUTABLE README
Farshad Muhammad 18/08/13

The Multi Agent E-Negotiation System is packaged into 2 runnable JARS. E-Negotiation.jar is the client and E-NegotiationServer.jar is the Server. Please run the server before running the client. Here is a small guide to running both the programs. The client does work safely on a network but the easiest way to test both programs is to run through local host. The database called E-Negotiationdb has to be i nthe same folder as the E-NegotiationServer.jar for the program to function. Please ensure JDK version 7 or higher is installed before running.

RUN AS LOCAL HOST
1) Extract both the jars and the database into a single folder. Make sure E-NegotiationServer.jar is in the same folder as E-Negotiationdb.
 
2) Run the E-NegotiationServer.jar first and wait for the "SERVER IS RUNNING" window to show, keep this window open as closing it will terminate the server. If this window does not show the port the server is trying to run on is likely blocked (currently the server port is hard coded to be port 80 which should be open on most networks).

3) Run E-Negotiation.jar which is the client. A message prompt asking for the server IP should appear, enter 'localhost' in this prompt without the quotes, you should now be in the loging window.

4) New accounts at the moment must be hard coded into the database, there are some test accounts ready to be used for running the program,
   these account usernames are (please note that these usernames are case sensitive:
   a) Farshad Muhammad
   b) John Hughes
   c) Andrew Hughes
   d) Lu Bao
   e) Si Wang Mu

The e-mails corresponding with these accounts are set to my e-mail address for now (these are used for offline notificiations). They can be    changed by opening the database and changing values for e-mail in the User table. Any SQLite browser should be able to easily achieve this.

NOTE: The client and server may need to be restarted if events do not follow this flow. They have gone through rigorous testing and seem to work but problems may occur.

RUN ON NETWORK
1) Extract both the jars and the database into a single folder. Make sure E-NegotiationServer.jar is in the same folder as E-Negotiationdb. 

2) Run the E-NegotiationServer.jar first and wait for the "SERVER IS RUNNING" window to show, keep this window open as closing it will terminate the server. If this window does not show the port the server is trying to run on is likely blocked (currently the server port is hard coded to be port 80 which should be open on most networks).

3) Run E-Negotiation.jar on a separate computer, A message prompt asking for the server IP should appear, enter the IP address of the server exactly, invalid IP address will cause the program to terminate and it must be restarted. If the client still does not connect and no loginwindow appears after the prompt, this means that the IP address of the server or port 80 is blocked on the network, see your local network manager for more information.

4) New accounts at the moment must be hard coded into the database, there are some test accounts ready to be used for running the program,
   these account usernames are (please note that these usernames are case sensitive:
   a) Farshad Muhammad
   b) John Hughes
   c) Andrew Hughes
   d) Lu Bao
   e) Si Wang Mu

The e-mails corresponding with these accounts are set to my e-mail address for now (these are used for offline notificiations). They can be changed by opening the database and changing values for e-mail in the User table. Any SQLite browser should be able to easily achieve this.

NOTE: The client and server may need to be restarted if events do not follow this flow. They have gone through rigorous testing and seem to work but problems may occur.
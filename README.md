# Description

The objective of this coursework is to use Java RMI technology to write a simple client-server application. In this assignment, you are requested to build a system which allows a school pupil to practise multiple choice tests in English. Design and implement a RMI-based Client / Server communication system in Java, which will do the following:

#Requirements 

#      1.	Client:
c1. Connect to server via client_stub. \
c2. Read and display the prompt message sent (see step s2 in server) by the server.\
c3. Receives and displays the first math question plus three possible answers from the server. \
c4. User then selects and inputs only one answer and the client program sends the answer to the
server. This process is repeated three times to complete all 4 questions from the server Questions
database \
c5. After the test is over the client accepts and displays the test results and score from the server.
#2.	Server:
s0. [Initialization] Create a RMI-based server which implements a test interface \
s1.Wait for a client connection\
s2.When a client connection is accepted send an acknowledgement (a welcome message) as a
sting of text.\
s3. From Questions database, server sends the first test questions plus three possible answers to
the client.\
s4. Receives the answer from the client. Stores and evaluates the answer. This process is repeated
three times.\
s5. After that the server sends all results and score to the client.\
s6. Close the connection to the client and then loop back and wait for another connection.

#3.	Questions database:

Q1: Good sleep is necessary _______ good health.\
A. from\
B. to\
C. on\
D. for (correct)

Q2: I bought him _______ with great difficulty.\
A. with\
B. round\
C. up (correct)\
D. in

Q3: Please remove the _______\
A. reed (correct)\
B. read\
C. rid\
D. redo

Q4: The train is _______ out of the platform now.\
A. pull\
B. push\
C. pulling (correct)\
D.pulled  

#Programming Notes
Here are a few tips/thoughts to help you with this assignment:\
•	You must use the questions from Questions database (see above).\
•	Define the remote methods to allow the client to send the answers to the server.\
•	Implement these methods as a remote object.\
•	Write a client to invoke the remote methods.\
•	Use the rmiregistry tool to register the server.

#Defining the Interface 
In this coursework, firstly you define the server methods. The server receives and reads the list of answers selected by the client. Then it checks that this answer is valid.\
•	If not, return an error message to the client.\
•	If yes, check answers and see if the client has answered correctly, then return the score to the client.
#Implementing the Remote Objects on the Server
Write the TestInfoImpl class that implements the TestInfo interface and the TestServer class that
creates an object and registers it on the server.
1.	Write the TestInfoImpl class that contains the actual methods with questions and to receive the answers.
2.	Derive the class from the UnicastRemoteObject class
3.	Create a TestServer class with a method that creates an object. Register this object with the RMI remote object registry, name and bind it to a port using the Naming class and the bind() or rebind() method
#Programming the Client
Produce the TestClient class that locates the server and invokes the remote methods. The command to start the client will be 
$ javaTestClient rmi://machine/remote_object \
Where the URL refers to the registry (usually on the same machine that the server runs on, probably localhost). \
Retrieve the remote object using the Naming class, by passing the use of the registry to the lookup()method. Then make the remote method call on this object.
#Producing the Distributed Objects
Finally, compile and run the distributed objects. 
1.	Compile all the classes you have written. 
2.	Your Java RMI programs are only a part of the application. It uses other piece of code called the stub. These reformat the method parameters and encapsulate them into a RMI data unit. This process is called marshalling. Run the command rmic TestInfoImpl to generate the stub. 
3.	Launch the registry rmiregistry. Once a remote object is registered on the server, callers can look up the object by name, obtain a remote object reference, and then invoke remote methods on the object. 
4.	Run the server and the client on a same machine and directory. Then try to run the client and the server on two different machines.

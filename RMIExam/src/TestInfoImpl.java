

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Arrays;

public class TestInfoImpl extends UnicastRemoteObject implements TestInfo {

    // Set the questions 
    private String[][] questionsDatabase = {
        {"Q1: Good sleep is necessary _______ good health.", "A. from", "B. to", "C. on", "D. for"},
        {"Q2: I bought him _______ with great difficulty.", "A. with", "B. round", "C. up", "D. in"},
        {"Q3: Please remove the _______", "A. reed", "B. read", "C. rid", "D. redo"},
        {"Q4: The train is _______ out of the platform now.", "A. pull", "B. push", "C. pulling", "D.pulled"}
    };

    // These stores the indices where to find the correct answers in the questions database
    private int[] questionCorrectAnswers = {4, 3, 1, 3};

    private ServerApplication serverApplication;

    /**
     * Initialize the server
     */

    public TestInfoImpl(ServerApplication serverApplication) throws RemoteException, ServerNotActiveException {
        super();

        this.serverApplication = serverApplication;
        serverApplication.logMessage("");

    }

    /**
     * Get the question text and choices given the question number
     */

    public String[] getQuestion(int questionNum) throws RemoteException {
        if (questionNum < 0 || questionNum >= questionsDatabase.length) {
            return null;
        }
        
        serverApplication.logMessage(Arrays.toString(questionsDatabase[questionNum]) + " question has been retrieved...");
        return questionsDatabase[questionNum];
    }

    /**
     * Submit the answer to the server and the server responds if they have the
     * question answered correctly or not
     */
    public boolean submitAnswer(int questionNum, int choice) throws RemoteException {
        if (questionNum < 0 || questionNum >= questionsDatabase.length) {
            return false;
        }
        
        serverApplication.logMessage("User answer: " + questionsDatabase[questionNum][choice] + " has been submitted...");
        return questionCorrectAnswers[questionNum] == choice;
    }

    @Override
    public String identifyClient(String host, String time) throws RemoteException, ServerNotActiveException {

        serverApplication.logMessage("Client: " + host + " has connected to the server at "
                + time);
        return null;
    }

    public String displayRules() throws RemoteException, ServerNotActiveException {

        return null;
    }
}

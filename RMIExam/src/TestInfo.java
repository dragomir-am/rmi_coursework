

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface TestInfo extends Remote {
    
    /**
     * Get the question text and choices given the question number
     */
    public String[] getQuestion(int questionNum) throws RemoteException;
    
    /**
     * Submit the answer to the server and the server responds if they have
     * the question answered correctly or not
     */
    public boolean submitAnswer(int questionNum, int choice) throws RemoteException;

    public String identifyClient(String host, String time ) throws RemoteException, ServerNotActiveException;

    public String displayRules() throws RemoteException, ServerNotActiveException;
}

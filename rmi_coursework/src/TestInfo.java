import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

public interface TestInfo extends Remote {

    /**
     * Get the question text and choices given the question number
     */
    String[] get_question(int question_num) throws RemoteException;

    /**
     * Submit the answer to the server and the server responds if they have
     * the question answered correctly or not
     */
    boolean submit_user_answer(int question_num, int choice) throws RemoteException;

    String identify_client(String host) throws RemoteException, ServerNotActiveException;

    String hello_client(String client) throws RemoteException;

}

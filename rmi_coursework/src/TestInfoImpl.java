import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.Arrays;

public class TestInfoImpl extends UnicastRemoteObject implements TestInfo {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    String time = "[" + timestamp + "]: ";

    // Set the questions 
    private final String[][] questions_database = {
            {"Q1: Good sleep is necessary _______ good health.", "A. from", "B. to", "C. on", "D. for"},
            {"Q2: I bought him _______ with great difficulty.", "A. with", "B. round", "C. up", "D. in"},
            {"Q3: Please remove the _______", "A. reed", "B. read", "C. rid", "D. redo"},
            {"Q4: The train is _______ out of the platform now.", "A. pull", "B. push", "C. pulling", "D. pulled"}
    };

    // These stores the indices where to find the correct answers in the questions database
    private final int[] question_correct_answers = {4, 3, 1, 3};

    private final ServerApplication server_application;

    /**
     * Initialize the server
     */

    public TestInfoImpl(ServerApplication server_application) throws RemoteException, ServerNotActiveException {
        super();

        this.server_application = server_application;
        server_application.log_message("");

    }

    /**
     * Get the question text and choices given the question number
     */

    public String[] get_question(int question_num) throws RemoteException {
        if (question_num < 0 || question_num >= questions_database.length) {
            return null;
        }

        server_application.log_message(time + Arrays.toString(questions_database[question_num]) + " question has been retrieved...");
        return questions_database[question_num];
    }

    /**
     * Submit the answer to the server and the server responds if they have the
     * question answered correctly or not
     */
    public boolean submit_user_answer(int question_num, int choice) throws RemoteException {
        if (question_num < 0 || question_num >= questions_database.length) {
            return false;
        }

        server_application.log_message(time + "User answer: " + questions_database[question_num][choice] + " has been submitted...");

        return question_correct_answers[question_num] == choice;
    }

    @Override
    public String identify_client(String host) throws RemoteException, ServerNotActiveException {

        server_application.log_message(time + "Client: " + host + " has connected to the server");
        return null;
    }

    @Override
    public String hello_client(String client) throws RemoteException {
        return server_application.hello_client(client);

    }

}

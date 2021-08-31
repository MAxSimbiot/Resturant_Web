package exception;

public class FailedDAOException extends Exception{
    public FailedDAOException(){}

    public FailedDAOException(String message){super(message);
    }
}

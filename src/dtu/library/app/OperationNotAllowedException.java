package dtu.library.app;

//Stolen from the library assignment
public class OperationNotAllowedException extends Exception{
	
	public OperationNotAllowedException(String operation) {
		super(operation);
	}
}
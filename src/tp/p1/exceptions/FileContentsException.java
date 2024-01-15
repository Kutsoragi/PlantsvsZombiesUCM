package tp.p1.exceptions;

public class FileContentsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileContentsException() {
		super("ERROR: Game file damaged.");
	}
	
	public FileContentsException(String message){
		super(message);
	}
		
	public FileContentsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public FileContentsException(Throwable cause){
		super(cause);
	}
}
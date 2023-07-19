package exceptions;

public class WrongLayoutException extends Exception{	
	private static final long serialVersionUID = 1L;

	public WrongLayoutException () {
		super();
	}

	public WrongLayoutException (String in) {
		super(in);
	}
}
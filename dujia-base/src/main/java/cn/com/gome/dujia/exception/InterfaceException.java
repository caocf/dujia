package cn.com.gome.dujia.exception;

public class InterfaceException extends Exception {

    private static final long serialVersionUID = 3354369923860501884L;

    public InterfaceException() {
		super();
	}

	public InterfaceException(String message) {
		super(message);
	}

	public InterfaceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InterfaceException(Throwable cause) {
		super(cause);
	}
}

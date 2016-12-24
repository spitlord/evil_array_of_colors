// to be thrown when a pixel is set to value below 0 or over 255

public class ColorException extends Exception {

	public ColorException() {}
	public ColorException(String message) {
		super(message);
	}

}

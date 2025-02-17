
public class NominativoException extends Exception {
	
	public NominativoException() {
		super("Problema sul nominativo");
	}
	
	@Override
	public String toString() {
		return getMessage() +" hai inserito dei numeri!";
	}
}

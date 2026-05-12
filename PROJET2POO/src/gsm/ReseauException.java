package gsm;

// Notre exception personnalisee pour les erreurs du reseau GSM
public class ReseauException extends Exception {

    public ReseauException(String message) {
        super(message);
    }
}

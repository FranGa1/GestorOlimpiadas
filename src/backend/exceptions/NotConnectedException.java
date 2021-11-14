package backend.exceptions;

public class NotConnectedException extends Exception {
    @Override
    public String getMessage(){
        return "No se encuentra conectado a la base de datos";
    }
}

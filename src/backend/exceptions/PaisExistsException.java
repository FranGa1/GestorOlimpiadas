package backend.exceptions;

public class PaisExistsException extends Exception {
    @Override
    public String getMessage() {
        return "El Pais ya se encuentra en la base de datos";
    }
}

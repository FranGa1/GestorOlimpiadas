package backend.exceptions;

public class PaisUsedException extends Exception {
    @Override
    public String getMessage() {
        return "El Pais se encuentra asignado a uno o mas deportistas";
    }
}

package backend;

public class ManejoExcepciones extends Exception{
    ManejoExcepciones(int prueba){
        if (prueba > 1)
            System.out.println("prueba es mayor a 1");
    }

    @Override
    public String getMessage() {
        return "asdf";
    }
}

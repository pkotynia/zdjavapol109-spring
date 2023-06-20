package pl.sda.demo.exception;

//we are creating this exception to hava a specific type for which we can provide handling and return 404
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message){
        super(message);
    }
}

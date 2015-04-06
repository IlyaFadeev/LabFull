package vector.exceptions;

/**
 * Created by Ilya on 12.03.2015.
 */
public class IncompatibleVectorSizesException extends RuntimeException{
    public IncompatibleVectorSizesException()
    {
        super();
    }

    public IncompatibleVectorSizesException(String message)
    {
        super(message);
    }

}

import java.lang.Exception;

public class InvalidInputSyntax extends Exception{
    public InvalidInputSyntax(String s) {
        // Call constructor of parent Exception
        super(s);
    }
}

import java.util.*;
import java.io.*;

public class MyObjectOutputStream extends ObjectOutputStream {

    MyObjectOutputStream() throws IOException {
        super();
    }

    MyObjectOutputStream(OutputStream o) throws IOException {
        super(o);
    }

    public void writeStreamHeader() throws IOException
    {
        return;
    }
}
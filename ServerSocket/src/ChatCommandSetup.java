
import java.io.*;

public class ChatCommandSetup implements Serializable {

    // The different types of message sent by the Client to the Server

    static final int YAQUI = 0, MESSAGE = 1, HELP = 2, QUIT = 3, DEPRESSION = 4;
    private int type;
    private String message;

    // constructor
    ChatCommandSetup(int type, String message) {
        this.type = type;
        this.message = message;
    }

    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
}

///  Thx to StackOverflow for this code, is more friendly like this ///

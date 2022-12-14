
import java.net.*;
import java.io.*;
import java.util.*;


//The Client that can be run as a console
public class EchoClient  {

    private String notif = "[SERVER] : ";

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private String server;
    private String username;
    private int port;

    EchoClient(String server, int port, String username) {
        this.server = server;
        this.port = port;
        this.username = username;
    }

    /*
     * To start the chat
     */
    public boolean start() {
        // try to connect to the server
        try {
            socket = new Socket(server, port);
        }
        // exception handler if it failed
        catch(Exception ec) {
            display("Error connectiong to server:" + ec);
            return false;
        }

        String msg = "Connection accepted depuis " + socket.getInetAddress() + ":" + socket.getPort() + " par " + socket.getLocalAddress() + ":" + socket.getLocalPort() + " avec le pseudo : " + username;
        display(msg);

        try
        {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException eIO) {
            display("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        new ListenFromServer().start();

        try
        {
            output.writeObject(username);
        }
        catch (IOException eIO) {
            display("Exception doing login : " + eIO);
            return false;
        }
        // success we inform the caller that it worked
        return true;
    }

    /*
     * To send a message to the console
     */
    private void display(String msg) {

        System.out.println(msg);

    }

    /*
     * To send a message to the server
     */
    void sendMessage(ChatCommandSetup msg) {
        try {
            output.writeObject(msg);
        }
        catch(IOException e) {
            display("Exception writing to server: " + e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    private void disconnect() {
        try {
            if(input != null) input.close();
        }
        catch(Exception e) {}
        try {
            if(output != null) output.close();
        }
        catch(Exception e) {}
        try{
            if(socket != null) socket.close();
        }
        catch(Exception e) {}

    }

    public static void main(String[] args) {
        // default values if not entered
        int portNumber = 2002;
        String serverAddress = "127.0.0.1";
        String userName = "Unknow";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the username: ");
        userName = scanner.nextLine();


        if (args.length == 0) {
            // Nothing
        } else if (args.length == 1) {
            // javac EchoClient username
            userName = args[0];
        } else if (args.length == 2) {
            // javac Client username port
            try {
                portNumber = Integer.parseInt(args[1]);
            } catch (Exception e) {
                System.out.println("Invalid port number.");
                System.out.println("Usage is: java Client [username] [portNumber]");
                return;
            }
        } else if (args.length == 3) {
            // javac Client username portNumber serverAddr
            serverAddress = args[2];
        } else {
            // if number of arguments are invalid
            System.out.println("Usage is: java Client [username] [portNumber] [serverAddress]");
            return;
        }

        EchoClient client = new EchoClient(serverAddress, portNumber, userName);
        if(!client.start()) return;

        System.out.println("\nSalut ! Welcome to the chatroom.");
        System.out.println("Instructions:");
        System.out.println("Pour envoyer un message au serveur, tapez le message et appuyez sur la touche Entrée.");

        new ChatCommand().help();

        while(true) {
            System.out.print("> ");
            String msg = scanner.nextLine();
            // Mode QUIT
            if(msg.equalsIgnoreCase("QUIT")) {
                client.sendMessage(new ChatCommandSetup(ChatCommandSetup.QUIT, ""));
                break;
            }
            // Mode Y A QUI x)
            else if(msg.equalsIgnoreCase("YAQUI")) {
                client.sendMessage(new ChatCommandSetup(ChatCommandSetup.YAQUI, ""));
            }
            // Mode HELP x)
            else if(msg.equalsIgnoreCase("HELP")) {
                client.sendMessage(new ChatCommandSetup(ChatCommandSetup.HELP, ""));
            }
            // Mode depression :(
            else if(msg.equalsIgnoreCase("DEPRESSION")) {
                client.sendMessage(new ChatCommandSetup(ChatCommandSetup.DEPRESSION, ""));
            }
            // Mode Normal
            else {
                client.sendMessage(new ChatCommandSetup(ChatCommandSetup.MESSAGE, msg));
            }
        }
        scanner.close();
        client.disconnect();
    }

    class ListenFromServer extends Thread {

        public void run() {
            while(true) {
                try {
                    // read the message form the input datastream
                    String msg = (String) input.readObject();
                    // print the message
                    System.out.println(msg);
                    System.out.print("> ");
                }
                catch(IOException e) {
                    display(notif + "Server has closed the connection: " + e + notif);
                    break;
                }
                catch(ClassNotFoundException e2) {
                }
            }
        }
    }
}


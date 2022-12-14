import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EchoServer {
    // Unique ID for each connexion
    private static int uniqueId;
    // List of clients
    protected ArrayList<newClient> clientList;
    // to display time like a chat pro ahahaha
    protected SimpleDateFormat displayTime;
    // port number
    private int port;
    // to check if server still running
    private boolean stillHere;
    protected String notif = "[SERVER] : ";

    public EchoServer(int port) {
        this.port = port;
        displayTime = new SimpleDateFormat("HH:mm:ss");
        clientList = new ArrayList<newClient>();
    }

    public EchoServer() {
    }

    public void execute() {

        stillHere = true;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (stillHere) {
                display("En attente de connexion d'un clients sur le port " + port + ".");

                Socket socket = serverSocket.accept();

                if (!stillHere) break;

                newClient user = new newClient(socket, uniqueId++);
                clientList.add(user);
                user.start();
            }
            try {
                serverSocket.close();
                for(int i = 0; i < clientList.size(); ++i) {
                    newClient user = clientList.get(i);
                    try {
                        // close all data streams and socket
                        user.input.close();
                        user.output.close();
                        user.socket.close();
                    }
                    catch(IOException ioE) {
                    }
                }
            } catch (Exception e) {
                display("Erreur, lors de la fermeture du serveur: " + e);
            }
        } catch (IOException ex) {
            display(displayTime.format(new Date()) + "Erreur, lors de l'ajout d'un new ServerSocket " + ex.getMessage());
        }
    }

    // Fermeture du serveur
    protected void stop() {
        stillHere = false;
        try {
            new Socket("localhost", port);
        } catch (Exception e) {
            // nothing to do
        }
    }

    // Affichage d'un log / message dans la console avec le formatage de l'heure
    public void display(String msg) {
        System.out.println(displayTime.format(new Date()) + " " + msg);
    }

    // Pour afficher un message a tous les utilisateurs
    protected synchronized boolean broadcast(String message) {
        // add timestamp to the message
        String time = displayTime.format(new Date());

        String messageRewrite = time + " " + message + "\n";
        // display message
        System.out.print(messageRewrite);

        for (int i = clientList.size(); --i >= 0; ) {
            newClient user = clientList.get(i);
            // try to write to the Client if it fails remove it from the list
            if (!user.writeMsg(messageRewrite)) {
                clientList.remove(i);
                display("Une personne vient de partir " + user.username + ".");
            }
        }
        return true;
    }

    // if client sent LOGOUT message to exit
    synchronized void remove(int id) {

        String disconnectedClient = "";
        // scan the array list until we found the Id
        for (int i = 0; i < clientList.size(); ++i) {
            newClient user = clientList.get(i);
            // if found remove it
            if (user.id == id) {
                disconnectedClient = user.getUsername();
                clientList.remove(i);
                break;
            }
        }
        broadcast(notif + disconnectedClient + " has left the chat room." + notif);
    }

    public static void main(String[] args) {
        int port = 2002;
        if(args.length == 1){
            port = Integer.parseInt(args[0]);
        }

        EchoServer server = new EchoServer(port);
        server.execute();
    }

    public class newClient extends Thread {

        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        int id;
        String username;
        String date;
        ChatCommandSetup cm;

        newClient(Socket socket, int id) {

            try {
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());
                // read the username
                username = (String) input.readObject();
                broadcast(notif + username + " has joined the chat room.");
            } catch (IOException e) {
                display("Exception creating new Input/output Streams: " + e);
                return;
            } catch (ClassNotFoundException e) {
            }
            date = new Date().toString() + "\n";

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // infinite loop to read and forward message
        public void run() {
            // to loop until LOGOUT
            boolean keepGoing = true;
            while (keepGoing) {
                // read a String (which is an object)
                try {
                    cm = (ChatCommandSetup) input.readObject();
                } catch (IOException e) {
                    display(notif + username + " Exception reading Streams: " + e);
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                // get the message from the ChatMessage object received
                String message = cm.getMessage();

                // different actions based on type message
                switch (cm.getType()) {
                    case ChatCommandSetup.QUIT:
                        display(username + " déconnecter grace au message QUITd.");
                        keepGoing = false;
                        break;
                    case ChatCommandSetup.YAQUI:
                        writeMsg("Liste des utilisateurs connectés à " + displayTime.format(new Date()) + "");
                        // send list of active clients
                        for (int i = 0; i < clientList.size(); ++i) {
                            newClient user = clientList.get(i);
                            writeMsg((i + 1) + ") " + user.username + " depuis " + user.date);
                        }
                        break;
                    case ChatCommandSetup.HELP:
                        writeMsg("Liste des commandes disponibles :");
                        new ChatCommand().help();
                        break;
                    case ChatCommandSetup.DEPRESSION:
                        new ChatCommand().depression();
                        break;
                    case ChatCommandSetup.MESSAGE:
                        broadcast(username + ": " + message);
                        break;
                }
            }
            // if out of the loop then disconnected and remove from client list
            remove(id);
            close();
        }

        // close everything
        private void close() {
            try {
                if (output != null) output.close();
            } catch (Exception e) {
            }
            try {
                if (input != null) input.close();
            } catch (Exception e) {
            }
            ;
            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
            }
        }


        // write a String to the Client output stream
        protected boolean writeMsg(String msg) {

            // write the message to the stream
            try {
                output.writeObject(msg);
            }
            // if an error occurs, do not abort just inform the user
            catch(IOException e) {
                display(notif + "Erreur lors de l'envoie du message " + username);
                display(e.toString());
            }
            return true;
        }
    }

}

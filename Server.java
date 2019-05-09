package udp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static ServerSocket aSocket;
    private static Socket dataSocket;

    public Server (int port) {
        try {
            aSocket = new ServerSocket(port);
        }
        catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE , null , ex);
        }
    }

    private static void receiveMessage(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        OperationMessage stationMessage = (OperationMessage) objectInputStream.readObject();

        // run script with received messages:
        System.out.println("Received device: " + stationMessage.getStationID() + ":[" + stationMessage.getDescription() + "]");
        System.out.println("Product at station: " + stationMessage.getProductID());
    }

    private static void loop() throws IOException, ClassNotFoundException {
        aSocket = new ServerSocket(9876);

        while (true) {
            System.out.println("Server waiting for connections...");
            dataSocket = aSocket.accept();
            InputStream inputStream = dataSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            receiveMessage(objectInputStream);
        }
    }

    public static void main(String[] args) {
        try {
            loop();
        }
        catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE , null , ex);
        }
    }
}

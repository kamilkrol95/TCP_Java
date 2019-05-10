package udp;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client  {

    private String host;
    private Integer port;
    private Station station = new Station();
    private Product product = new Product();

    private Integer getPort() {
        return port;
    }

    private String getHost() {
        return host;
    }

    private Client(String[] args) {
        setConnection(args);
    }

    private void setConnection(String[] args){
        this.host = args[0];
        this.port = Integer.parseInt(args[1]);
    }

    // default values - DEBUGGING
    private void setStationInfo() {
        this.station = new Station(2, "Drilling");
    }

    private void setProductInfo() {
        this.product = new Product(5, "iPhone 11");
    }

    private OperationMessage setMessage() {
        return new OperationMessage(this.station, this.product);
    }

    private void scanBarcode(String[] args){
        Integer scannedStationID = Integer.parseInt(args[2]);
        Integer scannedOrderID = Integer.parseInt(args[3]);
        this.product = new Product(scannedOrderID);
        this.station = new Station(scannedStationID);
    }

    private static void receiveMessage(Client currentClient, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException{
        OperationMessage incomingMessage = (OperationMessage) objectInputStream.readObject();
        currentClient.station = incomingMessage.getStation();
        currentClient.product = incomingMessage.getProduct();

        System.out.println("\nReceived data:");
        System.out.println(currentClient.product.toString());
        System.out.println(currentClient.station.toString());
    }

    private static void productArrive(Client client, String[] connectionArguments) throws IOException, ClassNotFoundException {

        if (connectionArguments.length < 4){
            client.setStationInfo();
            client.setProductInfo();
        }
        else {
            client.scanBarcode(connectionArguments);
        }

        Socket dataSocket = new Socket(client.getHost(), client.getPort());
        System.out.println("Connected!");

        OutputStream outputStream = dataSocket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending message to server!");
        objectOutputStream.writeObject(client.setMessage());

        System.out.println("Waiting for server response...");
        InputStream inputStream = dataSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        receiveMessage(client, objectInputStream);

        System.out.println("\nClosing socket!");
        dataSocket.close();
    }

    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println("udp Client usage: <host name> <port>");
            System.out.println("Exiting with code 1");
            System.exit(1);
        }
        else {
            try {
                Client client = new Client(args);
                productArrive(client, args);
            }
            catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE , null , ex);
            }
        }
    }
}

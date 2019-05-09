package udp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

    private void setStationInfo() {
        this.station = new Station(2, "Drilling");
    }

    private void setProductInfo() {
        this.product = new Product(5, "iPhone 11");
    }

    private OperationMessage setMessage() {
        OperationMessage operationMessage = new OperationMessage(this.station.getStationID(), this.station.getDescription());
        operationMessage.setProductID(this.product.getProductID());

        return operationMessage;
    }

    private static void productArrive(String[] connectionArguments) throws IOException {
        Client client = new Client(connectionArguments);

        client.setStationInfo();
        client.setProductInfo();

        Socket socket = new Socket(client.getHost(), client.getPort());
        System.out.println("Connected!");

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending message to server!");
        objectOutputStream.writeObject(client.setMessage());
        System.out.println("Closing socket!");
        socket.close();
    }

    public static void main(String[] args) {

        if (args.length < 2){
            System.out.printf("udp Client usage: <host name> <port>");
            System.out.println("Exiting with code 1");
            System.exit(1);
        }
        else {
            try {
            productArrive(args);
            }
            catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE , null , ex);
            }
        }
    }
}

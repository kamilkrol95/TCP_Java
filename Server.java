package udp;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static ServerSocket aSocket;
    private static Socket dataSocket;
    private OperationMessage incomingStationMessage;

    private static Connection connectToDB() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/production_line?serverTimezone=UTC","root","krolkamil");
    }

    private static void selectDBInfo(Server serverState) throws SQLException{
        Connection connection = connectToDB();

        String sql = "select * from Products " +
                "inner join ProductOrders on Products.ProductID = ProductOrders.ProductID " +
                "where ProductOrders.OrderID = " + serverState.incomingStationMessage.getProduct().getOrderID();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while(rs.next()) {
            serverState.incomingStationMessage.getProduct().setProductID(rs.getInt("ProductID"));
            serverState.incomingStationMessage.getProduct().setDescription(rs.getString("Description"));
            serverState.incomingStationMessage.getProduct().setCurrentOperation(rs.getInt("CurrentOperation"));
            serverState.incomingStationMessage.getProduct().setState(ProductState.getProductState(rs.getInt("State")));

            ArrayList<Integer> operations = new ArrayList<>();
            for ( Integer i = 1; i <= 6; i++ ){
                Integer operationID = rs.getInt("Operation" + i.toString());
                if ( operationID > 0 ){
                    operations.add(operationID);
                }
            }
            serverState.incomingStationMessage.getProduct().setOperation(operations.toArray(new Integer[0]));
        }

        sql = "select * from Stations where StationID = " + serverState.incomingStationMessage.getStation().getStationID();
        statement = connection.createStatement();
        rs = statement.executeQuery(sql);

        while(rs.next()) {
            serverState.incomingStationMessage.getStation().setDescription(rs.getString("Description"));
        }

        connection.close();
    }

    private static void updateDBInfo(Server serverState) throws SQLException {
        Connection connection = connectToDB();

        Integer currentOperation = serverState.incomingStationMessage.getProduct().getCurrentOperation();

        if (serverState.incomingStationMessage.getProduct().getOperation()[currentOperation - 1].equals(serverState.incomingStationMessage.getStation().getStationID())) {

            int nextOperation = currentOperation + 1;
            if (nextOperation > serverState.incomingStationMessage.getProduct().getOperation().length){
                serverState.incomingStationMessage.getProduct().setState(ProductState.DONE);
                nextOperation = 1;
            }

            serverState.incomingStationMessage.getProduct().setCurrentOperation(nextOperation);

            if (serverState.incomingStationMessage.getProduct().getCurrentOperation() > 1) {
                serverState.incomingStationMessage.getProduct().setState(ProductState.IN_PROGRESS);
            }

            PreparedStatement preparedStatement  = connection.prepareStatement("UPDATE ProductOrders SET CurrentOperation = ?, State = ? WHERE OrderID = ?");
            preparedStatement.setInt(1, serverState.incomingStationMessage.getProduct().getCurrentOperation());
            preparedStatement.setInt(2, serverState.incomingStationMessage.getProduct().getState().ordinal());
            preparedStatement.setInt(3, serverState.incomingStationMessage.getProduct().getOrderID());
            preparedStatement.execute();
            serverState.incomingStationMessage.getStation().setState(StationState.WORK);
        }
        else {
            serverState.incomingStationMessage.getStation().setState(StationState.PASS);
        }

        connection.close();
    }

    private static Server receiveMessage(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException, SQLException {
        Server serverState = new Server();
        serverState.incomingStationMessage = (OperationMessage) objectInputStream.readObject();

        System.out.println("Received data:");
        System.out.println(serverState.incomingStationMessage.getProduct().orderIDToString());
        System.out.println(serverState.incomingStationMessage.getStation().stationIDToString());

        selectDBInfo(serverState);
        updateDBInfo(serverState);

        System.out.println("\nCurrent data:");
        System.out.println(serverState.incomingStationMessage.getProduct().toString());
        System.out.println(serverState.incomingStationMessage.getStation().toString());

        return serverState;
    }

    private static OperationMessage sendMessage(Server serverState) {
        return serverState.incomingStationMessage;
    }

    private static void loop() throws IOException, ClassNotFoundException, SQLException {
        aSocket = new ServerSocket(9876);

        while (true) {
            System.out.println("Server waiting for connections...\n");
            dataSocket = aSocket.accept();
            InputStream inputStream = dataSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Server server = receiveMessage(objectInputStream);

            OutputStream outputStream = dataSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("\nSending message to client!\n");
            objectOutputStream.writeObject(sendMessage(server));
        }
    }

    public static void main(String[] args) {
        try {
            loop();
        }
        catch (SQLException | ClassNotFoundException | IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE , null , ex);
        }
    }
}

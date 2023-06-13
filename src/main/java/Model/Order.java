package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


/**
 * Face o comanda in functie de client
 */
public class Order implements Serializable {

    private final int id;
    private final int ClientId;
    private final Date orderDate;
    private static int i=0;



    public Order(int clientId, Date orderDate) {
        this.id = i;
        i++;
        ClientId = clientId;
        this.orderDate = orderDate;
    }



    public int getId() {
        return id;
    }

    public int getClientId() {
        return ClientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order: " + id +
                " Client: " + ClientId +
                " Date: " + orderDate;
    }
}

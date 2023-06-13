package Model;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clasa principala care stocheaza datele
 */

public class DeliveryService extends Observable implements Serializable {
        private Set<MenuItem> products;
        private Set<User> persons;
        private HashMap<Order, List<MenuItem>> orders;
        private User currentUser;
        private List<Observer> employees = new ArrayList<>();
        private List<Order> report;

    public DeliveryService() {
        this.products = new HashSet<>();
        this.persons = new HashSet<>();
        this.orders = new HashMap<>();
    }

    public void hardcodare() {
        persons.add( new User("adminul", "pass123", Rank.ADMIN));
        persons.add( new User("clientul1", "pass123", Rank.CLIENT));
        persons.add( new User("clientul2", "pass123", Rank.CLIENT));
        persons.add( new User("angajatul", "pass123", Rank.EMPLOYEE));

    }
    //din lista de comenzi am luat key set ul care imi sunt order-urile
    //si le am filtrat in fct de ora
    public List<Order> Report(int fisrtHour, int lastHour){
        report = orders.keySet().stream().filter(x->x.getOrderDate().getHours()>fisrtHour&& x.getOrderDate().getHours()<lastHour).collect(Collectors.toList());
        return report;
    }


    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    public void addUser(User user){this.persons.add(user);}
    public void removeUser(User user){this.persons.remove(user);}

    public User findUser(String name){
        for(User p:this.persons){
            if(p.getUsername().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void addProduct(MenuItem produs){
        products.add(produs);
    }
    public void removeProdus(MenuItem produs){
        products.remove(produs);
    }

    public MenuItem findProduct(String name){
        for(MenuItem item:this.products){
            if(item.getTitle().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void createOrder(Order order, List<MenuItem> list){
        orders.put(order,list);
    }

    public Set<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(Set<MenuItem> products) {
        this.products = products;
    }

    public Set<User> getPersons() {
        return persons;
    }

    public void setPersons(Set<User> persons) {
        this.persons = persons;
    }

    public HashMap<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Order, List<MenuItem>> orders) {
        this.orders = orders;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Observer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Observer> employees) {
        this.employees = employees;
    }
}

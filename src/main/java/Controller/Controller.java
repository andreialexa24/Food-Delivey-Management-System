package Controller;

import Model.*;
import View.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {
        private DeliveryService deliveryService;
        private Login login;
        private Register register;
        private Admin admin;
        private Client client;
        private Employee angajat;
        private FileWriter fileWriter = new FileWriter();
        private CreateProduct createProduct;
        private CreateMenu createMenu;
        private EditMenu editMenu;
        private EditProduct editProduct;


    /**
     * Porneste aplicatia, deserializeaza obiectul, deliveryService si in cazul terminarii aplicatiei se serializeaza obiectul
     */
    public void start(){
            login = new Login(this);
            login.setVisible(true);
//            deliveryService= new DeliveryService();
//            deliveryService.hardcodare();
            deliveryService= (DeliveryService) Serializator.Deserialization("deliveryService");
            angajat=new Employee();
            deliveryService.addObserver(angajat);
            deliveryService.getEmployees().add(angajat);
            login.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Serializator.Serialization(deliveryService,"deliveryService");
                    //super.windowClosing(e);
                }
            });
        }


    /**
     * Creeaza un tabel dintr-un set de produse
     * @param produse
     * @return
     */
    public DefaultTableModel tabelProduse(Set<MenuItem> produse){
            DefaultTableModel model = new DefaultTableModel();
            Field[] fields = MenuItem.class.getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                model.addColumn(f.getName());
            }
            produse.forEach(menuItem -> {
                List<Object> row = new ArrayList<>();
                for (Field f : fields) {
                    try {
                        row.add(f.get(menuItem));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                model.addRow(row.toArray());
            });
            return model;
        }

    /**
     * Functie lambda care filtreaza functiile din Set in fct de valorile unui produs dat astfel incat sa fie mai mare decat ele
     * @param p
     * @return
     */
    public Set<MenuItem> filtrare(BaseProduct p)
    {
        Set<MenuItem> result;
        result= (Set<MenuItem>) deliveryService.getProducts().stream().filter((menuItem) ->
            menuItem.getTitle().contains(p.getTitle()) &&
                    menuItem.getRating()>=p.getRating() &&
                    menuItem.getCalories()>=p.getCalories() &&
                    menuItem.getProteins()>=p.getProteins() &&
                    menuItem.getFats()>=p.getFats() &&
                    menuItem.getSodium()>=p.getSodium() &&
                    menuItem.getPrice()>=p.getPrice())
        .collect(Collectors.toSet());
        return result;
    }
    public CreateProduct getCreateProduct() {
        return createProduct;
    }

    public void setCreateProduct(CreateProduct createProduct) {
        this.createProduct = createProduct;
    }

    public CreateMenu getCreateMenu() {
        return createMenu;
    }

    public void setCreateMenu(CreateMenu createMenu) {
        this.createMenu = createMenu;
    }

    public EditMenu getEditMenu() {
        return editMenu;
    }

    public void setEditMenu(EditMenu editMenu) {
        this.editMenu = editMenu;
    }

    public EditProduct getEditProduct() {
        return editProduct;
    }

    public void setEditProduct(EditProduct editProduct) {
        this.editProduct = editProduct;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getAngajat() {
        return angajat;
    }

    public void setAngajat(Employee angajat) {
        this.angajat = angajat;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public Login getLogin() {
        return login;
    }

    public Register getRegister() {
        return register;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}

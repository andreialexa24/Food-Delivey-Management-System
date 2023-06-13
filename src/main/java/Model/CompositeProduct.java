package Model;

//composite contine mai multe produse de timpul menu item

import java.awt.*;
import java.util.HashSet;
import java.util.Set;



public class CompositeProduct extends  MenuItem{
    private Set<MenuItem> menu;



    public CompositeProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
        menu = new HashSet<>(); //initializez hashset-ul(meniul)
    }


    /**
     * calculeaza pretul final al tuturor produselor
     * @return
     */
    @Override
    public int computePrice() {
        int total = menu.stream().mapToInt(MenuItem::getPrice).sum();  //creez din set un stream, mapez fiecare int din acel set
                                                                        //folosind metoda getPrice , si sum le aduna automat pe toate
        super.setPrice(total);
        return total;
    }


    /**
     * Adauga un produs in meniu
     * @param produs
     */
    public void addProduct(MenuItem produs){
        menu.add(produs);
    }

    /**
     * Sterge un produs din meniu
     * @param produs
     */
    public void removeProdus(MenuItem produs){
        menu.remove(produs);
    }

    /**
     * Gaseste un produs din meniu
     * @param name
     * @return
     */
    public MenuItem findProduct(String name){
        for(MenuItem item:this.menu){
            if(item.getTitle().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Set<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Set<MenuItem> menu) {
        this.menu = menu;
    }
}

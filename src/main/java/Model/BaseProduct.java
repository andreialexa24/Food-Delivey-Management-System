package Model;

import java.awt.*;

public class BaseProduct extends MenuItem {


    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, int price) {
        super(title, rating, calories, proteins, fats, sodium, price);
    }

    @Override
    public int computePrice() {
        return super.getPrice();
    }
}

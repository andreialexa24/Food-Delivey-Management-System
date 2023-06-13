package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWriter {

    /**
     * Transforma obiectele din file-ul producsts.csv intr-un Set de MenuItem
     * @return
     */

    public static Set<MenuItem> products() {
        String file = "products.csv";
        Set<MenuItem> itemStream = new HashSet<>();
        try {
            Stream<String> stream = Files.lines(Paths.get(file));
            itemStream = stream.skip(1)
                    .map(FileWriter::mapping)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemStream;
    }

    /**
     *
     * Parseaza o linie din file si o transforma intr-un MenuItem
     */
    public static BaseProduct mapping(String line) {
        String[] attributes = line.split(",");   //primesc o linie sii ii dau split in fct de virgula
                                                        //si fiecare element din array, il parsez
        String title = attributes[0];
        double rating = Double.parseDouble(attributes[1]);
        int calories = Integer.parseInt(attributes[2]);
        int proteins = Integer.parseInt(attributes[3]);
        int fats = Integer.parseInt(attributes[4]);
        int sodium = Integer.parseInt(attributes[5]);
        int price = Integer.parseInt(attributes[6]);
        return new BaseProduct(title, rating, calories, proteins, fats, sodium, price);
    }


    /**
     * Face o factura pentru comanda
     * @param o
     * @param items
     */
    public static void bill(Order o, List<MenuItem> items)
    {
        java.io.FileWriter writer= null;
        try {
            writer = new java.io.FileWriter("bill.txt");
            writer.append(o.toString());
            writer.append("\n");
            for(MenuItem menuItem:items)
            {
                writer.append(menuItem.toString());
                writer.append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

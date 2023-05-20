import model.*;
import service.impl.UserServiceImpl;
import util.Category;

public class Main {
    public static void main(String[] args) {
        Address a = new Address("Romania", "Bucuresti", "Carol", "3F");
        Address a1 = new Address("Romania", "Bucuresti", "Carol", "3F", "203D", "10");
        Address a2 = new Address("Romania", "Bucuresti", "Carol", "78");
        Address a3 = new Address("Romania", "Bucuresti", "Carol", "109");

        Owner owner = new Owner("Alex", "0788899989", a);

        Product p = new Product("Tumble Tower", Category.Games, 17.78);
        Product p1 = new Product("Poker", Category.Games, 23.32);
        Product p2 = new Product("Monopoly", Category.Games, 68.99);
        Product p3 = new Product("Mascara", Category.Fashion, 38.99);
        Product p4 = new Product("Lipstick", Category.Fashion, 25.68);
        Product p5 = new Product("Food Bowl", Category.Pet, 10.68);
        Product p6 = new Product("Catnip", Category.Pet, 15.89);
        Product p7 = new Product("Leash", Category.Pet, 5.77);

        Store store = new Store("Game Heaven", a1);
        Store store1 = new Store("Chic Boutique", a2);
        Store store2 = new Store("Pet Shop", a3);
        store.addProduct(p);
        store.addProduct(p1);
        store.addProduct(p2);
        store1.addProduct(p3);
        store1.addProduct(p4);
        store2.addProduct(p5);
        store2.addProduct(p6);
        store2.addProduct(p7);

        // Add some stores
        owner.addStore(store);
        owner.addStore(store1);
        owner.addStore(store2);

        // Owner menu
        owner.menu();

        // User menu
        new UserServiceImpl();
    }
}
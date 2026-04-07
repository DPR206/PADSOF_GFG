import user.StorePermission;
import product.Category;

public class TestStorePermission {
    public static void main(String[] args) {
        // Initialize store and categories
        StorePermission sp = new StorePermission();
        // Add some categories that are in the CSV
        sp.getStore().addCategory(new Category("manga", 0.0));
        sp.getStore().addCategory(new Category("aventuras", 0.0));
        sp.getStore().addCategory(new Category("estrategia", 0.0));
        sp.getStore().addCategory(new Category("accion", 0.0));
        sp.getStore().addCategory(new Category("militar", 0.0));
        sp.getStore().addCategory(new Category("multijugador", 0.0));
        sp.getStore().addCategory(new Category("funko", 0.0));
        sp.getStore().addCategory(new Category("cine", 0.0));
        sp.getStore().addCategory(new Category("familiar", 0.0));
        sp.getStore().addCategory(new Category("anime", 0.0));
        sp.getStore().addCategory(new Category("robot", 0.0));
        sp.getStore().addCategory(new Category("ciencia-ficcion", 0.0));
        sp.getStore().addCategory(new Category("cocina", 0.0));
        sp.getStore().addCategory(new Category("costumbrismo", 0.0));
        sp.getStore().addCategory(new Category("japon", 0.0));

        try {
            boolean result = sp.addProductByFile("resources/productos.csv");
            System.out.println("addProductByFile result: " + result);
            System.out.println("Products in store: " + sp.getStore().getStoreProducts().size());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
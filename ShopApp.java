import java.util.HashMap;
import java.util.Map;

public class ShopApp {
    public static void main(String[] args) {
        SalesManager manager = new SalesManager();

        // Добавляем продажи
        manager.addSale("Яблоко", 50.0);
        manager.addSale("Банан", 30.0);
        manager.addSale("Яблоко", 50.0);
        manager.addSale("Апельсин", 60.0);
        manager.addSale("Банан", 30.0);
        manager.addSale("Яблоко", 50.0);

        // Выводим результаты
        manager.printSales();
        System.out.println("Общая сумма продаж: " + manager.calculateTotalSum() + " руб.");
        System.out.println("Наиболее популярный товар: " + manager.getMostPopularProduct());
    }
}

// Класс для управления продажами
class SalesManager {
    // HashMap: Ключ — Название товара, Значение — Количество проданных единиц
    private HashMap<String, Integer> salesCount = new HashMap<>();
    
    // HashMap: Ключ — Название товара, Значение — Цена за единицу
    private HashMap<String, Double> productPrices = new HashMap<>();

    // Метод добавления продажи
    public void addSale(String productName, double price) {
        // Обновляем количество проданных товаров (если товара нет — ставим 1)
        salesCount.put(productName, salesCount.getOrDefault(productName, 0) + 1);
        
        // Запоминаем цену товара (для расчета общей суммы)
        productPrices.put(productName, price);
    }

    // Вывод списка всех проданных товаров
    public void printSales() {
        System.out.println("Список проданных товаров:");
        for (String name : salesCount.keySet()) {
            System.out.println(name + " — Продано: " + salesCount.get(name) + " шт.");
        }
    }

    // Расчет общей суммы продаж
    public double calculateTotalSum() {
        double total = 0;
        for (String name : salesCount.keySet()) {
            // Сумма = Количество * Цена
            total += salesCount.get(name) * productPrices.get(name);
        }
        return total;
    }

    // Поиск наиболее популярного товара (где количество продаж максимально)
    public String getMostPopularProduct() {
        String popular = "Нет продаж";
        int maxSales = 0;

        for (Map.Entry<String, Integer> entry : salesCount.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                popular = entry.getKey();
            }
        }
        return popular;
    }
}

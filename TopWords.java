import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "text.txt";
        File file = new File(filePath);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            return;
        }

        // Подсчет слов в Map (слово -> количество)
        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext()) {
            // Убираем лишние знаки и переводим в нижний регистр
            String word = scanner.next().toLowerCase().replaceAll("[^a-zа-я0-9]", "");
            
            if (!word.isEmpty()) {
                // Если слово уже было — прибавляем 1, если нет — ставим 1
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }
        scanner.close();

        // Переносим данные в список для сортировки
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        // Сортировка списка по убыванию значения
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        // Вывод первых 10 результатов
        System.out.println("Топ-10 частых слов:");
        int count = 0;
        for (Map.Entry<String, Integer> entry : list) {
            if (count < 10) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                count++;
            } else {
                break;
            }
        }
    }
}

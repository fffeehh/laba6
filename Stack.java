public class Stack<T> {
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        // Создаем массив из объектов и приводим к типу T (стандартный хак для дженериков)
        data = (T[]) new Object[capacity];
        size = 0;
    }

    // Добавление элемента в стек
    public void push(T element) {
        if (size < data.length) {
            data[size] = element; // Кладем элемент на текущую свободную позицию
            size++;               // Двигаем границу стека вперед
        } else {
            System.out.println("Стек переполнен");
        }
    }

    // Удаление и возвращение верхнего элемента
    public T pop() {
        if (size == 0) {
            System.out.println("Стек пуст");
            return null;
        }
        size--;             // Двигаем границу назад
        T element = data[size];
        data[size] = null;  // Очищаем ячейку для сборщика мусора
        return element;
    }

    // Получение верхнего элемента без удаления
    public T peek() {
        if (size == 0) {
            System.out.println("Стек пуст");
            return null;
        }
        // Возвращаем элемент, который лежит перед текущей пустой позицией
        return data[size - 1];
    }

    public static void main(String[] args) {
        // Пример использования
        Stack<Integer> stack = new Stack<>(10);
        
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Удален (pop): " + stack.pop()); // Должен вывести 3
        System.out.println("Верхний (peek): " + stack.peek()); // Должен вывести 2
        
        stack.push(4);
        System.out.println("Удален (pop): " + stack.pop()); // Должен вывести 4
    }
}

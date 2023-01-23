import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] operationsArr = {"Добавить", "Показать", "Удалить", "Найти"};
        List<String> operations = new ArrayList<>(Arrays.asList(operationsArr)); //список доступных операций
        List<String> purchases = new LinkedList<>(); //список покупок
        Scanner scan = new Scanner(System.in);


        System.out.println("Вам доступны следующие операции: ");
        for (int i = 0; i < operations.size(); i++) {
           System.out.println(i + 1 + ". " + operations.get(i));
        }


        while (true) {
            System.out.println("Выберите операцию и введите ее номер: ");
            String input = scan.nextLine();
            int operationNumber;

            try {
                operationNumber = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Проверьте корректность ввода данных");
                continue;
            }

            switch (operationNumber) {
                case 1:
                    System.out.println("Какую покупку вы ходите добавить? ");
                    String inputPurchase = scan.nextLine();
                    if (!inputPurchase.trim().equals("")) {
                        purchases.add(capitalize(inputPurchase));
                        System.out.println("Итого в списке покупок: " + purchases.size());
                    }
                    break;
                case 2:
                    purchasePrint(purchases);
                    break;
                case 3:
                    purchasePrint(purchases);
                    System.out.println("Какую хотите удалить? Введите номер или название: ");
                    String inputToDelete = scan.nextLine();
                    int numberToDelete = -1;
                    String purchaseToDelete = null;
                    try {
                        numberToDelete = (Integer.parseInt(inputToDelete.trim()) - 1); //определяем номер покупки к удалению
                    } catch (NumberFormatException exception) {
                        purchaseToDelete = inputToDelete; //если введена строка, то будем пытаться удалить ее
                    }

                    if (numberToDelete >= 0 && numberToDelete < purchases.size()) {
                        String deleting = purchases.get(numberToDelete);
                        purchases.remove(numberToDelete);
                        System.out.println("Покупка '" + deleting + "' удалена.");
                        purchasePrint(purchases);
                    } else if (numberToDelete >= purchases.size() || (numberToDelete < 0 && purchaseToDelete == null)) {
                        System.out.println("Покупки с таким номером не существует");
                    } else if (purchaseToDelete != null) {
                        try {
                            purchaseToDelete = capitalize(purchaseToDelete);
                            purchases.remove(purchases.indexOf(purchaseToDelete));
                            System.out.println("Покупка '" + purchaseToDelete + "' удалена.");
                            purchasePrint(purchases);
                        } catch (IndexOutOfBoundsException exception) {
                            System.out.println("Покупки с таким названием не существует");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Введите текст для поиска: ");
                    String inputToFind = scan.nextLine();
                    boolean flag = true;
                    for (int i = 0; i < purchases.size(); i++) {
                        String itemLower = purchases.get(i).trim().toLowerCase();
                        String toFindLower = inputToFind.trim().toLowerCase();
                        if (itemLower.contains(toFindLower)) {
                            System.out.println(i + 1 + ". " + purchases.get(i));
                            flag = false;
                        }
                    }
                    if(flag) {
                        System.out.println("Таких покупок не нашлось");
                    }

                    break;
                default:
                    System.out.println("Нет операции с таким номером.");
            }
        }

    }

    public static void purchasePrint(List purchases) { // метод выводит в консоль список покупок
        System.out.println("Список покупок: ");
        for (int i = 0; i < purchases.size(); i++) {
            System.out.println(i + 1 + ". " + purchases.get(i));
        }
    }

    public static String capitalize(String inputPurchase) { // метод делает первую букву покупки заглавной, остальные - строчными
        inputPurchase = inputPurchase.trim().toLowerCase();
        return Character.toUpperCase(inputPurchase.charAt(0)) + inputPurchase.substring(1);

    }
}
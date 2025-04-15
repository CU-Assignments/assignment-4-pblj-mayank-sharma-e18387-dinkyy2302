import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return symbol + " - " + value;
    }
}

public class CardCollection {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, List<Card>> cardMap = new HashMap<>();

        System.out.print("Enter the number of cards you want to store: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Enter card " + (i + 1) + " symbol: ");
            String symbol = scanner.nextLine();

            System.out.print("Enter card " + (i + 1) + " value: ");
            String value = scanner.nextLine();

            Card card = new Card(symbol, value);

            cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
        }

        System.out.print("\nEnter the symbol to search: ");
        String searchSymbol = scanner.nextLine();

        List<Card> result = cardMap.get(searchSymbol);
        if (result != null && !result.isEmpty()) {
            System.out.println("Cards of symbol " + searchSymbol + ":");
            for (Card c : result) {
                System.out.println(c.getValue());
            }
        } else {
            System.out.println("No cards found with symbol: " + searchSymbol);
        }

        scanner.close();
    }
}

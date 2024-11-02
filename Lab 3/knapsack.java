import java.util.ArrayList;
import java.util.List;

public class knapsack {
    public static int knapsack(int[] w, int[] p, int C, int n) {
        int[] max_profits = new int[C + 1];
        int[] choice = new int[C + 1];

        // initializing max_profits array and choice array
        for (int i = 1; i <= C; i++) {
            max_profits[i] = 0;
            choice[i] = -1;
        }

        // outer loop for each capacity from 1 to C
        for (int i = 1; i <= C; i++) {
            System.out.println();
            System.out.println("Capacity: " + i);
            int chosenItem = -1;

            // iterates through each object
            for (int j = 0; j < n; j++) {
                // checking if object j can fit into knapsack of capacity i
                if (w[j] <= i) {

                    int profitIfTaken = max_profits[i - w[j]] + p[j];


                    System.out.println("  Considering item " + j + " (weight " + w[j] +
                            ", profit " + p[j] + ") -> profitIfTaken: " + profitIfTaken);
                    System.out.println("  Total profit if this item is chosen: " + profitIfTaken );
                    System.out.println();

                    if (profitIfTaken > max_profits[i]) {
                        max_profits[i] = profitIfTaken;
                        choice[i] = j;
                        chosenItem = j;

                    }
                }
            }

            if (chosenItem != -1) {
                System.out.println("  Chosen item for capacity " + i + ": (index: " + chosenItem +
                        ", weight: " + w[chosenItem] + ", profit: " + p[chosenItem] + ")");
            }
            System.out.print("  Updated max_profits array: ");
            for (int k = 0; k <= C; k++) {
                System.out.print(max_profits[k] + " ");
            }
            System.out.println();
        }

        List<Integer> itemsUsed = new ArrayList<>();
        int remainingCapacity = C;
        while (remainingCapacity > 0 && choice[remainingCapacity] != -1) {
            int chosenItem = choice[remainingCapacity];
            itemsUsed.add(chosenItem);
            remainingCapacity -= w[chosenItem];
        }

        System.out.println(" ");
        System.out.println("Items used (by index): " + itemsUsed);
        return max_profits[C];
    }

    public static void main(String[] args) {
        // Q(2)
        //int[] weights = {4, 6, 8};
        //int[] profits = {7, 6, 9};
        //int capacity = 14;
        //int numItems = weights.length;
        //int maxProfit = knapsack(weights, profits, capacity, numItems);
        //System.out.println("Maximum profit: " + maxProfit);

        // Q(4)
        int[] weights1 = {5, 6, 8};
        int[] profits1 = {7, 6, 9};
        int capacity1 = 14;
        int numItems1 = weights1.length;
        int maxProfit1 = knapsack(weights1, profits1, capacity1, numItems1);
        System.out.println("Maximum profit: " + maxProfit1);
    }
}
import java.util.ArrayList;
import java.util.Scanner;

public class Krushkals {
    void algorithm(ArrayList<ArrayList<Node>> graph, int N) {
        
    }
    public static void main(String[] args) {
        int length;
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

        System.out.println("How any nodes you want to add?");
        length = scanner.nextInt();
        for (int i = 0; i < length; i++) graph.add(new ArrayList<Node>());

        while (true) {
            int from = -1;
            while (from < 0 || from > length - 1) {
                System.out.println("Enter the node from which you want to add the edge (0 - " + (length-1) + "):\t");
                from = scanner.nextInt();
            }
            int value = -1;
            while (value < 0 || value > length - 1) {
                System.out.println("Enter the value of the node (0 - " + (length-1) + "):\t");
                value  = scanner.nextInt();
            }
            System.out.println("Enter the weight of the edge joining " + from + " and " + value + ":\t");
            int weight  = scanner.nextInt();

            graph.get(from).add(new Node(value, weight));
            graph.get(value).add(new Node(from, weight));

            System.out.println("Do you want to add more nodes? (1: y / 0: n)");
            if (scanner.nextInt() == 0) {
                break;
            }
        }

        algorithm(graph, length);
        scanner.close();
    }
}

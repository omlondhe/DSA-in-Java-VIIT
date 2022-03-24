import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int value;
    int weight;

    public Node(int _value, int _weight) {
        value = _value;
        weight = _weight;
    }

    public int getValue() {
        return value;
    }
    public int getWeight() {
        return weight;
    }
}

public class Prims {
    static void algorithm(ArrayList<ArrayList<Node>> graph, int N) {
        int[] weights = new int[N];
        int[] parent = new int[N];
        boolean[] minimumSpanningTree = new boolean[N];

        for (int i = 0; i < N; i++) {
            weights[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            minimumSpanningTree[i] = false;
        }

        weights[0] = 0;

        for (int i = 0; i < N-1; i++) {
            int minimumWeight = Integer.MAX_VALUE;
            int currentNodeIndex = 0;
            for (int j = 0; j < N; j++) {
                if (!minimumSpanningTree[j] && weights[j] < minimumWeight) {
                    minimumWeight = weights[j];
                    currentNodeIndex = j;
                }
            }

            minimumSpanningTree[currentNodeIndex] = true;
            
            for (Node node: graph.get(currentNodeIndex)) {
                if (!minimumSpanningTree[node.getValue()] && node.getWeight() < weights[node.getValue()]) {
                    parent[node.getValue()] = currentNodeIndex;
                    weights[node.getValue()] = node.getWeight();
                }
            }
        }

        for (int i = 1; i < N; i++) {
            System.out.println(parent[i] + " ->" + i);
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        
        Scanner scanner = new Scanner(System.in);
        int length;
        
        System.out.println("How many nodes you want to add?");
        length = scanner.nextInt();
        
        for (int i = 0; i < length; i++) graph.add(new ArrayList<Node>());

        while (true) {
            int from = -1;
            while (from < 0 || from > length - 1) {
                System.out.println("Enter the node from which you want to add the edge (0 - " + (length-1) + "):\t");
                from  = scanner.nextInt();
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
        scanner.close();
        algorithm(graph, length);
    }
}

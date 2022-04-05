import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparator<Node> {
    int value;
    int weight;

    public Node() {}
    public Node(int _v, int _w) { value = _v; weight = _w; }

    public int getWeight() { return weight; }
    public int getValue() { return value; }

    @Override
    public int compare(Node node1, Node node2) {
        return (node1.getWeight() < node2.getWeight()) ? -1 : (node1.getWeight() > node2.getWeight()) ? 1 : 0;
    }
}

public class Dijkstra {
    static void algorithm(int source, ArrayList<ArrayList<Node>> graph, int N) {
        int distance[] = new int[N];
        for (int i = 0; i < N; i++) distance[i] = Integer.MAX_VALUE;
        distance[0] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(N, new Node());
        priorityQueue.add(new Node(source, 0));

        while (priorityQueue.size() > 0) {
            Node node = priorityQueue.poll();
            
            for (Node it: graph.get(node.getValue())) {
                if (distance[node.getValue()] + it.getWeight() < distance[it.getValue()]) {
                    distance[it.getValue()] = distance[node.getValue()] + it.getWeight();
                    priorityQueue.add(new Node(it.getValue(), distance[it.getValue()]));
                }
            }
        }

        for (int i = 0; i < N; i++) System.out.print(distance[i] + " ");
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
        algorithm(0, graph, length);
    }
}
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Node {
    int u;
    int v;
    int weight;

    public Node(int _u, int _v, int _weight) {
        u = _u;
        v = _v;
        weight = _weight;
    }

    public int getV() {
        return v;
    }
    public int getU() {
        return u;
    }
    public int getWeight() {
        return weight;
    }
}

class SortComparator implements Comparator<Node> {
    @Override
    public int compare(Node node1, Node node2) {
        return node1.getWeight() < node2.getWeight() ? -1 : node1.getWeight() > node2.getWeight() ? 1 : 0;
    }
}

public class Krushkals {
    static int findParent(int node, int parent[]) {
        if (node == parent[node]) return node;

        return parent[node] = findParent(parent[node], parent);
    }

    static void union(int u, int v, int parent[], int rank[]) {
        u = findParent(u, parent);
        v = findParent(v, parent);

        if (rank[u] < rank[v]) parent[u] = v;
        else if (rank[v] < rank[u]) parent[v] = u;
        else {
            parent[v] = u;
            rank[u]++;
        }
    }

    static void algorithm(ArrayList<Node> graph, int N) {
        int parent[] = new int[N];
        int rank[] = new int[N];    

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int costMST = 0;
        ArrayList<Node> mst = new ArrayList<Node>();
        for (Node node: graph) {
            if (findParent(node.getU(), parent) != findParent(node.getV(), parent)) {
                costMST += node.getWeight();
                mst.add(node);
                union(node.getU(), node.getV(), parent, rank);
            }
        }

        System.out.println("Cost: " + costMST);
        for (Node node: mst) System.out.println(node.getU() + " -> " + node.getV());
    }

    public static void main(String[] args) {
        int length;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Node> graph = new ArrayList<Node>();

        System.out.println("How any nodes you want to add?");
        length = scanner.nextInt();

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

            graph.add(new Node(from, value, weight));
            graph.add(new Node(value, from, weight));

            System.out.println("Do you want to add more nodes? (1: y / 0: n)");
            if (scanner.nextInt() == 0) {
                break;
            }
        }

        algorithm(graph, length);
        scanner.close();
    }
}

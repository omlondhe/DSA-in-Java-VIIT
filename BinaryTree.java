import java.util.Scanner;

class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int value) {
        root = new TreeNode(value);
    }   

    TreeNode addNode(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        } else if (value < root.value) {
            root.left = addNode(root.left, value);
        } else {
            root.right = addNode(root.right, value);
        }
        return root;
    }

    void insertNode(int value) {
        this.root = addNode(this.root, value);
    }

    TreeNode delete(TreeNode root, int value) {
        if (root == null) return root;

        if (value < root.value) root.left = delete(root.left, value);
        else if (value > root.value) root.right = delete(root.right, value);

        else {
            if (root.left == null) return root.right;  
            if (root.right == null) return root.left;  

            root.value = inOrderPreDecessor(root.right);
            root.right = delete(root.right, root.value);
        }

        return root;
    }

    void deleteNode(int value) {
        root = delete(root, value);        
    }

    void showBinaryTreeUsingInOrderTraversal(TreeNode root) {
        if (root == null) return;
        showBinaryTreeUsingInOrderTraversal(root.left);
        System.out.print(root.value + " ");
        showBinaryTreeUsingInOrderTraversal(root.right);
    }
    void showBinaryTreeUsingPreOrderTraversal(TreeNode root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        showBinaryTreeUsingPreOrderTraversal(root.left);
        showBinaryTreeUsingPreOrderTraversal(root.right);
    }
    void showBinaryTreeUsingPostOrderTraversal(TreeNode root) {
        if (root == null) return;
        showBinaryTreeUsingPostOrderTraversal(root.left);
        showBinaryTreeUsingPostOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    void show() {
        System.out.println("Pre Order traversal: ");
        showBinaryTreeUsingPreOrderTraversal(root);
        System.out.println("\n------------------------");
        System.out.println("In Order traversal: ");
        showBinaryTreeUsingInOrderTraversal(root);
        System.out.println("\n------------------------");
        System.out.println("Post Order traversal: ");
        showBinaryTreeUsingPostOrderTraversal(root);
        System.out.println("\n------------------------");
    }

    int inOrderPreDecessor(TreeNode node) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    int inOrderSuccessor(TreeNode node) {
        int maxValue = root.value;
        while(root.right != null) {
            maxValue = root.right.value;
            root = root.right;
        }
        return maxValue;
    }

    TreeNode search(int value) {
        TreeNode tempRoot = root;
        while (tempRoot != null) {
            if (value == tempRoot.value) {
                return tempRoot;
            }
            if (value < tempRoot.value) {
                tempRoot = tempRoot.left;
            } else {
                tempRoot = tempRoot.right;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        System.out.println("Current tree: ");
        tree.show();
        
        while (true) {
            System.out.println("What action you want to perform?");
            System.out.println("0\tExit");
            System.out.println("1\tInsert a node");
            System.out.println("2\tDelete a node");
            System.out.println("3\tSearch a node");
            System.out.println("4\tShow full tree");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Enter a number to insert:\t");
                    tree.insertNode(scanner.nextInt());
                    break;
                case 2: 
                    System.out.print("Enter a number to delete respective node:\t");
                    tree.deleteNode(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter a number to search in Binary Tree:\t");
                    TreeNode node = tree.search(scanner.nextInt());
                    if (node == null) {
                        System.out.println("Node not found!");
                    } else {
                        System.out.println("Node is present");
                        System.out.println("Its left element is " + node.left == null ? null : node.left.value);
                        System.out.println("Its right element is " + node.right == null ? null : node.right.value);
                        System.out.println("Its in order pre-decessor element is " + tree.inOrderPreDecessor(node));
                        System.out.println("Its in order successor element is " + tree.inOrderSuccessor(node));
                    }
                    break;
                case 4:
                    tree.show();
                    break;
            
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

}

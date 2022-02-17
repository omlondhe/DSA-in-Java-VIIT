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
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            root = null;
            return null;
        }

        if (value < root.value) root.left =  delete(root.left, value);
        else if (value > root.value) root.right = delete(root.right, value);
        else {
            TreeNode node = inOrderPreDecessor(root);
            if (node == null) {
                node = inOrderPostSuccessor(root);
                root.value = node.value;
                root.right = delete(root.right, node.value);
            } else {
                root.value = node.value;
                root.left = delete(root.left, node.value);
            }
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
        if (root == null) {
            System.err.println("Tree is empty");
            return;
        }
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

    TreeNode inOrderPreDecessor(TreeNode node) {
        node = node.left;
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    TreeNode inOrderPostSuccessor(TreeNode node) {
        node = node.right;
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

        while (true) {
            System.out.println("What action you want to perform?");
            System.out.println("0.\tExit");
            System.out.println("1.\tInsert a node");
            System.out.println("2.\tDelete a node");
            System.out.println("3.\tSearch a node");
            System.out.println("4.\tGet In order pre-decessor");
            System.out.println("5.\tGet In order post-successor");
            System.out.println("6.\tShow full tree");
            
            System.out.print("Enter your choice:\t");
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
                        TreeNode treeNode;
                        System.out.println("Node is present");
                        String left = node.left == null ? "null" : String.valueOf(node.left.value);
                        System.out.println("Left child             :\t" + left);
                        String right = node.right == null ? "null" : String.valueOf(node.right.value);
                        System.out.println("Right child            :\t" + right);
                        treeNode = tree.inOrderPreDecessor(node);
                        String inOrderPreDecessor = treeNode == null ? "null" : String.valueOf(treeNode.value);
                        System.out.println("In Order Pre-decessor  :\t" + inOrderPreDecessor);
                        treeNode = tree.inOrderPostSuccessor(node);
                        String inOrderPostSuccessor = treeNode == null ? "null" : String.valueOf(treeNode.value);
                        System.out.println("In Order Post-successor:\t" + inOrderPostSuccessor);
                    }
                    break;
                case 4: 
                    System.out.print("Enter an element for which you want to find:\t");
                    TreeNode treeNode1 = tree.search(scanner.nextInt());
                    if (treeNode1 == null) System.out.println("Node not found!");
                    else {
                        treeNode1 = tree.inOrderPreDecessor(treeNode1);
                        if (treeNode1 == null) System.out.println("Not found!");
                        else System.out.println(treeNode1.value);
                    }
                    break;
                    case 5:
                    System.out.print("Enter an element for which you want to find:\t");
                    TreeNode treeNode2 = tree.search(scanner.nextInt());
                    if (treeNode2 == null) System.out.println("Node not found!");
                    else {
                        treeNode2 = tree.inOrderPostSuccessor(treeNode2);
                        if (treeNode2 == null) System.out.println("Not found!");
                        else System.out.println(treeNode2.value);
                    }
                    break;
                case 6:
                    tree.show();
                    break;
            
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

}


/*
1
5
1
3
1
7
1
6
1
8
1
4
1
2
*/
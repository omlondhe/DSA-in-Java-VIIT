// Code is written by referring to GeeksForGeeks article
import java.util.Scanner;

public class Trie{
	
	static int ALPHABET_SIZE = 26;

	// trie node
	static class TrieNode {
		TrieNode children[] = new TrieNode[ALPHABET_SIZE];

		// isEndOfWord is true if the node represents
		// end of a word
		boolean isEndOfWord;
	}


	// If not present, inserts key into trie
	// If the key is prefix of trie node, just
	// marks leaf node
	static void insert(TrieNode root, String key)
	{
		TrieNode pCrawl = root;

		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (pCrawl.children[index] == null)
				pCrawl.children[index] = new TrieNode();

			pCrawl = pCrawl.children[index];
		}

		// mark last node as leaf
		pCrawl.isEndOfWord = true;
	}

	// Returns true if key presents in trie, else
	// false
	static boolean search(TrieNode root, String key)
	{
		TrieNode pCrawl = root;

		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (pCrawl.children[index] == null)
				return false;

			pCrawl = pCrawl.children[index];
		}

		return (pCrawl != null && pCrawl.isEndOfWord);
	}

	// Returns true if root has no children, else false
	static boolean isEmpty(TrieNode root)
	{
		for (int i = 0; i < ALPHABET_SIZE; i++)
			if (root.children[i] != null)
				return false;
		return true;
	}

	// Recursive function to delete a key from given Trie
	static TrieNode remove(TrieNode root, String key, int depth)
	{
		// If tree is empty
		if (root == null)
			return null;

		// If last character of key is being processed
		if (depth == key.length()) {

			// This node is no more end of word after
			// removal of given key
			if (root.isEndOfWord)
				root.isEndOfWord = false;

			// If given is not prefix of any other word
			if (isEmpty(root)) {
				root = null;
			}

			return root;
		}

		// If not last character, recur for the child
		// obtained using ASCII value
		int index = key.charAt(depth) - 'a';
		root.children[index] =
			remove(root.children[index], key, depth + 1);

		// If root does not have any child (its only child got
		// deleted), and it is not end of another word.
		if (isEmpty(root) && root.isEndOfWord == false){
			root = null;
		}

		return root;
	}

	// Driver
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);

		TrieNode root = new TrieNode();
	
        System.out.println("How many words you want to enter?");
        int length = scanner.nextInt();
        
		// Construct trie
		for (int i = 0; i < length ; i++) insert(root, scanner.next());
	
        while (true) {
            System.out.print("Which operation you want to perform?\n1.\tInsert\n2.\tSearch\n3.\tDelete\n4.\tExit\n\nSelect your option:\t");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter the word:\t");
                    insert(root, scanner.next());
                    break;
                case 2:
                    System.out.print("Enter the word:\t");
                    if (search(root, scanner.next()))
                        System.out.print("Present\n\n");
                    else
                        System.out.print("Not Found\n\n");
                    break;
                case 3:
                    System.out.print("Enter the word:\t");
                    remove(root, scanner.next(), 0);
                    break;
                case 4:
                    System.out.println("Exiting, Bye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default: 
                    System.out.println("Incorrect choice!");
            }
        }
	}
}


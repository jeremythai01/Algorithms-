import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree {

    private Node root;

    private class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;

        Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

    }

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value, null);
        } else {
            insert(value, root);
        }
    }

    public void remove(int value) {
        remove(value, root);
    }

    public boolean contains(int value) {
        return contains(value, root);
    }

    public int getHeight() {
        return getHeight(root);
    }

    public Integer findMin() {
        Node minNode = findMin(root);
        if (minNode == null) return null;
        return minNode.value;
    }


    public List<Integer> infixOrder() {
        List<Integer> elements = new LinkedList<>();
        infixOrder(root, elements);
        return elements;
    }

    public List<Integer> levelOrder() {
        List<Integer> elements = new LinkedList<>();
        ArrayDeque<Node> nodesCheck = new ArrayDeque<>();

        if (root != null) {
            nodesCheck.push(root);
            levelOrder(nodesCheck, elements);
        }

        return elements;
    }

    private void insert(int value, Node currentNode) {

        if (contains(value))
            return;

        if (value < currentNode.value) {

            if (currentNode.left == null)
                currentNode.left = new Node(value, currentNode);
            else
                insert(value, currentNode.left);

        } else {
            if (currentNode.right == null)
                currentNode.right = new Node(value, currentNode);
            else
                insert(value, currentNode.right);
        }
    }

    private void remove(int value, Node currentNode) {

        if (!contains(value))
            return;

        if (value < currentNode.value)
            remove(value, currentNode.left);

        else if (value > currentNode.value)
            remove(value, currentNode.right);

        else if (currentNode.left != null && currentNode.right != null) {
            currentNode.value = findMin(currentNode.right).value;
            remove(currentNode.value, currentNode.right);
        } else
            currentNode = (currentNode.left != null) ? currentNode.left : currentNode.right;
    }


    private boolean contains(int value, Node currentNode) {

        while (currentNode != null) {

            if (value < currentNode.value)
                currentNode = currentNode.left;

            else if (value > currentNode.value)
                currentNode = currentNode.right;

            else
                return true;
        }
        return false;
    }

    private Node findMin(Node currentNode) {

        while (currentNode.left != null)
            currentNode = currentNode.left;

        return currentNode;

    }

    private void infixOrder(Node currentNode, List<Integer> elements) {
        if (currentNode.left != null) infixOrder(currentNode.left, elements);
        elements.add(currentNode.value);
        if (currentNode.right != null) infixOrder(currentNode.right, elements);
    }

    private void levelOrder(ArrayDeque<Node> nodesCheck, List<Integer> elements) {
        Node node;
        while (nodesCheck.isEmpty() == false) {
            node = nodesCheck.pollFirst();
            elements.add(node.value);
            if (node.left != null)
                nodesCheck.addLast(node.left);
            if (node.right != null)
                nodesCheck.addLast(node.right);
        }
    }

    private int getHeight(Node currentNode) {
        if (currentNode == null)
            return 0;

        return 1 + Math.max(getHeight(currentNode.left), getHeight(currentNode.right));

    }
}


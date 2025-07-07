package logicalTasks;

public class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this(value, null, null);
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * root -> left -> right
     *
     * @param node
     */
    static void preorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * left -> right -> root
     *
     * @param node
     */
    static void postorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * left -> root -> right
     *
     * @param node
     */
    static void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        inorderTraversal(node.left);
        System.out.print(node.value + " ");
        inorderTraversal(node.right);
    }
}

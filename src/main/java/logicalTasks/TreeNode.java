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
}

/**
 * Definition for a binary tree node.
 * public class BinaryTreeNode {
 *     int val;
 *     BinaryTreeNode left;
 *     BinaryTreeNode right;
 *     BinaryTreeNode(int x) { val = x; }
 * }
 */
class DeleteNode {
    public BinaryTreeNode deleteNode(BinaryTreeNode root, int key) {
        // Base case
        if (root == null) { return root; }
        //If the current node's value equals the value we are trying to delete.
        if (root.val == key) {
          // If the current node's right child holds a value.
          if (root.right != null) {
            // Hold a reference to the current node's right child.
            BinaryTreeNode currentNode = root.right;
            // Hold a copy of the reference to the current node's right child
            // so we can transverse through the node and modifies it's content
            BinaryTreeNode leftMost = currentNode;
            // While the reference still holds a left child value, tranverse.
            while (leftMost.left != null) { leftMost = leftMost.left; }
            // Replace the current node's right child's left child with the
            // current node's left child to maintain's reference while swapping
            // nodes.
            leftMost.left = root.left;
            // Return the current node's right child.
            return currentNode;
          }
          // If the current node's right child does not hold a value,
          // return the current node's left child.
          return root.left;
        }
        // If the current node's value is less than the value we would like to
        // delete, based off of convention, if the value exists, the key will
        // be found transversing through the sub tree's right branch.
        if (root.val < key) { root.right = deleteNode(root.right, key); }
        // If the current node's value is right than the value we would like to
        // delete, based off of convention, if the value exists, the key will
        // be found transversing through the sub tree's left branch.
        if (root.val > key) { root.left = deleteNode(root.left, key); }
        // Return the given tree's state post evaluation.
        return root;
    }
}

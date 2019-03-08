package tree;

 public class BinaryTreeNode {

     private String data;
     private BinaryTreeNode left, right;

     BinaryTreeNode (String s) {
         data = s;
         left = null;
         right = null;
     }

     public void add (String s, String child) {
         if (child.equals("L")) {
             left = new BinaryTreeNode(s);
         } else if (child.equals("R")) {
             right = new BinaryTreeNode(s);
         } else {
             throw new IllegalArgumentException();
         }
     }

     public BinaryTreeNode getChild (String child) {
         return (child.equals("L")) ? left : right;
     }

     public String getString () {
         return data;
     }

     public static void preOrderPrint (BinaryTreeNode n) {
       if (n == null) { return; }
       System.out.printf("\nCurrent: %s\n", n.data);
       preOrderPrint(n.left);
       preOrderPrint(n.right);
     }

     public static void inOrderPrint (BinaryTreeNode n) {
       if (n == null) { return; }
       inOrderPrint(n.left);
       System.out.printf("\nCurrent: %s\n", n.data);
       inOrderPrint(n.right);
     }

     public static void postOrderPrint (BinaryTreeNode n) {
       if (n == null) { return; }
       inOrderPrint(n.left);
       inOrderPrint(n.right);
       System.out.printf("\nCurrent: %s\n", n.data);
     }

     // takes as input two references to two BinaryTreeNodes and determines if the
     // two trees are equivalent (same Nodes at each position and same values in each corresponding Node).
     public static boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
         if (n2 == null) { return true; }
         if (n1 == null) { return false; }
         if (areEquivalent(n1, n2)) { return true;}
         return sameTree(n1.left, n2) || sameTree(n1.right, n2);
     }

     private static boolean areEquivalent (BinaryTreeNode n1, BinaryTreeNode n2) {
       if (n1 == null && n2 == null) { return true; }
       if (n1 == null || n2 == null) { return false; }
       return (n1.data == n2.data) &&
              areEquivalent(n1.left, n2.left) &&
              areEquivalent(n1.right, n2.right);
     }

 }

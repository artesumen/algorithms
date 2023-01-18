package com.edu.algorithms.data_structures;

public class BinaryTrees {

    public static void main(String[] args) {
        BinaryTreeNode<String> root = new BinaryTreeNode<>();
        BinaryTreeNode<String> left = new BinaryTreeNode<>();
        left.data = "Left";
        BinaryTreeNode<String> right = new BinaryTreeNode<>();
        right.data = "Right";

        root.data = "Root";
        root.left = left;
        root.right = right;

        buildSibling(root);
        System.out.println(root);

//        System.out.println(BinaryTreeNode.isBalanced(root));
    }

    public static void buildSibling(BinaryTreeNode <String> tree){
        while(tree != null){
            constructLevel(tree);
            tree = tree.left;
        }
    }

    private static void constructLevel(BinaryTreeNode<String> startNode){
        while(startNode != null && startNode.left != null){
            startNode.left.next = startNode.right;
            if(startNode.next != null){
                startNode.right.next = startNode.next.left;
            }
            startNode = startNode.next;
        }
    }


}

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
    BinaryTreeNode<T> next;

    public static void preOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            System.out.println(root.data);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }

    }

    public static void inOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }


    }

    public static void postOrderTraversal(BinaryTreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }

    }

    public static boolean isBalanced(BinaryTreeNode tree) {
        if (tree == null) {
            return true;
        }
        return checkBalance(tree).balanced;

    }

    private static NodeWithHeight checkBalance(BinaryTreeNode tree) {
        if (tree == null) {
            return new NodeWithHeight(true, -1);
        }
        var leftResult = checkBalance(tree.left);
        if (!leftResult.balanced) {
            return leftResult;
        }
        var rightResult = checkBalance(tree.right);
        if (!rightResult.balanced) {
            return rightResult;
        }

        var isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        var height = Math.max(leftResult.height, rightResult.height) + 1;
        return new NodeWithHeight(isBalanced, height);
    }
}

class NodeWithHeight {
    boolean balanced;
    int height;

    public NodeWithHeight(boolean balanced, int height) {
        this.balanced = balanced;
        this.height = height;
    }
}

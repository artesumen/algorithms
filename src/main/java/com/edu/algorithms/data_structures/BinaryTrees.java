package com.edu.algorithms.data_structures;

public class BinaryTrees {

    public static void main(String[] args) {
        BinaryTreeNode <String> root = new BinaryTreeNode<>();
        BinaryTreeNode <String> left = new BinaryTreeNode<>();
        left.data="Left";
        BinaryTreeNode <String> right = new BinaryTreeNode<>();
        right.data="Right";

        root.data = "Root";
        root.left=left;
        root.right=right;

        BinaryTreeNode.preOrderTraversal(root);
        System.out.println("-----------------");
        BinaryTreeNode.inOrderTraversal(root);
        System.out.println("-----------------");
        BinaryTreeNode.postOrderTraversal(root);
    }


}

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

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
        if(root != null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }

    }
}

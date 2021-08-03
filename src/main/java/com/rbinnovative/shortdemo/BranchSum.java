package com.rbinnovative.shortdemo;

import java.util.*;

class BranchSum {
    // This is the class of the input root. Do not edit it.
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static List<Integer> branchSums(final BinaryTree root) {

        BinaryTree currentRoot = root;
        List<Integer> result = new ArrayList<>();
        result.add(root.value);
        while(currentRoot!= null) {
            result.get(result.size()-1 + root.value);
            currentRoot = nextRoot(root, result);
        }


//        if(root.left != null && root.right!= null){
//            List<Integer> result = branchSums();
//            Integer branchsize = result.get(result.size() - 1);
//        }
//        if( root.left == null && root.right == null){
//            List<Integer> branchSum = new ArrayList<>();
//            Integer result = root.value;
//            branchSum.add(result);
//            return branchSum;
//        } else if (root.left == null ) {
//            Integer value = root.value;
//            List<Integer> result = branchSums(root.right);
//            Integer branchsize = result.get(result.size() - 1);
//            result.set(result.size() - 1, branchsize + root.value);
//            return result;
//        }
//        return new ArrayList<>();
    }

    private static BinaryTree nextRoot(BinaryTree root, List<Integer> result) {
        if(root.left == null && root.right == null)
            return null;
        else if(root.left == null) {
            return root.right;
        } else {
            result.add(result.size()-1);
            return root.left;
        }
    }
}
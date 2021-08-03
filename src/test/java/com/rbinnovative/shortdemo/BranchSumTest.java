package com.rbinnovative.shortdemo;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BranchSumTest {

    public class BinaryTreeSetup {
        String leftId;
        String rightId;
        int value;

        public BinaryTreeSetup( String leftId, String rightId, int value) {
            this.leftId = leftId;
            this.rightId  = rightId;
            this.value = value;
        }
    }

    private static BranchSum.BinaryTree build(String id, Map<String, BinaryTreeSetup> nodesSetup) {
        if(!nodesSetup.containsKey(id)) throw new IllegalArgumentException(id);

        BinaryTreeSetup node = nodesSetup.get(id);

        BranchSum.BinaryTree binarytree = new BranchSum.BinaryTree(node.value);
        if(node.leftId != null) {
            binarytree.left = build(node.leftId, nodesSetup);
        }
        if(node.rightId != null) {
            binarytree.right = build(node.rightId, nodesSetup);
        }
        return binarytree;
    }

    @Test
    public void TestCase1() {
        // Given
        Map<String, BinaryTreeSetup> nodesSetup = Map.of(
                "root", new BinaryTreeSetup("2","3",1),
                "2", new BinaryTreeSetup("4","5",2),
                "3", new BinaryTreeSetup("6","7",3),
                "4", new BinaryTreeSetup("8","9",4),
                "5", new BinaryTreeSetup("10",null,5),
                "6", new BinaryTreeSetup(null,null,6),
                "7", new BinaryTreeSetup(null,null,7),
                "8", new BinaryTreeSetup(null,null,8),
                "9", new BinaryTreeSetup(   null,null,9),
                "10", new BinaryTreeSetup(null,null,10)
        );
        BranchSum.BinaryTree root = build("root", nodesSetup);

        // When
        final List<Integer> actualSums = BranchSum.branchSums(root);

        // Then
        assertThat(actualSums).containsExactly(15, 16, 18, 10, 11);
    }

    @Test
    public void TestCase2() {
        // Given
        Map<String, BinaryTreeSetup> nodesSetup = Map.of(
                "root", new BinaryTreeSetup(null,null,1)
        );
        BranchSum.BinaryTree root = build("root", nodesSetup);

        // When
        final List<Integer> actualSums = BranchSum.branchSums(root);

        // Then
        assertThat(actualSums).containsExactly(1);
    }

    @Test
    public void TestCase3() {
        // Given
        Map<String, BinaryTreeSetup> nodesSetup = Map.of(
                "root", new BinaryTreeSetup("2",null,1),
                "2", new BinaryTreeSetup(null,null,2)
        );
        BranchSum.BinaryTree root = build("root", nodesSetup);

        // When
        final List<Integer> actualSums = BranchSum.branchSums(root);

        // Then
        assertThat(actualSums).containsExactly(3);
    }

    @Test
    public void TestCase4() {
        // Given
        Map<String, BinaryTreeSetup> nodesSetup = Map.of(
                "root", new BinaryTreeSetup("2","3",1),
                "2", new BinaryTreeSetup("4","5",2),
                "3", new BinaryTreeSetup(null,null,3),
                "4", new BinaryTreeSetup(null,null,4),
                "5", new BinaryTreeSetup(null,null,5)
        );
        BranchSum.BinaryTree root = build("root", nodesSetup);

        // When
        final List<Integer> actualSums = BranchSum.branchSums(root);

        // Then
        assertThat(actualSums).containsExactly(7, 8, 4);
    }


    @Test
    public void TestCase5() {
        // Given
        Map<String, BinaryTreeSetup> nodesSetup = Map.ofEntries(
                Map.entry("root", new BinaryTreeSetup("2","3",1)),
                Map.entry("2", new BinaryTreeSetup("4","5",2)),
                Map.entry("3", new BinaryTreeSetup("6","7",3)),
                Map.entry("4", new BinaryTreeSetup("8","9",4)),
                Map.entry("5", new BinaryTreeSetup("10","1-2",5)),
                Map.entry("6", new BinaryTreeSetup("1-3","1-4",6)),
                Map.entry("7", new BinaryTreeSetup(null,null,7)),
                Map.entry("8", new BinaryTreeSetup(null,null,8)),
                Map.entry("9", new BinaryTreeSetup(   null,null,9)),
                Map.entry("10", new BinaryTreeSetup(null,null,10)),
                Map.entry("1-2", new BinaryTreeSetup(null,null,1)),
                Map.entry("1-3", new BinaryTreeSetup(   null,null,1)),
                Map.entry("1-4", new BinaryTreeSetup(null,null,1))
        );
        BranchSum.BinaryTree root = build("root", nodesSetup);

        // When
        final List<Integer> actualSums = BranchSum.branchSums(root);

        // Then
        assertThat(actualSums).containsExactly(15, 16, 18, 9, 11, 11, 11);
    }

}
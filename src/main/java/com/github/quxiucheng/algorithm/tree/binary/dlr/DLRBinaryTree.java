package com.github.quxiucheng.algorithm.tree.binary.dlr;

import com.github.quxiucheng.algorithm.tree.binary.TreeNode;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author quxiucheng
 * @date 2019-04-22 10:00:00
 */
public class DLRBinaryTree {

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    /**
     * 输入: [1,null,2,3]
     * 1
     *      2
     *  3
     * 输出: [1,2,3]
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream("[1,null,2,3]".getBytes())));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            List<Integer> ret = new Solution().preorderTraversal(root);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        result.addAll(recursive(root));
        return result;
    }

    // 根左右
    public List<Integer> noRecursive1(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root!=null||!stack.empty()){
            // 遍历左子树
            while(root!=null){
                // 根
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            // 设置为左子树的右子树
            if(!stack.isEmpty()){
                root = stack.pop();
                root = root.right;
            }
        }
        return result;
    }
    public List<Integer> noRecursive2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);
        while(!stack.empty()){
            TreeNode node = stack.pop();
            // 遍历左子树
            while(node!=null){
                // 根
                result.add(node.val);
                stack.push(node);
                node = node.left;
            }
            // 设置为左子树的右子树
            if(!stack.isEmpty()){
                node = stack.pop();
                node = node.right;
                stack.push(node);
            }
        }
        return result;
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> recursive(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root==null){
            return result;
        }
        result.add(root.val);
        result.addAll(recursive(root.left));
        result.addAll(recursive(root.right));
        return result;
    }
}


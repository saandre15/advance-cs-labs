import java.util.Stack;
import java.util.stream.IntStream;

public class MyBST {
    
    private class BSTNode {
        Integer val;
        BSTNode left, right;

        public BSTNode(Integer val) {
            this.val = val;
            left = right = null;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
        
        private boolean contains(Integer i) {
            if(i < val && left != null) return left.contains(i);
            else if (i > val && right != null) return right.contains(i);
            else if (i == val) return true;
            return false;
        }

        private boolean insert(Integer n) {
            if(n == val) return false;
            else if (left == null && n < val) left = new BSTNode(n);
            else if (right == null && n > val) right  = new BSTNode(n);
            else if (n < val) left.insert(n);
            else if (n > val) right.insert(n);
            return true;
        }

        private boolean delete(Integer n) {
            if(!contains(n)) return false;
            if(left != null && left.val == n) left = deleteNode(left);
            else if (right != null && right.val == n) right = deleteNode(right);
            else if (n < val && left != null) return left.delete(n);
            else if (n > val && right != null) return right.delete(n);
            return false;
        }

        /**
         * Deletes the node
         * @param node Binary Tree Node
         * @return replacement node
         */
        private BSTNode deleteNode(BSTNode node) {
            if(node.isLeaf()) return null;
            else if(node.left != null && node.right != null) {
                int max = node.left.getMax();
                BSTNode n = new BSTNode(max);
                n.left = node.left;
                n.right = node.right;
                n.left.delete(max);
                return n;
            }
            else return node.left != null ? node.left : node.right;
        } 

        private Integer getMax() {
            if(right == null) return val;
            return right.getMax();
        }


        private Integer getMin() {
            if(left == null) return val;
            return left.getMin();
        }

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private void print(int level) {
            if(right != null) right.print(level + 1);
            if(level == 0) System.out.println(val);
            else System.out.println(IntStream.range(0, level)
                .mapToObj(index -> "    ")
                .reduce((total, cur) -> total + cur)
                .orElseThrow(RuntimeException::new) + val);
    
            if(left != null) left.print(level + 1);
        }

    }

    private BSTNode root;
    private int size;

    public int size() {
        return size;
    }

    public void insert(Integer n) {
        if(root == null) {
            root = new BSTNode(n);
            size++;
        }
        else if(root.insert(n)) size++;
    }

    public void delete(Integer n) {
        if(size == 0) return;
        if(root.val == n) {
            root = null; size = 0;
        } 
        else if (root.delete(n)) size--;
    }

    public boolean contains(Integer n) {
        return root.contains(n);
    }

    public Integer getMax() {
        return root.getMax();
    }

    public Integer getMin() {
        return root.getMin();
    }

    public void inOrder() {
        if(size == 0) return;
        inOrder(root);
    }

    public void inOrder(BSTNode node) {
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node + " ");
        inOrder(node.right);
    }

    public void print() {
        if(root == null) return;
        root.print(0);
    }

    
}

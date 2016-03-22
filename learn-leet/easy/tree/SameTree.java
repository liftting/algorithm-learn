package easy.tree;

/**
 * Created by wm on 16/2/24.
 */
public class SameTree {
    /**
     *
     * [0] [1]
     *
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        //这里要判断false情况，
        if (p.val != q.val) return false;

        if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) return true;

        return false;

    }

}

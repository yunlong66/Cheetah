### 二叉树的最大路径和

题目描述：

>给定一个非空二叉树，返回其最大路径和。
>
>本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
>
>示例 1:
>
>输入: [1,2,3]
>
>       1
>      / \
>     2   3
>
>输出: 6
>示例 2:
>
>输入: [-10,9,20,null,null,15,7]
>
> -10
>   / \
>  9  20
>    /  \
>   15   7
>
>输出: 42
>

思路：这题还是不太明白路径到底是怎么定义的，自己想了一种方法很复杂，但是官方题解几行递归就搞定了，还没搞清楚。

我的解法：

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

//递归遍历
void findMax(struct TreeNode* root, int* max){
    if(root == NULL)
        return NULL;
    int k1 = maxSum2(root);
    //printf("%d", k);
    if(*max < k1)
        *max = k1;
    findMax(root -> left, max);
    findMax(root -> right, max);
}

//包含当前节点的最大路径和（单线）
int maxSum(struct TreeNode* root){
    if(root == NULL)
        return 0;
    if(root -> left == NULL && root -> right == NULL)
         return root -> val;
    
    if(root -> left == NULL){
        int m = maxSum(root -> right);
        return (m > 0? m:0)+ root -> val;
    }
    else if(root -> right == NULL ){
        int m2 = maxSum(root -> left);
        return (m2 > 0?m2:0) + root -> val;
    }
        
    //左右子树最大路径和如果都小于0
    if(maxSum(root -> left) < 0 && maxSum(root -> right) < 0)
        return root -> val;
    //挑一个大的
    if(maxSum(root -> left) > maxSum(root -> right))
        return maxSum(root -> left) + root -> val;
    else
        return maxSum(root -> right) + root -> val;
}

//包含当前节点的最大路径和（双线）
int maxSum2(struct TreeNode* root){
    if(root == NULL)
        return 0;
    if(root -> left == NULL || root -> right == NULL){
        return maxSum(root);
    }
    int a = maxSum(root -> left);
    int b = maxSum(root -> right);
    int s = a + b + root -> val;
    
    // int k = maxSum(root);
    int k;
    if(a < 0 && b < 0)
        k = root -> val;
    else if((a + root -> val) > (b + root -> val))
        k = a + root -> val;
    else
        k = b + root -> val;
    //printf("s = %d", s);
    //printf("k = %d/n", k);
    if(s > k)
        return s;
    else
        return k;
}

int maxPathSum(struct TreeNode* root){
    if(root -> left == NULL && root -> right == NULL)
        return root -> val;
    int* max = (int*)malloc(sizeof(int));
    *max == root -> val;
    findMax(root, max);
    return *max;
}
```

官方解法：

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int max_sum = Integer.MIN_VALUE;

  public int max_gain(TreeNode node) {
    if (node == null) return 0;

    // max sum on the left and right sub-trees of node
    int left_gain = Math.max(max_gain(node.left), 0);
    int right_gain = Math.max(max_gain(node.right), 0);

    // the price to start a new path where `node` is a highest node
    int price_newpath = node.val + left_gain + right_gain;

    // update max_sum if it's better to start a new path
    max_sum = Math.max(max_sum, price_newpath);

    // for recursion :
    // return the max gain if continue the same path
    return node.val + Math.max(left_gain, right_gain);
  }

  public int maxPathSum(TreeNode root) {
    max_gain(root);
    return max_sum;
  }
}
```


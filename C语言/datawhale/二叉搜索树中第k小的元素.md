### 二叉搜索树中第k小的元素

题目描述：

> 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
>
> 说明：
> 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
>
> 示例 1:
>
> 输入: root = [3,1,4,null,2], k = 1
>    3
>   / \
>  1   4
>   \
>    2
> 输出: 1
>
> **进阶：**
> 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 `kthSmallest` 函数？

大致思路：把这个复杂问题，分成几个简单的问题和步骤解决。创建count函数计算出当前节点下一共有多少个节点，那么count(root -> left)+1就代表当前节点在整个树中的位置(按大小排序)。这样就可以计算出当前节点是第x个，如果x=k,就代表找到了，如果x>k，就去找当前节点左子树的第k个。如果x<k，就去找当前节点右子树的第k-x个。

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

void count(struct TreeNode* root, int* num){
    if(root == NULL)
        return;
    count(root -> left, num);
    *num = *num + 1;
    count(root -> right, num);
}

void ks(struct TreeNode* root, int k, int* res){
    if(root == NULL)
        return;
    int* num;
    num = (int*)malloc(sizeof(int));
    int c;
    *num = 0;
    count(root -> left, num);
    c = *num + 1;
    if(c == k){
        *res = root -> val;
    }else if(c < k){
        ks(root -> right, k - c, res);
    }else{
        ks(root -> left, k, res);
    }
    return 0;
}

int kthSmallest(struct TreeNode* root, int k){
    int* res;
    res = (int*)malloc(sizeof(int));
    ks(root, k, res);
    return *res;
}
```


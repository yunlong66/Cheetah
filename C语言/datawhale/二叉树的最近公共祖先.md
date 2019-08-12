### 二叉树的最近公共祖先

题目描述：

> 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
>
> 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
>
> 示例 1:
>
> 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
> 输出: 3
> 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
>
> **说明:**
>
> - 所有节点的值都是唯一的。
> - p、q 为不同节点且均存在于给定的二叉树中。

思路：最近总是先想到递归，把大问题分解成小问题，分步解决。void inTree(struct TreeNode* root, struct TreeNode* p, int* isinTree)函数判断p节点是否在二叉树root中，isinTree是保存结果用的（总感觉这样很别扭）。void dg()函数进行递归，判断p,q在左子树还是右子树，然后做下一步操作，到这逻辑就比较清晰了。但是程序的时间和空间复杂度，排名都在95%之后，说明这不是个好方法，有待继续研究。

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
void dg(struct TreeNode* root, struct TreeNode* p, struct TreeNode* q, struct TreeNode** zx){
    if(root == NULL)
        return;
    if(root -> val == p-> val || root -> val == q -> val){
        *zx = root;
        return;
    }
        
    int* isinTree1;
    int* isinTree2;
    isinTree1 = (int*)malloc(sizeof(int));
    isinTree2 = (int*)malloc(sizeof(int));
    *isinTree2 = 0;
    *isinTree1 = 0;
    inTree(root -> left, p, isinTree1);
    inTree(root -> left, q, isinTree2);
    if(*isinTree1 == 1 && *isinTree2 == 1){
        dg(root -> left, p ,q, zx);
    }
    else if(*isinTree1 == 0 && *isinTree2 == 0){
        dg(root -> right, p ,q, zx);
    }
    else{
        *zx = root;
        return;
    }
        
}

void inTree(struct TreeNode* root, struct TreeNode* p, int* isinTree){
    if(root == NULL)
        return;
    if(root -> val == p -> val)
        *isinTree = 1;
    inTree(root -> left, p, isinTree);
    inTree(root -> right, p, isinTree);
}

struct TreeNode* lowestCommonAncestor(struct TreeNode* root, struct TreeNode* p, struct TreeNode* q) {
    struct TreeNode** zx ;
    zx = (struct TreeNode**)malloc(sizeof(struct TreeNode*));
    dg(root, p, q, zx);
    return *zx;
}
```


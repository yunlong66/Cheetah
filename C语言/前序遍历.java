//前序遍历的递归实现与非递归实现
import java.util.Stack;
public class Test 
{
	public static void main(String[] args)
	{
		TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
		for(int i = 0; i < 10; i++)
		{
			node[i] = new TreeNode(i);
		}
		for(int i = 0; i < 10; i++)
		{
			if(i*2+1 < 10)
				node[i].left = node[i*2+1];
			if(i*2+2 < 10)
				node[i].right = node[i*2+2];
		}
		
		preOrderRe(node[0]);
	}
	
	public static void preOrderRe(TreeNode biTree)
	{//递归实现
		System.out.println(biTree.value);
		TreeNode leftTree = biTree.left;
		if(leftTree != null)
		{
			preOrderRe(leftTree);
		}
		TreeNode rightTree = biTree.right;
		if(rightTree != null)
		{
			preOrderRe(rightTree);
		}
	} 
	
	public static void preOrder(TreeNode biTree)
	{//非递归实现
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(biTree != null || !stack.isEmpty())
		{
			while(biTree != null)
			{
				System.out.println(biTree.value);
				stack.push(biTree);
				biTree = biTree.left;
			}
			if(!stack.isEmpty())
			{
				biTree = stack.pop();
				biTree = biTree.right;
			}
		}

		// whlie(biTree != null){
		// 	System.out.println(biTree.value);
		// 	stack.push(biTree);
		// 	biTree = biTree.left;
		// 	if (biTree == null) {
		// 		biTree = stack.pop();
		// 		biTree = biTree.right;
		// 	}
		// }
	}
}
 
class TreeNode//节点结构
{
	int value;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int value)
	{
		this.value = value;
	}
}
 
 
 
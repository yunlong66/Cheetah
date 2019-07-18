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
		
		midOrderRe(node[0]);
		System.out.println();
		midOrder(node[0]);
	}
	
	public static void midOrderRe(TreeNode biTree)
	{//中序遍历递归实现
		if(biTree == null)
			return;
		else
		{
			midOrderRe(biTree.left);
			System.out.println(biTree.value);
			midOrderRe(biTree.right);
		}
	}
	
	
	public static void midOrder(TreeNode biTree)
	{//中序遍历费递归实现
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(biTree != null || !stack.isEmpty())
		{
			while(biTree != null)
			{
				stack.push(biTree);
				biTree = biTree.left;
			}
			if(!stack.isEmpty())
			{
				biTree = stack.pop();
				System.out.println(biTree.value);
				biTree = biTree.right;
			}
		}
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








public static void zx(TreeNode biTree){
	if (biTree.left != null) {
			zx(biTree.left);
	}
	System.out.println(biTree.value);
	if (biTree.right != null) {
			zx(biTree.right);
	}

	if (biTree == null) {
			return;
	}
	else{
		zx(biTree.left);
		System.out.println(biTree.value);
		zx(biTree.right);
	}


} 

 
 
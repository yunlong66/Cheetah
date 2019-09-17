### 不同路径

题目描述：

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

说明：m 和 n 的值均不超过 100。

```
示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。

1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```

思路：一开始想着直接递归，然后发现时间复杂度有点高。中间还走了点弯路，但也发现了个知识盲区，就是二级指针和二维数组不能直接转换。

```c
int uniquePaths(int m, int n){
    //这种简单粗暴的递归，时间复杂度比较高，同样的思路用空间换时间
    // if(m == 1 || n == 1)
    //     return 1;
    // return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    int map[m][n];
    for(int i = 0; i < m; i++)
        map[i][0] = 1;
    for(int i = 0; i < n; i++)
        map[0][i] = 1;
    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            map[i][j] = map[i-1][j] + map[i][j-1];
        }
    }
    return map[m-1][n-1];
}
```

也可以用二级指针动态申请数组（就当复习指针知识了）

```c
int uniquePaths(int m, int n){
    int **map;
    map = (int**)malloc(sizeof(int*)*m);
  	//注意这一步分配内存步骤不能忘
    for(int i = 0; i < m; i++){
        *(map+i) = (int*)malloc(sizeof(int)*n);
    }
    for(int i = 0; i < m; i++)
        *(*(map+i)+0) = 1;
    for(int i = 0; i < n; i++)
        *(*(map+0)+i) = 1;
    for(int i = 1; i < m; i++){
        for(int j = 1; j < n; j++){
            *(*(map+i)+j) = *(*(map+i-1)+j) + *(*(map+i)+j-1);
        }
    }
    return *(*(map+m-1)+n-1);
}
```


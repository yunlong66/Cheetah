###螺旋矩阵||

题目描述：

给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

```c
示例:

输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
```

思路：分解成一圈一圈赋值的动作。

Java版：

```java
class Solution {
    int k;
    int rSize;
    int col = 0;
    public int[][] generateMatrix(int n) {
        k = 0;
        rSize = n;
        int[][] a = new int[n][n];
        while(k != n*n){
            quan(a);    
        }
        return a;
    }
    public void quan(int[][] r){
        if(k == rSize*rSize - 1 && (rSize%2 == 1)){
            r[rSize/2][rSize/2] = ++k;
            return;
        }
        //开始值为 k + 1
        //开始的横纵坐标为 x = k%rSize - 1
        //圈圈边长为 l = rSize - 2*x
        //printf("k=%d", k);
        System.out.print((k+1)%rSize);
        int x = col++;//printf("x=%d", x);
        int l = rSize - 2*x;//printf("l=%d", l);
        //向右
        for(int i = 0; i < l; i++)
            r[x][x + i] = ++k;
        //向下
        for(int j = 1; j < l; j++)
            r[x+j][x+l-1] = ++k;
        //向左
        for(int i = 1; i< l; i++)
            r[x+l-1][x+l-1-i] = ++k;
        //向上
        for(int j = 1; j< l - 1; j++)
            r[x+l-1-j][x] = ++k;
    //printf("k=%d", k);
    }
}
```

C语言版（不知道为什么 leetcode 输出总是空的，但是能打印出来值）：

```c
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
void quan(int** r);

int k;
int rSize;
int col = 0;

int** generateMatrix(int n, int* returnSize, int** returnColumnSizes){
    printf("%d", *returnSize);
    k = 0;
    rSize = n;
    returnColumnSizes = (int**) malloc(sizeof(int*)*n);
    for(int i = 0; i < n; i++)
        returnColumnSizes[i] = (int*)malloc(sizeof(int)*n);
    
    //*(*(returnColumnSizes + 1) + 1) = 2;
//     *(*(returnColumnSizes+1)+1) = 1;
        
//     return returnColumnSizes;
    if(n == 1){
        returnColumnSizes[0][0] = 1;
        return returnColumnSizes;
    }
    while(k != n*n){
        //printf("%d", rSize);
        quan(returnColumnSizes);
    }
    //returnColumnSizes[1][1] = 2;
    //*(*(returnColumnSizes + 1) + 1) = 2;
     for (int i = 0; i < 3; i++ )
    {
        for (int j = 0; j < 3; j++ )
        {
            printf( "%4d ", returnColumnSizes[i][j] );
        }
        printf( "\n" );
    }    
    
    return returnColumnSizes;
    
}

//设计一个从头到尾转一圈赋值的函数
void quan(int** r){
    if(k == rSize*rSize - 1 && (rSize%2 == 1)){
        r[rSize/2][rSize/2] = ++k;
        return;
    }
    //开始值为 k + 1
    //开始的横纵坐标为 x = k%rSize - 1
    //圈圈边长为 l = rSize - 2*x
    //printf("k=%d", k);
    int x = col++;//printf("x=%d", x);
    int l = rSize - 2*x;//printf("l=%d", l);
    //向右
    for(int i = 0; i < l; i++)
            r[x][x + i] = ++k;
    //向下
    for(int j = 1; j < l; j++)
        r[x+j][x+l-1] = ++k;
    //向左
    for(int i = 1; i< l; i++)
        r[x+l-1][x+l-1-i] = ++k;
    //向上
    for(int j = 1; j< l - 1; j++)
        r[x+l-1-j][x] = ++k;
    //printf("k=%d", k);
}
```


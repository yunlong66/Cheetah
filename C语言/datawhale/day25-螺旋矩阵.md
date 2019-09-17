### 螺旋矩阵

题目描述：

给定一个包含 *m* x *n* 个元素的矩阵（*m* 行, *n* 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

```c
示例 1:
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]

示例 2:
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```

思路：用0，1，2，3分别表示四种前进的状态（正在向右，要向下转/正在向下，要向左转/正在向左，要向上转/正在向上，要向右转），用一个boolean数组记录是否已经访问过，再写一个判断是否在范围内的函数。如果当前状态下的下一个位置，没被访问过而且在范围内，就继续走，否则，转换状态。

```java
class Solution {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        //n行，m列
        int n = matrix.length;
        if(n == 0)
            return ans;
        int m = matrix[0].length;
        int x = 0;
        int y = 0;
        //0代表正在向右走要向下转，1代表正在向下走要向左转，2代表正在向左走要向上转，3代表正在向上走要向右转。0123循环
        int dir = 0;
        boolean[][] seen = new boolean[n][m];
        while(isin(x, y, m, n) && seen[x][y] == false){
            seen[x][y] = true;
            ans.add(matrix[x][y]);
          //判断当前状态，并判断下一步是继续还是转向
            if(dir == 0){
                if(isin(x, y + 1, m, n) && seen[x][y + 1] == false)
                    y = y + 1;
                else{
                    dir = 1;
                    x = x + 1;
                }
            }else if(dir == 1){
                if(isin(x + 1, y, m, n) && seen[x + 1][y] == false)
                    x = x + 1;
                else{
                    dir = 2;
                    y = y - 1;
                }
            }else if(dir == 2){
                if(isin(x, y - 1, m, n) && seen[x][y - 1] == false)
                    y = y - 1;
                else{
                    dir = 3;
                    x = x - 1;
                }
            }
            else{
                if(isin(x - 1, y, m, n) && seen[x - 1][y] == false)
                    x = x - 1;
                else{
                    dir = 0;
                    y = y + 1;
                }
            }
        }
        
        return ans;
        
    }
    
    public boolean isin(int x, int y, int m, int n){
        if((y < m && x < n) && (x > -1 && y > -1))
            return true;
        else
            return false;
    }
}
```


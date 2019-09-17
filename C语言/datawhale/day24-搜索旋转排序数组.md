### 搜索旋转排序数组

题目描述：

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

```c
示例 1:
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4

示例 2:
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```

思路：看到logn就想到二分法，但是要先根据旋转排序数组的特性，找出一段target所在的正常排序的数组中。不容易啊，终于写出来了

代码：

```c
int search(int* nums, int numsSize, int target){
    if(numsSize == 0)
        return -1;
    if(numsSize == 1){
        if(nums[0] == target)
            return 0;
        else
            return -1;
    }
    if(numsSize == 2){
        if(nums[0] == target)
            return 0;
        else if(nums[1] == target)
            return 1;
        else 
            return -1;
    }
    int left = 0;
    int right = numsSize - 1;
    //第一次二分查找，找到分割点
    while(left < right){
        int oldleft = left;
        int oldright = right;
        int n = (left + right)/2;
        if(nums[n] == target)
            return n;
        else if(nums[left] == target)
            return left;
        else if(nums[right] == target)
            return right;
        //如果在target在右边排序好的
        if(target < nums[left]){
            //如果nums[n]在右边
            if(nums[n] < nums[left]){
                //target在n和right中间
                if(nums[n] < target){
                    left = n;
                    //此时target在left和right之间正常排序
                    break;
                }
                //target在n左边
                else{
                    right = n;
                }
            }else{
                left = n;
            }
        }
        //如果在target在左边排序好的
        else if(target > nums[right]){
            //如果也n在左边排序好的
            if(nums[n] > nums[left]){
                if(nums[n] > target){
                    right = n;
                    break;
                }else{
                    left = n;
                }
            }
            //如果n在右边排序好的
            else{
                right = n;
            }
        }
        
      //如果left和right相邻了，可以直接判断。（根据提交出错加的）
        if(left == right - 1 ){
            if(nums[left] != target && nums[right] != target)
                return -1;
            else if(nums[left] == target)
                return left;
            else
                return right;
        }
        
      //如果left和right没发生变化，就可以跳出循环了
        if(oldleft == left && oldright == right)
            break;
        
    }
   
    //正常的二分排序。现在target在left和right之间，且其是正常排序的
    while(left < right){
        int n = (left + right)/2;
        if(target < nums[n]){
            right = n;
        }else if(target > nums[n]){
            left = n;
        }else{
            return n;
        }
        if(left == right - 1 ){
            if(nums[left] != target && nums[right] != target)
                return -1;
            else if(nums[left] == target)
                return left;
            else
                return right;
        }
            
    }
    return -1;
}
```


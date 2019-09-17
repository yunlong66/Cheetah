# 最接近的三数之和

题目描述：

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

```c
例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
```

思路：先排序（用的插入排序），然后双指针向target靠拢

```c
#include<stdlib.h>

void insertSort(int* nums, int numsSize);

int threeSumClosest(int* nums, int numsSize, int target){
    //插入排序
    insertSort(nums, numsSize);

    //双指针收缩
    int sum = nums[0] + nums[1] + nums[2];
    int cha = abs(sum - target);;
    for(int i = 0; i < numsSize - 2; i++){
        int left = i + 1;
        int right = numsSize - 1;
        while(left != right){
            int s = nums[i] + nums[left] + nums[right];
            
            if(abs(s - target) < cha){
                sum = s;
                cha = abs(s - target);
            } 
            if(s < target)
                left++;
            else if(s > target)
                right--;
            else
                return target;
        }
    }
    return sum;
}

void insertSort(int* nums, int numsSize){
    if(numsSize <= 1)
        return;
    for(int i = 1; i < numsSize; i++){
        int val = nums[i];
        int j = i - 1;
        for(; j >= 0; j--){
            if(val < nums[j])
                nums[j+1] = nums[j];
            else
                break;
        }
        nums[j+1] = val;
    }
}
```


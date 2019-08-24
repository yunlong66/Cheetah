##除自身以外数组的乘积

题目描述：

给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

```
示例:

输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
```

思路：遍历求一遍左积，再遍历求一遍右积，然后相乘

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* productExceptSelf(int* nums, int numsSize, int* returnSize){
    *returnSize = numsSize;
    int* r1 = (int*) malloc(sizeof(int)*numsSize);
    int* r2 = (int*) malloc(sizeof(int)*numsSize);
    int s = 1;
    //遍历一遍求左积
    for(int i = 0; i < numsSize; i++){
        if(i == 0){
            r1[i] = 1;
            continue;
        }
        s = s*nums[i-1];
        r1[i] = s;
    }
    //遍历一遍求右积，顺便求出来终值
    int k = 1;
    for(int j = numsSize - 1; j >= 0; j--){
        if(j == numsSize - 1){
            r2[j] = 1;
            r2[j] = r1[j]*r2[j];
            continue;
        }
        k = k*nums[j+1];
        r2[j] = k;
        r2[j] = r1[j]*r2[j];
    }
  return r2;
}
```

空间优化版：

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* productExceptSelf(int* nums, int numsSize, int* returnSize){
    *returnSize = numsSize;
    int* r1 = (int*) malloc(sizeof(int)*numsSize);
    int s = 1;
    //遍历一遍求左积
    for(int i = 0; i < numsSize; i++){
        if(i == 0){
            r1[i] = 1;
            continue;
        }
        s = s*nums[i-1];
        r1[i] = s;
    }
    //遍历一遍求右积，顺便求出来终值
    int k = 1;
    for(int j = numsSize - 1; j >= 0; j--){
        if(j == numsSize - 1){
            continue;
        }
        k = k*nums[j+1];
        r1[j] = r1[j]*k;
    }
  return r1;
}
```


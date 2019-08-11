### 求众数

题目描述：

> 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
>
> 你可以假设数组是非空的，并且给定的数组总是存在众数。
>
> 示例 1:
>
> 输入: [3,2,3]
> 输出: 3
> 示例 2:
>
> 输入: [2,2,1,1,1,2,2]
> 输出: 2

```c
int majorityElement(int* nums, int numsSize){
    int k;
    int *nums2;
    nums2 = (int*)malloc(sizeof(int)*numsSize);
    for(int i = 0; i < numsSize; i++){
        if(*(nums2+i) == 1){
            continue;
        }
        
        k = 0;
        for(int j = 0; j < numsSize; j++){
            if(*(nums+j) == *(nums+i)){
                k++;
                *(nums2+j) = 1;
            }
        }
        if(k > numsSize/2){
            return *(nums+i);
        }
    }
    return 0;
}
```
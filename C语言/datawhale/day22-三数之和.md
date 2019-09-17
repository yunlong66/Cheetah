###三数之和

题目描述：

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

```c
例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

思路：先升序排序，然后i从0～nums.length-2，每次固定一个nums[i]，在右边两端分别设置left和right指针，如果nums[i] > 0,直接跳到下一个i。如果sum = nums[i]+nums[left]+nums[right] > 0，右指针向左，如果sum = nums[i]+nums[left]+nums[right] < 0，左指针向右，找到sum为零的三个数后，添加到结果中。注意要去掉一些重复的（nums[i]重复，nums[left]或nums[right]重复）

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) 
            return ans;
      	//排序
        Arrays.sort(nums);
      	//每次固定一个数
        for(int i = 0; i < len - 2; i++){
            int left = i + 1;
            int right = len - 1;
          	//这后面都大于零，可以直接退出遍历了
            if(nums[i] > 0){
                break;
            }
          	//如果当前的nums[i]和上一个值相同，就需要跳过它
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0){
                    right = right - 1;
                }else if(sum < 0){
                    left = left + 1;
                }else{
                    List tem = new ArrayList();
                    tem.add(nums[i]);
                    tem.add(nums[left]);
                    tem.add(nums[right]);
                    ans.add(tem);
                  	//找到一组符合条件的也要把和这组方案相同的重复数字跳过
                    while(left < right && nums[left] == nums[left + 1]) 
                        left++;
                    while(left < right && nums[right] == nums[right - 1]) 
                        right--;
                    left++;
                    right--;
                }
            }
        }
        return ans;
    }
}
```
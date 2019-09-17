###旋转链表

题目描述：

给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

```c
示例 1:
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

示例 2:
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

思路：先把链表连成环，找到分界点，分开即可。

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode* rotateRight(struct ListNode* head, int k){
    int len = 1;
    struct ListNode* h = head;
    
    if(head == NULL)
        return NULL;
    //h定位到链表尾节点
    while(h -> next != NULL){
        len++;
        h = h -> next;
    }
    //k取余
    k = k%len;
    //现在才能得到真正的 len 和 k
    if(k == 0 || len == 1)
        return head;
    //链表成环，找到分界点
    h -> next = head;
    int step = len - k;
    struct ListNode* p = h;
    //定位到分界点
    while(step > 0){
        p = p -> next;
        step--;
    }
    //从分界点分割环链表
    struct ListNode* newHead = p -> next;
    p -> next = NULL;
    h -> next = head;
    return newHead;
}
```




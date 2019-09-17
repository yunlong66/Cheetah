## 合并两个有序链表

题目描述：

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

代码：

```c
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */


struct ListNode* mergeTwoLists(struct ListNode* l1, struct ListNode* l2){
    struct ListNode* head;
    struct ListNode* l;
    struct ListNode* s;
    int k = 0;
    
    //处理特殊情况
    if(l1 == NULL){
        return l2;
    }else if (l2 == NULL){
        return l1;
    }
    
    //把较长的链表作为返回的结果
    if(sizeof(l1) > sizeof(l2)){
        l = l1;
        s = l2;
        head = l;
    }else{
        if(l1 -> val <= l2 -> val){
            k = 1;
        }
        l = l2;
        s = l1;
        head = l;
    }
    
    //遍历short
    while(s != NULL){
        if(s -> val < l -> val){
            //直接插到前面
            struct ListNode* q = s;
            s = s -> next;
            q -> next = l;
            l = q;
            if(k == 1){
                head = l;
            }
        }else{
            k = 0;
            //如果链表已经到最后了
            if(l -> next == NULL){
                l -> next = s;
                return head;
            }
            
            if(s -> val > l -> next -> val){
                l = l -> next;
            }else{
                //插在l的下一个结点   
                struct ListNode* p = s;
                s = s -> next;
                p -> next = l -> next;
                l -> next = p;
                l = l -> next;
            }
        }
    }
    return head;
}
```




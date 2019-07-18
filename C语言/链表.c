typedef struct Link{
	char elem;
	struct Link * next; 
}link;

//不含头节点初始化
link* initLink(){
	link * p=NULL;//创建头指针
    link * temp = (link*)malloc(sizeof(link));//创建首元节点
    //首元节点先初始化
    temp->elem = 1;
    temp->next = NULL;
    p = temp;//头指针指向首元节点
    //从第二个节点开始创建
    for (int i=2; i<5; i++) {
     //创建一个新节点并初始化
        link *a=(link*)malloc(sizeof(link));
        a->elem=i;
        a->next=NULL;
        //将temp节点与新建立的a节点建立逻辑关系
        temp->next=a;
        //指针temp每次都指向新链表的最后一个节点，其实就是 a节点，这里写temp=a也对
        temp=temp->next;
    }
    //返回建立的节点，只返回头指针 p即可，通过头指针即可找到整个链表
    return p;
}

//含头节点初始化
link * initLink2(){
    link * p=(link*)malloc(sizeof(link));//创建一个头结点
    link * temp=p;//声明一个指针指向头结点，
    //生成链表
    for (int i=1; i<5; i++) {
        link *a=(link*)malloc(sizeof(link));
        a->elem=i;
        a->next=NULL;
        temp->next=a;
        temp=temp->next;
    }
    return p;
}
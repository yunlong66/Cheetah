#include<stdio.h>
#include<stdlib.h>
#define Size 5

typedef struct Table{
	int * head;
	int length;
	int size;
}table;

//初始化
table initTable(){
	table t;
	t.head = (int*)malloc(Size*sizeof(int));
	if (!t.head) //如果申请失败，作出提示并直接退出程序
    {
        printf("初始化失败");
        exit(0);
    }

    t.length = 0;
    t.size = Size;
    return t;
}	

//输出顺序表中元素
void displayTable(Table t){
	for (int i = 0; i < t.length; ++i)
	{
		printf("%d", head[i]);
	}
}

int main(){
	Table t = initTable();
	for (int i = 0; i < Size; ++i)
	{
		t.head[i] = i;
		t.length++;
	}
	displayTable(t);
}
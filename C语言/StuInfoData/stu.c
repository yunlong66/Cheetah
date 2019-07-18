#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <conio.h>
#include "common.h"
#include "stu.h"

//��ʼ��
void init(){
	STU stu;
	NODE *preNode = NULL, *thisNode = NULL;

	//���ļ�
	if( (fp=fopen(FILENAME, "rb+")) == NULL && (fp=fopen(FILENAME, "wb+")) == NULL ){
		pause("Error on open %s file!", FILENAME);
		exit(EXIT_FAILURE);
	}

	if(getFileSize(fp)){  //����ļ��������ݣ���ô��������
		while(fread(&stu, 1, sizeof(STU), fp)){
			thisNode = (NODE*)malloc(sizeof(NODE));
			thisNode->data = stu;
			thisNode->next = NULL;
			if(preNode==NULL){  //�����һ���ڵ�
				header.link = thisNode;
			}else{  //���ǵ�һ���ڵ�
				preNode->next = thisNode;
			}
			preNode = thisNode;
			header.num++;
		}
	}else{  //����ļ���û�����ݣ���ô����Ϊ��
		header.num = 0;
		header.link = NULL;
	}
}

//��ʾ����ѧ����Ϣ
void showAllStu(){
	STU thisStu;
	NODE *thisNode;
	float mathTotal = 0, cnTotal = 0, enTotal = 0, ageTotal = 0, oneTotal = 0, allTotal = 0;
	int manTotal = 0, womanTotal = 0, n = header.num;

	if(!header.num){
		pause("��δ����κ�ѧ����Ϣ�������������...");
		return;
	}
	system("cls");
	printf("-----------------------------------------------------------------------\n");
	printf("  ѧ��  |  ����  |  �Ա�  |  ����  |  ��ѧ  |  ����  |  Ӣ��  | �ܳɼ�\n");
	printf("--------+--------+--------+--------+--------+--------+--------+--------\n");

	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		thisStu = thisNode->data;
		oneTotal = thisStu.math + thisStu.cn + thisStu.en;
		allTotal += oneTotal;
		mathTotal += thisStu.math;
		cnTotal += thisStu.cn;
		enTotal += thisStu.en;
		ageTotal += thisStu.age;
		if(strcmp(thisStu.sex, "��")){
			womanTotal++;
		}else{
			manTotal++;
		}
		printf("   %.2d   | %-6s |   %-3s  |   %-3d  | %-6.2f | %-6.2f | %-6.2f | %-6.2f\n", thisStu.id, thisStu.name, thisStu.sex, thisStu.age, thisStu.math, thisStu.cn, thisStu.en, oneTotal);
	}
	printf("--------+--------+--------+--------+--------+--------+--------+--------\n");
	printf("   --   |   --   | %2d/%-2d  | %-6.2f | %-6.2f | %-6.2f | %-6.2f | %-6.2f\n", manTotal, womanTotal, ageTotal/n, mathTotal/n, cnTotal/n, enTotal/n, allTotal/n);
	printf("-----------------------------------------------------------------------\n");
	pause("\n����%d��ѧ����Ϣ�������������...", n);
}

//���ѧ����Ϣ
void addStuInfo(){
	STU stu;
	int id, isStuExist = 0;
	NODE *thisNode, *pnode = (NODE*)malloc(sizeof(NODE));
	getStuID(&stu.id);
	id = stu.id;

	if(header.num == 0){  //�������Ϊ�գ�ֱ�Ӳ���
		header.link = pnode;
		pnode->next = NULL;
	}else{  //�������Ϊ�գ���Ҫ��������ȷ������λ��
		for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
			if(thisNode->data.id == id){  //��ѧ����Ϣ����
				free(pnode);
				isStuExist = 1;
			}

			if(thisNode==header.link && thisNode->data.id>id){  //�����Ҫ������ͷ���루Ҳ������ͷ���͵�һ���ڵ�֮����룩
				header.link = pnode;
				pnode->next = thisNode;
				break;
			}else if(thisNode->next==NULL && thisNode->data.id<id){  //�����Ҫ������ĩβ����
				thisNode->next = pnode;
				pnode->next = NULL;
				break;
			}else if(thisNode->data.id<id && thisNode->next->data.id>id ){  //�������м����
				pnode->next = thisNode->next;
				thisNode->next = pnode;
				break;
			}
		}
	}

	if(isStuExist){  //��ѧ������
		pause("���󣺸�ѧ����Ϣ�Ѵ��ڣ������ظ���ӣ������������...");
	}else{  //��ѧ��������
		getStuName(stu.name);
		getStuSex(stu.sex);
		getStuAge(&stu.age);
		getStuMath(&stu.math);
		getStuCN(&stu.cn);
		getStuEN(&stu.en);
		pnode->data = stu;
		updateFile();
		header.num++;
		pause("��ʾ����ӳɹ��������������...");
	}
}

//ɾ��ѧ����Ϣ
void deleteStuInfo(){
	STU stu;
	NODE *thisNode, *preNode;
	getStuID(&stu.id);

	for(thisNode=header.link, preNode=NULL; thisNode!=NULL; preNode=thisNode, thisNode=thisNode->next){
		if(thisNode->data.id == stu.id){
			if(preNode == NULL){  //����ǵ�һ���ڵ�
				header.link = thisNode->next;
			}else{  //������ǵ�һ���ڵ�
				preNode->next = thisNode->next;
			}
			free(thisNode);
			updateFile();
			header.num--;
			pause("��ʾ��ɾ���ɹ��������������...");
			return;
		}else if(thisNode->data.id > stu.id){
			break;
		}
	}

	pause("���󣺸�ѧ����Ϣ�����ڣ�ɾ��ʧ�ܣ������������...");	
}

//�޸�ѧ����Ϣ
void alterStuInfo(){
	STU stu;
	NODE *thisNode;
	getStuID(&stu.id);

	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		if(thisNode->data.id == stu.id){
			getStuName(stu.name);
			getStuSex(stu.sex);
			getStuAge(&stu.age);
			getStuMath(&stu.math);
			getStuCN(&stu.cn);
			getStuEN(&stu.en);
			thisNode->data = stu;
			updateFile();
			pause("��ʾ���޸ĳɹ��������������...");
			return;
		}else if(thisNode->data.id > stu.id){
			break;
		}
	}

	pause("���󣺸�ѧ����Ϣ�����ڣ��޸�ʧ�ܣ������������...");	
}

//����ѧ�Ų�ѯ
void findStuByID(){
	STU stu;
	NODE *thisNode, *pnode = NULL;
	float total = 0;
	getStuID(&stu.id);

	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		if(thisNode->data.id == stu.id){
			pnode = thisNode;
			break;
		}else if(thisNode->data.id > stu.id){
			break;
		}
	}

	if(pnode==NULL){
		pause("���󣺸�ѧ����Ϣ�����ڣ���ѯʧ�ܣ������������...");
	}else{
		stu = pnode->data;
		total = stu.math + stu.cn + stu.en;
		printf("-----------------------------------------------------------------------\n");
		printf("  ѧ��  |  ����  |  �Ա�  |  ����  |  ��ѧ  |  ����  |  Ӣ��  | �ܳɼ�\n");
		printf("--------+--------+--------+--------+--------+--------+--------+--------\n");
		printf("   %.2d   | %-6s |   %-3s  |   %-3d  | %-6.2f | %-6.2f | %-6.2f | %-6.2f\n", stu.id, stu.name, stu.sex, stu.age, stu.math, stu.cn, stu.en, total);
		printf("-----------------------------------------------------------------------\n");
		pause("\n�����������...");
	}
}

//����������ѯѧ����Ϣ
void findStuByName(){
	STU stu;
	NODE *thisNode = NULL;
	int n = 0;  //ƥ�䵽����ѧ����¼
	float total;
	char name[20];
	getStuName(name);

	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		stu = thisNode->data;
		if(strstr(stu.name, name)){
			n++;
			total = stu.math + stu.cn + stu.en;
			if(n==1){
				printf("-----------------------------------------------------------------------\n");
				printf("  ѧ��  |  ����  |  �Ա�  |  ����  |  ��ѧ  |  ����  |  Ӣ��  | �ܳɼ�\n");
				printf("--------+--------+--------+--------+--------+--------+--------+--------\n");
			}
			printf("   %.2d   | %-6s |   %-3s  |   %-3d  | %-6.2f | %-6.2f | %-6.2f | %-6.2f\n", stu.id, stu.name, stu.sex, stu.age, stu.math, stu.cn, stu.en, total);
		}
	}

	if(n>0){
		printf("-----------------------------------------------------------------------\n");
		pause("\n����ѯ��%d����¼�������������...", n);
	}else{
		pause("����û�в�ѯ����ؼ�¼�������������...");
	}
}

//���ݳɼ���ѯѧ����Ϣ
void findStuByScores(int flag){
	STU stu;
	int n = 0;  //ƥ�䵽����ѧ����¼
	float *scores = NULL;  //��ǰѧ���ĳɼ�
	float min = 0, max = 0;  //�û��������߷ֺ���ͷ�
	int MAX = 0;  //common.h ��ָ���ĳɼ���߷�
	char *courseName = NULL;  //��Ŀ����
	float total = 0;  //��ǰѧ���ܷ�
	NODE *thisNode = NULL;

	if(flag == FIND_BY_MATH){
		courseName = "��ѧ�ɼ�";
		MAX = MAX_STU_MATH;
		scores = &stu.math;
	}else if(flag == FIND_BY_CN){
		courseName = "���ĳɼ�";
		MAX = MAX_STU_CN;
		scores = &stu.cn;
	}else if(flag == FIND_BY_EN){
		courseName = "Ӣ��ɼ�";
		MAX = MAX_STU_EN;
		scores = &stu.en;
	}else if(flag == FIND_BY_TOTAL){
		courseName = "�ܳɼ�";
		MAX = MAX_STU_MATH + MAX_STU_CN + MAX_STU_EN;
		scores = &total;
	}else{
		return;
	}

	while(1){
		printf("Ҫ��ѯ��%s�ķ�Χ��", courseName);
		fflush(stdin);
		scanf("%f %f", &min, &max);
		if(min<0 || min>MAX || max<0 || max>MAX){
			pause("����%s��ȡֵ��ΧΪ0~%d�����������������...", courseName, MAX);
			continue;
		}
		if(min>max){
			pause("������߷�Ҫ������ͷ֣����������������...");
			continue;
		}
		break;
	}

	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		stu = thisNode->data;
		total = stu.math + stu.cn + stu.en;
		if(min <= *scores && *scores <= max){
			n++;
			if(n==1){
				printf("-----------------------------------------------------------------------\n");
				printf("  ѧ��  |  ����  |  �Ա�  |  ����  |  ��ѧ  |  ����  |  Ӣ��  | �ܳɼ�\n");
				printf("--------+--------+--------+--------+--------+--------+--------+--------\n");
			}
			printf("   %.2d   | %-6s |   %-3s  |   %-3d  | %-6.2f | %-6.2f | %-6.2f | %-6.2f\n", stu.id, stu.name, stu.sex, stu.age, stu.math, stu.cn, stu.en, total);
		}
	}

	if(n>0){
		printf("-----------------------------------------------------------------------\n");
		pause("\n����ѯ��%d����¼�������������...", n);
	}else{
		pause("����û�в�ѯ����ؼ�¼�������������...");
	}
}

//�����ļ�
void updateFile(){
	NODE *thisNode;
	freopen(FILENAME, "wb+", fp );  //���´��ļ�������ļ��е�����
	for(thisNode=header.link; thisNode!=NULL; thisNode=thisNode->next){
		fwrite(&(thisNode->data), sizeof(STU), 1, fp);
	}
	fflush(fp);
}

//����ѧ��ID
void getStuID(int *id){
	while(1){
		printf("����ѧ�ţ�");
		fflush(stdin);
		scanf("%d", id);
		if(checkStuID(*id)){
			break;
		}
	}
}

//����ѧ������
void getStuName(char name[]){
	while(1){
		printf("����������");
		fflush(stdin);
		scanf("%s", name);
		if(checkStuName(name)){
			break;
		}
	}
}

//����ѧ���Ա�
void getStuSex(char sex[]){
	while(1){
		printf("�����Ա�");
		fflush(stdin);
		scanf("%s", sex);
		if(checkStuSex(sex)){
			break;
		}
	}
}

//����ѧ������
void getStuAge(int *age){
	while(1){
		printf("�������䣺");
		fflush(stdin);
		scanf("%d", age);
		if(checkStuAge(*age)){
			break;
		}
	}
}

//������ѧ�ɼ�
void getStuMath(float *math){
	while(1){
		printf("��ѧ�ɼ���");
		fflush(stdin);
		scanf("%f", math);
		if(checkStuMath(*math)){
			break;
		}
	}
}

//�������ĳɼ�
void getStuCN(float *cn){
	while(1){
		printf("���ĳɼ���");
		fflush(stdin);
		scanf("%f", cn);
		if(checkStuCN(*cn)){
			break;
		}
	}
}

//����Ӣ��ɼ�
void getStuEN(float *en){
	while(1){
		printf("Ӣ��ɼ���");
		fflush(stdin);
		scanf("%f", en);
		if(checkStuEN(*en)){
			break;
		}
	}
}

//���ѧ��ID�Ƿ���ȷ
int checkStuID(int stuID){
	if(stuID <= 0 || stuID > MAX_STU_ID){
		printf("����ѧ�ű����Ǵ���0��С�ڵ���%d��������\n", MAX_STU_ID);
		return 0;
	}
	return 1;
}

//���ѧ�������Ƿ���ȷ
int checkStuName(char name[]){
	if(strlen(name) > MAX_STU_NAME){
		printf("�������ֵ���󳤶Ȳ�����%d���ֽڣ�\n", MAX_STU_NAME);
		return 0;
	}
	return 1;
}

//���ѧ���Ա��Ƿ���ȷ
int checkStuSex(char sex[]){
	if(strcmp(sex, "��") && strcmp(sex, "Ů")){
		printf("�����Ա�ֻ�����л�Ů��\n");
		return 0;
	}else{
		return 1;
	}
}

//���ѧ�������Ƿ���ȷ
int checkStuAge(int age){
	if(age <=0 || age > MAX_STU_AGE){
		printf("���������ȡֵ��ΧΪ1~%d��\n", MAX_STU_AGE);
		return 0;
	}
	return 1;
}

//�����ѧ�ɼ��Ƿ���ȷ
int checkStuMath(float math){
	if(math < 0 || math > MAX_STU_MATH){
		printf("������ѧ�ɼ���ȡֵ��ΧΪ0~%d��\n", MAX_STU_MATH);
		return 0;
	}
	return 1;
}

//������ĳɼ��Ƿ���ȷ
int checkStuCN(float cn){
	if(cn < 0 || cn > MAX_STU_CN){
		printf("�������ĳɼ���ȡֵ��ΧΪ0~%d��\n", MAX_STU_CN);
		return 0;
	}
	return 1;
}

//���Ӣ��ɼ��Ƿ���ȷ
int checkStuEN(float en){
	if(en < 0 || en > MAX_STU_EN){
		printf("����Ӣ��ɼ���ȡֵ��ΧΪ0~%d��\n", MAX_STU_EN);
		return 0;
	}
	return 1;
}

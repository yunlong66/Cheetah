#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <conio.h>
#include <stdarg.h>
#include "tools.h"

//��ȡ�ļ���С�����ֽڼƣ�
long getFileSize(FILE *fp){
	long fsize;
	fpos_t fpos;  //��ǰλ��
	fgetpos(fp, &fpos);  //��ȡ��ǰλ��
	fseek(fp, 0, SEEK_END);
	fsize = ftell(fp);
	fsetpos(fp,&fpos);  //�ָ�֮ǰ��λ��

	return fsize;
}

/**
 * ��ͣ����
 * @param  str  ������ͣʱ��ʾ���ַ��������԰�����ʽ���Ʒ�
 * @param  ...  �䳤����
**/
void pause(const char *str, ...){
	va_list vl;
	char buf[500] = {0};
	va_start(vl, str);
	vsnprintf(buf, 500, str, vl);
	va_end(vl);
	printf(buf);
	getch();
	printf("\n");
}
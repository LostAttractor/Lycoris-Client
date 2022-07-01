#include <iostream>
#include <string.h>

char classes[]={0x61,0x62,0x63,0x64,0x65,0x66,0x67,0x68,0x69,0x6a,0x6b,0x6c};
int size[] = {3,4,5};
int count = 3;

int main(){
    int tempClassIndex = 0;
    int lastClassIndex = 0;
    int classIndex = 0;
    for (int i = 0; i != count ; i++) {
    	 char *lastClass = new char[size[i]+1];
         for (classIndex = 0 ; classIndex != size[i]; classIndex++) {
            tempClassIndex++;
			lastClass[classIndex] = classes[lastClassIndex + classIndex];
         }
         delete []lastClass;
         lastClassIndex = tempClassIndex;
    }
    return 0;
}
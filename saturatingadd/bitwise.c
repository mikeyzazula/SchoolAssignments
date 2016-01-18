//2.58. 

#include <stdio.h> //standard io libr


int is_little_endian()
{
	int x = 0x2345; 
	char * pointer;
	pointer = (char*)&x;

	if(*pointer == 0x45) {
		printf("1\n");
		return 1;
	}
	
	else{
		printf("1\n");
		return 0;
	}
}

int main(){
	is_little_endian();
}

//2.73

#include <stdio.h> //standard io libr
#include <limits.h>

int get_msb (int x) {
	int shift_val = (sizeof(int)-1) << 3;
	int xright = x >> shift_val; 
	return xright;
}

int saturating_add(int x, int y){


        int sum = x + y;
        int xmsb = get_msb(x);
        int ymsb = get_msb(y);
        int summsb = get_msb(sum);
        int positiveflow = ~xmsb & ~ymsb & summsb;
        int negativeflow  = xmsb & ymsb & ~summsb;
        int overflow = positiveflow | negativeflow;

        int newsum = (positiveflow & INT_MAX) | (negativeflow & INT_MIN | sum & ~overflow);

        printf("%d\n",newsum );

        return newsum;
}











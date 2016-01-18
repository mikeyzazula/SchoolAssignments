//2.58. 

#include <stdio.h> //standard io libr


//return 1 if the machine is little endian, or return 0 if it's big endian. 
int is_little_endian()
{
	//pick an integer
	int x = 0x2345; 
	char * pointer;
	
	//set our pointer at the integer we just made. 
	pointer = (char*)&x;

	//if the pointer is pointer at 0x45, we know our machine is little endian, so we print one.  
	if(*pointer == 0x45) {
		printf("1\n");
		return 1;
	}
	
	else{
		printf("1\n");
		return 0;
	}
}



//2.73
/*saturating addition function adds two numbers and if they overflow INT_MAX or INT_MIN is returned depending on negative 
or positive overflow*/

/* 
. Assumptions

	Integers are represented in two’s-complement form.
	Right shifts of signed data are performed arithmetically.
	Data type int is w bits long. For some of the problems, you will be given a
	specific value for w, but otherwise your code should work as long as w is a
	multiple of 8. You can use the expression sizeof(int)<<3 to compute w.

. Forbidden

	Conditionals (if or ?:), loops, switch statements, function calls, and macro
	invocations.
	Division, modulus, and multiplication.
	Relative comparison operators (<, >, <=, and >=).
	Casting, either explicit or implicit.

. Allowed operations

	All bit-level and logic operations.
	Left and right shifts, but only with shift amounts between 0 and w − 1.
	Addition and subtraction.
	Equality (==) and inequality (!=) tests. (Some of the problems do not allow
	these.)
	Integer constants INT_MIN and INT_MAX.

*/

#include <stdio.h> //standard io libr
#include <limits.h>

//given function from the text  to find the most significant bit (sign bit)
int get_msb (int x) {
	int shift_val = (sizeof(int)-1) << 3;
	int xright = x >> shift_val; 
	return xright;
}

int saturating_add(int x, int y){

        int sum = x + y;
        
        // grab the most significant bit for each of our variables 
        int xmsb = get_msb(x);
        int ymsb = get_msb(y);
        int summsb = get_msb(sum);

        //logic to find out what type of overflow do we have if we have overflow
        int positiveflow = ~xmsb & ~ymsb & summsb;
        int negativeflow  = xmsb & ymsb & ~summsb;
        
        //variable needed for our return statement 
        int overflow = positiveflow | negativeflow;

        //the variable we will be returning.
        int newsum = (positiveflow & INT_MAX) | (negativeflow & INT_MIN | sum & ~overflow);

        printf("%d\n",newsum );

        return newsum;
}











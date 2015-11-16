

import java.lang.*;
import java.util.*;
import java.io.*;

public class Heap {  
    static int size;
    static node data[];    
// basic constructor for the minheap, calls minHeapify  
    public Heap(node[] newData){
		int length = newData.length;
		this.size = length-1;
		data = newData;
		for(int i = length/2;i >= 1;  i--){
			minHeapify(data,i);
						
		}
		
	}
/*pre:
	newData is our nodeArray
	int i is the position
 
 post:   
 sorts the heap to output it as a minimum heap. left and right are the children of parent i. the series of if statements compare each node to find the smallest.*/
    
	public  void minHeapify(node[] newData , int i){
		int left = 2*i;
		int right = (2*i)+1; 
		int smallest;

			if(left < this.size &&  newData[left].frequency < newData[i].frequency){
				smallest = left;			
			}
			else{
				smallest = i;
			}
		
			if( right < this.size&& newData[right].frequency < newData[smallest].frequency){
				smallest = right;
			}
			if(smallest != i){
				node temp = newData[i];
				newData[i] = newData[smallest];
				newData[smallest] = temp;
				minHeapify (newData,smallest);
			}
		//}	
    

	}
/*pre: 
	newNode is a node passed in by huffman which is a combination of the freqs. of left and right
post:
	a newNode is a * with freq of left+right now inserted into our heap*/
	public  void insert(node newNode ){
		int sizeCopy = size;
		node temp = null;
		size += 1;
		data[size] = newNode;
		while(sizeCopy/2 != 0 && data[sizeCopy / 2].frequency > data[sizeCopy].frequency){
			temp = data[sizeCopy/2];
			data[sizeCopy /2] = data[sizeCopy];
			data[sizeCopy] = temp;
			sizeCopy = sizeCopy/2;
		}
		
	}

/*	pre: called when we need to remove the first node of a heap
	post:the first node is removed and returned */
		
	public  node delete(){
		if(size < 1){
			System.out.println("heap underflow");
		}
		node min = data[1];
		data[1] = data[size];
		minHeapify(data,1);	
		size--;

		return min;
	}
}
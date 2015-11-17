

import java.lang.*;
import java.util.*;
import java.io.*;


// basic constructor for our node class where we taking in the character frequency and left and rights if they're there. 
public class node {
    char character;
    int frequency;
    node left;
    node right;



	public node(char character, int frequency, node left, node right){
	    this.character = character;
	    this.frequency = frequency;
	    this.left = left;
	    this.right = right;

	}

}
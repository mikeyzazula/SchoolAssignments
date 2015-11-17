//Michael Zazula
//6/1/2015
//CS241 Assgn.3

//what this program does : reads in a text file, counts the characters and finds how frequent they are used and how huffman can save space!
import java.lang.*;
import java.util.*;
import java.io.*;


public class Program3{
	//class variables 
	public static HashMap<Character,Integer> newMap;
	public static node[] nodeArray;
	public static int count = 0;
	public static Heap minHeap;
	public static node hRoot;
	public static int sumBits = 0;
	public static int totalBits = 0;
	public static String txtFilename;
	

	//main runs the program,  etc etc 
	 
		
	public static void main(String args[]){
        newMap = new HashMap<Character,Integer>();
        Scanner console = new Scanner(System.in);
		System.out.println("Type your filename:  ");
		txtFilename = console.nextLine();
		//String outdir        = args[1];

        loadTxt(newMap, txtFilename);
        //System.out.print(newMap);
        minHeap = new Heap(nodeArray);
        hRoot = huffman();
        outPut();
	}
	
/*
*Pre: 
*-newMap is a new hashmap
*-txtFilename is a string of the file to be read through
*Post:
*nodeArray is created and filled with nodes containing a character and frequecny 
*/	
   public static void loadTxt( HashMap<Character,Integer> newMap,String txtFilename ) {
	      try{
	         File fileName = new File(txtFilename);
	         //@SuppressWarnings("resource")
			Scanner infile = new Scanner(fileName);
	         while(infile.hasNextLine() ){
	            String word = infile.nextLine();
	            for(int i = 0 ; i<word.length();i++){
	            		char w = word.charAt(i);
	            		incrementHashMap(newMap,w);	
	            }   
	            nodeArray = new node[count];

	         }  
	      }catch(FileNotFoundException e){
	         System.out.println("Not able to open file " + txtFilename);
	      }    
	        createArray(newMap);
	    }


   /* Pre:
    * newMap is a hashmap to be turned into an array of nodes
    * Post
    *nodeArray is created and filled with nodes containing a character and frequecny 
    */		
   public static void createArray(HashMap<Character,Integer> newMap){
	   int i = 1;
       nodeArray = new node[newMap.keySet().size()+1];
	   for(char key : newMap.keySet()){		
		   node curr = new node (key,newMap.get(key),null,null);		
		   totalBits += newMap.get(key);
		   if(nodeArray[i] == null){
			   nodeArray[i] = curr;		 
			   i++;
		}
		   
	}
	   
   }

/*pre: 
	minheap is an unsorted heap
post:
	add up the left and right frequencies and creat a new node that is the total of freq. of the left and right */
   public static node huffman(){
	   while(minHeap.size > 1){
		   node left = minHeap.delete();
		   node right = minHeap.delete();
		   node z = new node('*',left.frequency + right.frequency,left,right);
		   //node z = new node('*',left.frequency + right.frequency,null,null);
		   minHeap.insert(z);		   		   
	   }
       return minHeap.delete();
   }
   
   
   
/*pre
hashmap contains chars and ints
Key is a character in the hashmap
post
checked to see if the character key was in the map, if it wasn't it adds space and adds it, if it was laready in the map it will just increment the freq.
*/   public static void incrementHashMap(HashMap<Character,Integer> map,char key) {
	    if( map.containsKey(key) ) {
	      map.put(key,map.get(key)+1);
	      count +=1;
	    } else {
	      map.put(key,1);
	    }
	    return;
	  }
   

/*pre:
node root is the hRoot which is the first node
ladder is the string of 1's and 0's
printstream output is used later for print to a new file
post:
checks both left and right branches if they are null, if they aren't it's going to keep calling itself and build the 1's and 0's edges while totalling up the bits saved in the proces
once both left and right are null, it knows that one is complete and spits it out. */
   public static int printHuffman(node root, String ladder, PrintStream output){
	  if(root.left != null && root.right != null){		
			  sumBits = printHuffman(root.left,"0"+ ladder, output );		  
			  sumBits += printHuffman(root.right, "1"+ ladder,output);
	  }
	   
	   
	   if(root.left == null && root.right == null){
		   sumBits = (8 - ladder.length()) * root.frequency;
		   output.println(root.character + " " + ladder);	
		   return sumBits;
	   }
	   	return sumBits;
		
		
	}
/*pre 
called in main to call printhuffman, basically only used because we need a printstream
post
calls printHuffman and creates the file
   */
   
	public static void outPut (){
		java.io.PrintStream file = null;
		txtFilename = txtFilename.substring(0, (txtFilename.length()-3));
		txtFilename = txtFilename+"huf";
        try {
            file = new java.io.PrintStream(new java.io.File(txtFilename));
            int bitsSaved = printHuffman(hRoot, "", file);
            file.println("Total bits: "+ totalBits*8);
            file.println("Total bits saved: "+ bitsSaved);
        } catch( java.io.FileNotFoundException e ) {
            System.err.println("Error: Unable to open output file for writing" + e);
            System.exit(1);
        }
		
	}


}
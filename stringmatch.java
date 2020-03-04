package stringdistance;
class stringmatch 
{ 
static int min(int x,int y,int z) 
{ 
    if (x<=y && x<=z)
    	return x; 
    if (y<=x && y<=z)
    	return y; 
    else
    	return z; 
} 

static int editDistance(String str1 , String str2 , int i ,int j) 
{ 
	 if (i == 0) 
		 return j; 
	 if (j == 0) 
		 return i; 
	 if (str1.charAt(i-1) == str2.charAt(j-1)) 
	        return editDistance(str1, str2, i-1, j-1); 
	 return 1 + min ( editDistance(str1,  str2, i, j-1),    // Insert 
             editDistance(str1,  str2, i-1, j),   // Remove 
             editDistance(str1,  str2, i-1, j-1) // Replace                      
           ); 
} 

public static void main(String[] args) 
{ 
String str1 = "student"; 
String str2 = "study"; 

System.out.println( editDistance( str1 , str2 , str1.length(), str2.length()) ); 
} 
} 

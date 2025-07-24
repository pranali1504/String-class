package myString_class;
import java.util.*;
class MyStringIndexOutOfBoundsException extends RuntimeException
{
	public MyStringIndexOutOfBoundsException(String message)
	{
		super(message);
	}
}
public final class MyString 
{
    char[] arr;
    //CONSTRUCTORS
    public MyString()
    {
    	arr=new char[0];
    }
    public MyString(String str)
    {
    	arr=new char[str.length()];
    	for(int i=0;i<arr.length;i++)
    		arr[i]=str.charAt(i);
    }
    public MyString(StringBuffer sb)
    {
    	this(sb.toString());
    }
    public MyString(StringBuilder sb)
    {
    	this(sb.toString());
    }
    public MyString(char []arr)
    {
    	this(arr,0,arr.length);
    }
    public MyString(char[]arr,int begin,int count)
    {
    	if((begin+count)>arr.length)
    		throw new MyStringIndexOutOfBoundsException(" Range ["+begin+","+ begin +"+"+count+") out of bounds for length "+arr.length);
    	this.arr=new char[count];
    	for(int i=begin,j=0;i<(begin+count);i++,j++)
    		this.arr[j]=arr[i];
    }
    
    public MyString(byte[] arr) 
    {
    	this(arr,0,arr.length);
    }
    public MyString(byte []arr,int start,int count)
    {
    	if((start+count)>arr.length)
    		throw new MyStringIndexOutOfBoundsException(" Range ["+start+","+ start +"+"+count+") out of bounds for length "+arr.length);
    	this.arr=new char[count];
    	for(int i=start,j=0;i<(start+count);i++,j++)
    		this.arr[j]=(char)arr[i];
    }
	public MyString(int[] arr, int start, int count) 
	{
		if((start+count)>arr.length)
    		throw new MyStringIndexOutOfBoundsException(" Range ["+start+","+ start +"+"+count+") out of bounds for length "+arr.length);
    	this.arr=new char[count];
    	for(int i=start,j=0;i<(start+count);i++,j++)
    		this.arr[j]=(char)arr[i];
	}
	
	//METHODS
	public int length() 
	{
		return arr.length;
    }
	public boolean isEmpty() 
	{
		return arr.length==0;
	}
	 public char charAt(int indx) 
	 {
		return arr[indx];
     }
	 public int codePointAt(int indx) 
	 {
		 if(indx<0 || indx>=arr.length)
			 throw new MyStringIndexOutOfBoundsException("Index "+indx+" out of bounds for length "+arr.length);
			return arr[indx];
     }
	 public int codePointBefore(int indx) 
	 {
			return codePointAt(indx-1);
     }
	 public int codePointCount(int start, int end) 
	 {
		 if(start>end || start<0||end>arr.length)
			 throw new MyStringIndexOutOfBoundsException("Range ["+start+", "+end+") out of bounds for length "+arr.length);
			return (end-start);
	 }
	 public MyString toUpperCase() 
	 {
		char[] newArr =new char[arr.length];
		for(int i=0;i<newArr.length;i++)
		{
			char ch=arr[i];
			newArr[i]=(ch>=97 && ch<=122)? (char)(ch-32):ch;
		}
		return new MyString(newArr);
	 }	
	 public MyString toLowerCase() 
	 {
			char[] newArr=new char[arr.length];
			for(int i=0;i<arr.length;i++)
			{
				char ch=arr[i];
				newArr[i]=(ch>=65 && ch<=90)? (char)(ch+32):ch;
			}
			return new MyString(newArr);
     }
	 public int indexOf(char ch) 
	 {
		 return indexOf(ch,0);
	 }
	 public int indexOf(char ch, int start) 
	 {
		 for(int i=start;i<arr.length;i++)
			 if(arr[i]==ch)
				 return i;
		 return -1;
     }
	  public int lastIndexOf(char ch) 
	  {
			return lastIndexOf(ch,arr.length-1);
	  }
	  public int lastIndexOf(char ch, int start) 
	  {
		  for(int i=start;i>=0;i--)
				if(arr[i]==ch)
					return i;
			return -1;
	  }
	  public MyString concat(MyString str)
	  {
			char [] newArr=new char[arr.length+str.length()];
			for(int i=0,j=0;i<newArr.length;i++)
			{
				if(arr.length>i)
					newArr[i]=arr[i];
				else
					newArr[i]=str.charAt(j++);
			}
			return new MyString(newArr);
      }
	  @Override
	  public boolean equals(Object obj)
	  {
		  if(!(obj instanceof MyString)) return false;
		  MyString str =(MyString)obj;
		  if(arr.length!=str.length()) return false;
		  for(int i=0;i<arr.length;i++)
		  {
			  if(arr[i]!=str.charAt(i)) return false;
		  }
		  return true;
	  }
	  public MyString trim() 
	  {
		 int left=0, right=0;
		 for(int i=0;i<arr.length;i++)
			 if(arr[i]==' ') left++;
			 else break;
		 for(int i=arr.length-1;i>=0;i--)
			 if(arr[i]==' ') right++;
			 else break;
			return substring(left,(arr.length-right));
	  }
	  public boolean contentEquals(StringBuffer str1) 
	  {
			MyString obj= new MyString(str1);
			return this.equals(obj);
	  }
	  public boolean equalsIgnoreCase(MyString str) 
	   {
		  return this.toLowerCase().equals(str.toLowerCase());
	   }
	  public MyString substring(int indx) 
	   {
		  if(indx==0) return new MyString(arr);
		  return substring(indx,arr.length);
	   }   
	  public MyString substring(int start, int end) 
	   {
		  if(start>end || start<0 ||end>arr.length)
			  throw new  MyStringIndexOutOfBoundsException("Range ["+start+", "+ end+")"+" out of bounds for length "+arr.length);
		  char[] newArr=new char[end-start];
			for(int i=0;i<newArr.length;i++)
				newArr[i]=arr[start++];
			return new MyString(newArr);
	   }
	  public boolean startsWith(String prefix) 
	   {
		return startsWith(prefix,0);
	   }
	  public boolean startsWith(String prefix,int start) 
	   {
		if(prefix.length()>arr.length || prefix.length()>arr.length-start) return false;
		for(int i=0;i<prefix.length();i++)
			if(arr[start++]!=prefix.charAt(i)) return false;
		return true;
	   }
	   public boolean endsWith(String suffix)
	   {
		   if(suffix.length()>arr.length) return false;
		   for(int i=suffix.length()-1,j=arr.length-1;i>=0;i--,j--)
			   if(arr[j]!=suffix.charAt(i)) return false;
		   return true;
	   }
	   public boolean contains(String str) 
	   {
		   if(str.length()>arr.length) return false;
		   for(int i=0;i<arr.length;i++)
			   if(startsWith(str,i)) return true;
		   return false;
	   }
	@Override
    public String toString()
    {
      String str="";
      for(char ele:arr)
    	  str+=ele;
      return str;
    }
   
   public static void main(String[] args)
   {
	   String str=new String("Hllo java ello ava");
	   System.out.println(str);
	   System.out.println(str.replaceFirst("[aeiou]",""));
	   
	   MyString str2=new MyString("Hello java ello ava");
	   System.out.println(str2.contains("llo"));

   }
   
  
   
   
}
   

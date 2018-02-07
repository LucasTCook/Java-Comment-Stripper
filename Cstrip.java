import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cstrip {

private static Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(java))$)");
     
    public static boolean validateFileExtn(String s){
         
        Matcher mtch = fileExtnPtrn.matcher(s);
        if(mtch.matches()){
            return true;
        }
        return false;
    }


public static void main(String [] a) throws IOException {
	int r;
	String fn;
	Stripper myStripper = new Stripper();
	Reader fs;
	Scanner in = new Scanner(System.in);

	System.out.print("Enter file name: ");
	fn = in.next();

	if(validateFileExtn(fn) == true){
		fs = new InputStreamReader(new FileInputStream(fn));
		while ((r =  fs.read()) != -1) 
			myStripper.process((char)r);
	}else{
		System.out.println("Please enter a .java file next time..."); 
	}
}
}

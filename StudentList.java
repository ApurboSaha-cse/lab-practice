import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {

	public static String getlineFromFile() throws  Exception{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.STUDENTS_FILE_NAME)));
		String line = bufferedReader.readLine();
		return line;
	}
	public  static BufferedWriter getFileBufferedWriter() throws Exception{
		return new BufferedWriter(new FileWriter(Constants.STUDENTS_FILE_NAME, false));
	}
	public static void main(String[] args) {

		if(args.length != 1){
			System.err.println(Constants.MSG_INVALID_NUMBER_OF_ARGUMENTS);
			System.err.println(Constants.MSG_USAGE);
			System.err.println(Constants.MSG_EXITING_PROGRAM);
			System.exit(1);
		}

//		Check arguments
		if(args[0].equals(Constants.ARG_LIST_DATA)) {
			System.out.println(Constants.MSG_LOADING_DATA);
			try {
			for(String student : getlineFromFile().split(Constants.WORDS_SPLIT_REGEX)) {
				System.out.println(student.trim());
			}
			} catch (Exception e){} 
			System.out.println(Constants.MSG_LOADED_DATA);
		}
		else if(args[0].equals(Constants.ARG_SHOW_RANDOM_DATA)) {
			System.out.println(Constants.MSG_LOADING_DATA);
			try {
			String students[] = getlineFromFile().split(Constants.WORDS_SPLIT_REGEX);
					System.out.println(students[new Random().nextInt(students.length)].trim());
			} catch (Exception e){} 
			System.out.println(Constants.MSG_LOADED_DATA);
		}
		else if(args[0].contains(Constants.ARG_ADD_DATA)){
			System.out.println(Constants.MSG_LOADING_DATA);
			try {
				String previousContent = getlineFromFile();
				BufferedWriter bufferedWriter = getFileBufferedWriter();
			bufferedWriter.write(previousContent);
			bufferedWriter.write(Constants.WORDS_SPLIT_REGEX + " " +args[0].substring(1)+Constants.MSG_DATA_UPDATED+new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN).format(new Date()));
			bufferedWriter.close();
			} catch (Exception e){}
			System.out.println(Constants.MSG_LOADED_DATA);
		}
		else if(args[0].contains(Constants.ARG_FIND_DATA)) {
			System.out.println(Constants.MSG_LOADING_DATA);
			try {
			String students[] = getlineFromFile().split(Constants.WORDS_SPLIT_REGEX);
			if(Arrays.stream(students).anyMatch(student -> args[0].substring(1).contains(student.trim()))) {
				System.out.println(Constants.MSG_DATA_FOUND +args[0].substring(1));
			}
			else{
				System.out.println(Constants.MSG_DATA_NOT_FOUND +args[0].substring(0));
			}
			} catch (Exception e){} 
			System.out.println(Constants.MSG_LOADED_DATA);
		}
		else if(args[0].contains(Constants.ARG_COUNT_WORDS)) {
			System.out.println(Constants.MSG_LOADING_DATA);
			try {
			boolean in_word = false;
			int count=0;
			for(char student : getlineFromFile().toCharArray()) {
				if(student ==' ') {
					if (!in_word) {
						count++; in_word =true;
					}
					else { in_word=false;}			
				}
			}
			System.out.println(getlineFromFile().split(Constants.WORDS_SPLIT_REGEX).length + Constants.MSG_WORDS_FOUND);
			} catch (Exception e){} 
			System.out.println(Constants.MSG_LOADED_DATA);
		}
		else{
			System.err.println(Constants.MSG_INVALID_ARGUMENTS);
			System.err.println(Constants.MSG_USAGE);
			System.err.println(Constants.MSG_EXITING_PROGRAM);
			System.exit(2);
		}
	}
}
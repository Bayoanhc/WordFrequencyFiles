
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class mainWordFrequency {

	public static void main(String[] args) {
		
		//File of path files
		String path = "./src/txtFiles/readPathFiles.txt";
		
		//Hashmaps to store words
		HashMap<String, List<ArrListWordFrequency>> sumPerFile = new HashMap<String, List<ArrListWordFrequency>>();
		HashMap<String, Integer> globalMapSum = new HashMap<String, Integer>();
		
		try {
			//Reading the file of path files
			Scanner pathFiles = new Scanner(new File(path));
			
			while(pathFiles.hasNextLine()) {
				//Reading the words on file
				String filePath = pathFiles.nextLine();
				
				//Occurrences inside each file
				HashMap<String, Integer> localMapSum = functionLocalMapSum(filePath);				
				//System.out.println(filePath + ": " + localMapSum);
				
				for(Map.Entry<String, Integer> entry : localMapSum.entrySet()) {
					
					ArrListWordFrequency alpc = new ArrListWordFrequency(filePath, entry.getValue());
					List<ArrListWordFrequency> itemsList = sumPerFile.get(entry.getKey());
					
					if(itemsList == null) {
						itemsList = new ArrayList<ArrListWordFrequency>();
						itemsList.add(alpc);
						sumPerFile.put(entry.getKey(), itemsList);
					}
					else {
						if(!itemsList.contains(alpc)) itemsList.add(alpc);
					}
					
					if(globalMapSum.containsKey(entry.getKey())) {
						int partial = globalMapSum.get(entry.getKey());
						globalMapSum.put(entry.getKey(), partial + entry.getValue());
						
					}
					else {
						globalMapSum.put(entry.getKey(), entry.getValue());
					}
					
				}
				
			}
			pathFiles.close();
			//System.out.println(sumPerFile);
			System.out.println("Total sums of the words across files: ");
		    System.out.println(globalMapSum);
			
			while(true) {
				System.out.print("\nEnter word to search (#555 to quit): ");
				
				Scanner in = new Scanner(System.in);
				String s = in.nextLine();
				
				if (s.equals("#555")) {
					break;
				}
				
				System.out.println(sumPerFile.get(s));
				System.out.println(globalMapSum.get(s));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	private static HashMap<String, Integer> functionLocalMapSum(String filePath) {

		HashMap<String, Integer> localMapSum = new HashMap<String, Integer>();
		
		Scanner wordsFiles;
		try {
			wordsFiles = new Scanner(new File(filePath));
			
			while(wordsFiles.hasNext()) {
				String word = wordsFiles.next();
				
				if(localMapSum.containsKey(word)) {
					int partial = localMapSum.get(word) + 1;
					localMapSum.put(word, partial);
				}
				else {
					localMapSum.put(word, 1);
				}
			}
			wordsFiles.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return localMapSum;
	}

}


	

	
	
	
	


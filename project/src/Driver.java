import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Driver{
	LinkedList<String> stopWords;
	index index1;
	Inverted_Index inverted;

	public Driver() {
		this.stopWords = new LinkedList<>();
		this.index1 = new index();
		this.inverted = new Inverted_Index();
	}

	public void Load_stopWords (String fileName) {
		
		try {
			File f = new File (fileName);
			Scanner s = new Scanner (f);
				while (s.hasNextLine()) {
				String line= s.nextLine();
				stopWords.insert(line);
				}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void Load_all_doc(String fileName) {
		String line =null;
		try {
		File f = new File (fileName);
		Scanner s = new Scanner (f);
		s.nextLine();
			while (s.hasNextLine()) {
				line = s.nextLine();
				if (line.trim().length()<3) {
					System.out.println("Empty line found. skipping this ");
					break;
				}
				String x = line.substring(0,line.indexOf(','));
				int id = Integer.parseInt(x.trim());
				String content = line.substring(line.indexOf(',')+1).trim();
				LinkedList<String> words_in_doc = make_linked_list_of_words_in_doc_index_inverted_index(content,id);
				index1.add_Doc(new Document (id,words_in_doc));
			}
		}
			catch(Exception e) {
				System.out.println("end of file");
			}
		}
	
	
				
				
		public LinkedList<String> make_linked_list_of_words_in_doc_index_inverted_index(String content,int id){
			LinkedList<String> words_in_doc = new LinkedList <String>();
			make_index_and_inverted_index(content,words_in_doc,id);
			return words_in_doc;
			
		}
		public void make_index_and_inverted_index(String content,LinkedList<String> words_in_doc,int id){
			content = content.toLowerCase().replaceAll("[^a-zA-z0-9 ]", "");
			String[] tokens = content.split("\\s+");
			for (String w : tokens) {
				if(!existsIn_stop_words(w)) {
					words_in_doc.insert(w);
					inverted.add(w,id);
				}
			}
		}
		public boolean existsIn_stop_words(String word) {
		    if (stopWords == null || stopWords.empty()) 
		        return false;
		    stopWords.findFirst();
		    while (!stopWords.last()) {
		        if (stopWords.retrieve().equals(word)) {
		            return true;
		        }
		        stopWords.findNext();
		    }
		    if (stopWords.retrieve().equals(word)) {
		        return true;
		    }
		    return false;
	}
	public void Load_all_files(String stopwordsFile,String documentFile){
		Load_stopWords(stopwordsFile);
		Load_all_doc(documentFile);
	}
	public static void main(String[] args) {
		Driver d = new Driver();
		d.Load_all_files("project\\src\\stop.txt", "project\\src\\dataset.csv");
		d.index1.display();
		System.out.println("\n---------------");
		d.inverted.display_Inverted_Index();
		
	}
}

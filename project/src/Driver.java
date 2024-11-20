import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Driver {
	LinkedList<String> stopWords;
	index index1;
	InvertedIndex inverted;

	public Driver() {
		stopWords = new LimkedList<>();
		index1 = new index();
		inverted = new InvertedIndex();
	}

	public void Load_stopWordes (String fileName) {
		
		try {
			File f = new File (fileName);
			Scanner s = new Scanner (f);
				while (s.hasNextLine()) {
				String line= s.nextline();
				stopWords.insert(line);
				}
		}
		catch (IOExeption e) {
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
				String contant = line.substring(line.indexOf(',')+1).trim();
				LinkedList<String>words_in_doc=make_linked_list_of_words_in_doc_index_inverted_index(contant);
				index1.add_Document(new Document (id.words_in_doc));
			}
			catch(Exception e) {
				System.out.println("end of file");
			}
		}
				
				
				
		public LinkedList<String> make_linked_list_of_words_in_doc_inverted_index(String contant,int id){
			LinkedList<String>words_in_doc = newLinkedList <String>();
			make_index_and_inverted_index(contant, words_in_doc,id);
			return words_in_doc;
			
		}
		public void make_index_andinverted_index(String_contant,LinkedList<String>words_in_doc,int id) {
			contant = contant.toLowerCase().replaceAll("[^a-zA-z0-9 ", "");
			string[]tokens =contant.split("\\s+");
			for (String w : tokens) {
				if(!exeistIn_stop_words(w)) {
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
}

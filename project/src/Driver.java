import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Driver {
	LinkedList<String> stopWords;
	index index1;
	InvertedIndex inverted;
	InvertedIndexBST invertedBST;
	int num_tokens = 0;

	public Driver() {
		stopWords = new LimkedList<>();
		index1 = new index();
		inverted = new InvertedIndex();
	}

	public void Load_stopWordes(String fileName) {

		try {
			File f = new File(fileName);
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextline();
				stopWords.insert(line);
			}
		} catch (IOExeption e) {
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
			contant = contant.relaceAll("\'","");
			contant =contant.replaceAll("-", ""); 
			
			contant = contant.toLowerCase().replaceAll("[^a-zA-z0-9 ", "");
			String[]tokens =contant.split("\\s+");
			num_tokens+=tokens.length;
			
			for (String w : tokens) {
				if(unique_words.exist(w)) {
					unique_words.insert(w);
				}
			}
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

	public void display_doc_with_given_IDS(LinkedList<Integer> IDs)
			{
			    if(IDs.empty()){
			        System.out.println("no documents exist");
			        return;
			    }
			    IDs.findFirst();
			    while(!IDs.last())
			    {
			        Document d=index1.get_document_given_id(IDs.retrieve());
			        if(d!=null)
			        {
			            System.out.println("Document "+d.id+":"+d.content);
			        }
			        IDs.findNext();
			    }
			    Document d=index1.get_document_given_id(IDs.retrieve());
			    if(d!=null)
			    {
			        System.out.println("Document "+d.id+":"+d.content);
			    }
			    System.out.println("");

	public static void Test1() {
		Driver d = new Driver();
		d.Load_all_files("stop.txt", "dataset.csv");
		d.index1.displayDocuments();
		System.out.println("\n=============================");
		d.inverted.display_inverted_index();
		System.out.println("num of tokens=" + d.num_tokens);
		System.out.println("num of unique=" + d.unique_words.n);

		QueryProcessing g = new QueryProcessing(d.inverted);
		LinkedList res = QueryProcessing.AndQuery("colorANDpole");
		d.display_doc_with_given_IDS(res);
		System.out.println("----------------------------");
		LinkedList res1 = QueryProcessing.ORQuery("Arabia OR pole OR color");
		d.display_doc_with_given_IDS(res1);

	}

}

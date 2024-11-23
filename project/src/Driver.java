import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Driver {

	LinkedList<String> stopWords;
	index index1;
	Inverted_Index inverted;
	Inverted_Index_BST invertedBST;
	int num_tokens = 0;
	LinkedList<String> unique_words = new LinkedList<>();

	public Driver() {
		stopWords = new LinkedList<>();
		index1 = new index();
		inverted = new Inverted_Index();
		invertedBST = new Inverted_Index_BST();
	}

	public void Load_stopWordes(String fileName) {

		try {
			File f = new File(fileName);
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				stopWords.insert(line);
			}
		} catch (IOException e) {

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
				LinkedList<String>words_in_doc=make_linked_list_of_words_in_doc_index_inverted_index(contant,id);
				index1.add_Doc(new Document (id,words_in_doc,contant));
			}
		}
			catch(Exception e) {
				System.out.println("end of file");
			}
		
	}
	public LinkedList<String> make_linked_list_of_words_in_doc_index_inverted_index(String contant,int id){
			LinkedList<String> words_in_doc = new LinkedList <String>();
			make_index_and_inverted_index(contant, words_in_doc,id);
			return words_in_doc;
			
		}

	public void make_index_and_inverted_index(String contant,LinkedList<String> words_in_doc,int id) {
			contant = contant.replaceAll("\'"," ");
			contant =contant.replaceAll("-", " "); 
			
			contant = contant.toLowerCase().replaceAll("[^a-zA-z0-9 ]", "");
			String[]tokens =contant.split("\\s+");
			num_tokens+=tokens.length;
			
			for (String w : tokens) {
				if(!unique_words.exists(w)) {
					unique_words.insert(w);
				}
			
				if(!existsIn_stop_words(w)) {
					words_in_doc.insert(w);
					inverted.add(w,id);
					invertedBST.add(w,id);
				}
			}
		}
		

	public boolean existsIn_stop_words(String word){

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
	public void Load_all_files(String stop,String file){
		Load_stopWordes(stop);
		Load_all_doc(file);
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
			        Document d=index1.get_Document_given_id(IDs.retrieve());
			        if(d!=null)
			        {
			            System.out.println("Document "+d.id+":"+d.content);
			        }
			        IDs.findNext();
			    }
			    Document d=index1.get_Document_given_id(IDs.retrieve());
			    if(d!=null)
			    {
			        System.out.println("Document "+d.id+":"+d.content);
			    }
			    System.out.println("");
			}
	public static void Test() {
		Driver d = new Driver();
		d.Load_all_files("project\\src\\stop.txt", "project\\src\\dataset.csv");
			Scanner input = new Scanner(System.in);
			int x = 0;
			do { 
				System.out.println("1- Boolean Retrieval.");
				System.out.println("2- Ranked Retrieval.");
				System.out.println("3- Indexed Documents(Number of documents in the index).");
				System.out.println("4- Indexed Tokens.");
				System.out.println("5- Exit.");
				System.out.print("Please select one of the choices above:");
				x=input.nextInt();
				switch (x) {
					case 1:
							input.nextLine();
						System.out.print("Enter a query to retreive:");
							String query = input.nextLine();

						query=query.toLowerCase();
						query=query.replaceAll(" and ", " AND ");
						query=query.replaceAll(" or ", " OR ");

						System.out.println("Which method would you want to make the query:(Enter a number)");
						
								System.out.println("1- Use index");
								System.out.println("2- Use inverted index");
								System.out.println("3- Use BST");
								System.out.println("4- Back");
								int y=input.nextInt();
							do { 

								if(y==1){
										QueryProcessing_from_index query1 = new QueryProcessing_from_index(d.index1);
										System.out.println("#Q " + query);
										LinkedList res1=QueryProcessing_from_index.MixedQuery(query);
											d.display_doc_with_given_IDS(res1);
										break;
								}
								else if(y==2){
									QueryProcessing query1 = new QueryProcessing(d.inverted);
										System.out.println("#Q: " + query);
										LinkedList res1=QueryProcessing.MixedQuery(query);
											d.display_doc_with_given_IDS(res1);
											break;
								}
								else if(y==3){
									QueryProcessing_BST query1 = new QueryProcessing_BST(d.invertedBST);
									System.out.println("#Q: " + query);
									LinkedList res1=QueryProcessing_BST.MixedQuery(query);
										d.display_doc_with_given_IDS(res1);
										break;
								}
								else if(y==4){
									break;
								}
								else {
									System.out.println("Wrong input");
									System.out.println("Which method would you want to make the query:(Enter a number)");
						
								System.out.println("1- Use index");
								System.out.println("2- Use inverted index");
								System.out.println("3- Use BST");
								System.out.println("Back");
								 y=input.nextInt();
								}

							} while (y!=4);
							break;

							case 2:
								input.nextLine();
								System.out.print("Enter a query to rank:");
								String query2 = input.nextLine();
								query2=query2.toLowerCase();
								Ranking r = new Ranking(d.invertedBST,d.index1 ,query2);
								r.insert_sorted_in_list();
								r.display_all_doc_with_score_usingList();
								break;

							case 3:
								System.out.println("num of documents="+d.index1.documents.n); 
							break;
							case 4:
								System.out.println("num of unique words including stop words="+d.unique_words.n);

						break;
					default:
					
				}

			} while (x!=5);
	}
	public static void main(String[] args) {
		Test();
	}


}
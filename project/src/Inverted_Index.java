
public class Inverted_Index {
 
	public LinkedList<Word> inverted_Index ;
	
	public Inverted_Index() {
		inverted_Index = new  LinkedList<Word>() ;
	}
	
	public void add(String text , int id) {
		
		if(!search_Word_in_inverted(text)) {
			Word w = new Word(text);
			w.doc_IDS.insert(w);
		}else {
			Word existing_Word = inverted_Index.retrieve();
			existing_Word.add_Id(id);
		}
		
	}
	public boolean search_Word_in_inverted(String text) {
		
		if(inverted_Index == null || inverted_Index.empty())
		return false;
		
		inverted_Index.findFirst();
		while(!inverted_Index.last()) {
			if(inverted_Index.retrieve().equals(text)) {
				return true;
			}
			inverted_Index.findNext();
			
		}
		if(inverted_Index.retrieve().equals(text)) {
			return true;
		}
		
		return false;
	}
	public void display_Inverted_Index() {
		if(inverted_Index == null){
			System.out.println("null Inverted_Index");
			return;
			}else if(inverted_Index.empty()) {
				System.out.println("empty Inverted_Index");
				return;
			}
		
		inverted_Index.findFirst();
		while(!inverted_Index.last()) {
			inverted_Index.retrieve().display();
			inverted_Index.findNext();
		}
	        inverted_Index.retrieve().display();
		}
}
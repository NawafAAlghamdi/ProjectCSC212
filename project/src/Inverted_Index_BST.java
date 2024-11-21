public class Inverted_Index_BST {

    BST<Word> inverted_index;

    public Inverted_Index_BST(){
        inverted_index = new BST<Word>();
    }
    public void add(String word,int id){
        if(!search_Word_in_inverted(word)){
            Word w = new Word(word);
            w.document_IDs.insert(id);
            inverted_index.insert(word, w);
        }
        else{
            Word existing_word = inverted_index.retrieve();
            existing_word.add_DocID(id);
        }
    }
    public boolean search_Word_in_inverted(String w){    
        return  inverted_index.findkey(w);
    }
    public void display_Inverted_Index() {
		if(inverted_index == null){
			System.out.println("null Inverted_Index");
			return;
			}else if(inverted_index.empty()) {
				System.out.println("empty Inverted_Index");
				return;
			}
            inverted_index.inOrder();
}
}

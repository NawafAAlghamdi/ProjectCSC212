public class Word{
    LinkedList<Integer> document_IDs;
    String word;
    public Word(String word){
        this.word=word;
        document_IDs= new LinkedList<Integer>();
    }
    public void add_DocID(int id){
        if(document_IDs.empty()){
            document_IDs.insert(id);
        }
        document_IDs.findFirst();
        while(!document_IDs.last()){
                if(document_IDs.retrieve().equals(id)){
                    return;
                }
                document_IDs.findNext();
        }
        if(document_IDs.retrieve().equals(id))
            return;

            document_IDs.insert(id);
    }
}
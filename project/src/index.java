public class index{
    LinkedList<Document> documents;
    public index(){
        documents= new LinkedList<Document>();
    }
    public void add_Doc(Document doc){
        documents.insert(doc);
    }
    public void display(){
        if(documents.empty()){
            System.out.println("There are no documents");
        }
        documents.findFirst();
        while(!documents.last()){
              
                System.out.println("ID:" + documents.retrieve().id);
                documents.retrieve().words.display();
                System.out.println();
                documents.findNext();
        }
        
        System.out.println("ID:" + documents.retrieve().id);
        documents.retrieve().words.display();

    }
    public LinkedList<Integer> get_all_documents_given_term(String word){
        LinkedList<Integer> result = new LinkedList<>();
        if(documents.empty()){
            System.out.println("There are no documents");
            return null;

        }
        documents.findFirst();
        while(!documents.last()){
            if(documents.retrieve().words.exists(word.toLowerCase().trim()))
                result.insert(documents.retrieve().id);
                documents.findNext();
        }
        if(documents.retrieve().words.exists(word.toLowerCase().trim()))
                result.insert(documents.retrieve().id);
                return result;
    }
    public Document get_Document_given_id(int id){
        if(documents.empty()){
            System.out.println("There are no documents");
            return null;
        }
        documents.findFirst();
        while(!documents.last()){
            if(documents.retrieve().id==id){
                return documents.retrieve();
            }
            documents.findNext();
        }
        if(documents.retrieve().id==id){
            return documents.retrieve();
        }
        return null;
    }
}
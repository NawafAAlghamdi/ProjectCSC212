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
                documents.findNext();
        }
        
        System.out.println("ID:" + documents.retrieve().id);
        documents.retrieve().words.display();

    }
}
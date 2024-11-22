public class Ranking {
    static String Query;
    static Inverted_Index_BST inverted;
    static index index1;
    static LinkedList<Integer> all_doc_in_query;
    static LinkedList<Doc_Rank> all_doc_ranked;

    public Ranking(Inverted_Index_BST inverted,index index1,LinkedList<Integer> all_doc_in_query,String Query){
        this.Query=Query;
        this.index1=index1;
        this.inverted=inverted;
        all_doc_in_query = new LinkedList<Integer>();
        all_doc_ranked = new LinkedList<Doc_Rank>();
    }
    public static void display_all_doc_with_score_usingList(){
        if(all_doc_ranked.empty()){
        System.out.println("Empty");
            return;
        }
        System.out.println("DocID  Score");
        all_doc_ranked.findFirst();
        while(!all_doc_ranked.last()){
            all_doc_ranked.retrieve().display();
            all_doc_ranked.findNext();
        }
        all_doc_ranked.retrieve().display();
    }
    public static Document get_doc_given_id(int id){
        return index1.get_document_given_id(id);
    }
    public static int term_frequency_in_doc(Document d,String term){
        int freq = 0;
        LinkedList<String> words = d.words;
        if(words.empty())
        return 0;
        words.findFirst();
        while(!words.last()){
            if(words.retrieve().equalsIgnoreCase(term))
                freq++;
                words.findNext();
        }
    }
}




public class Ranking {
    static String Query;
    static Inverted_Index_BST inverted;
    static index index1;
    static LinkedList<Integer> all_doc_in_query;
    static LinkedList<Doc_Rank> all_doc_ranked;

    public Ranking(Inverted_Index_BST inverted,index index1,String Query){
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
        return index1.get_Document_given_id(id);
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
        if(words.retrieve().equalsIgnoreCase(term))
        freq++;
        return freq;
    }
    public static int get_doc_rank_score(Document d,String Query){
        if(Query.length()==0)
            return 0;
            String terms[]=Query.split(" ");
            int sum_freq=0;
            for(int i=0;i<terms.length;i++){
                sum_freq+=term_frequency_in_doc(d,terms[i].trim().toLowerCase());
            }
            return sum_freq;
    }
    public static void RankQuery(String Query){
        LinkedList<Integer> A = new LinkedList<>();
        if(Query.length()==0) return;
        String terms[] = Query.split(" ");
        boolean found=false;
        for(int i=0;i<terms.length;i++){
            found = inverted.search_Word_in_inverted(terms[i].trim().toLowerCase());
            if(found)
            A=inverted.inverted_index.retrieve().document_IDs;
            Adding_in_1_List_sorted(A);
        }
    }
    public static boolean existsIn_result(LinkedList<Integer> result, Integer id) {
        if(result.empty()) return false;
        result.findFirst();
        while (!result.last()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.findNext();
        }
        if (result.retrieve().equals(id)) {
            return true;
        }
        return false;
    }
    public static void Adding_in_1_List_sorted(LinkedList<Integer> A){
        if(A.empty())
        return;
        A.findFirst();
        while(!A.empty()){
            boolean found = existsIn_result( all_doc_in_query, A.retrieve());
            if(!found){
                insert_sorted_Id_list(A.retrieve());
            }
            if(!A.last())
                A.findNext();
                else
                break;
        }
    }
    public static void insert_sorted_Id_list(Integer id){
        if(all_doc_in_query.empty())
        {
            all_doc_in_query.insert(id);
            return;
        }
        all_doc_in_query.findFirst();
        while(!all_doc_in_query.last()){
            if(id<all_doc_in_query.retrieve()){
                Integer id1 = all_doc_in_query.retrieve();
                all_doc_in_query.update(id);
                all_doc_in_query.insert(id1);
                return;
            }
            else
            all_doc_in_query.findNext();
        }
        if(id<all_doc_in_query.retrieve()){
            Integer id1 = all_doc_in_query.retrieve();
            all_doc_in_query.update(id);
            all_doc_in_query.insert(id1);
            return;
        }
        else
        all_doc_in_query.insert(id);
    }
         public static void insert_sorted_in_list(){
            RankQuery(Query);
            if(all_doc_in_query.empty()){
                System.out.println("empty query");
                return;
            }
            all_doc_in_query.findFirst();
            while(!all_doc_in_query.last()){
                Document d = get_doc_given_id(all_doc_in_query.retrieve());
                int Rank = get_doc_rank_score(d, Query);
                insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));
                all_doc_in_query.findNext();
            }
            Document d = get_doc_given_id(all_doc_in_query.retrieve());
            int Rank = get_doc_rank_score(d, Query);
            insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));

         }
         public static void insert_sorted_list(Doc_Rank dr){
            if(all_doc_ranked.empty()){
                all_doc_ranked.insert(dr);
                return;
            }
            all_doc_ranked.findFirst();
            while(!all_doc_ranked.last()){
                if(dr.rank>all_doc_ranked.retrieve().rank){
                    Doc_Rank dr1 = all_doc_ranked.retrieve();
                    all_doc_ranked.update(dr);
                    all_doc_ranked.insert(dr1);
                    return;
                }
                else
                    all_doc_ranked.findNext();
            }
            if(dr.rank>all_doc_ranked.retrieve().rank){
                Doc_Rank dr1 = all_doc_ranked.retrieve();
                all_doc_ranked.update(dr);
                all_doc_ranked.insert(dr1);
                return;
         }
         else
            all_doc_ranked.insert(dr);
        }
    
}


public class QueryProcessing {
	static InvertedIndex inverted;

    public QueryProcessing(InvertedIndex inverted) {
        this.inverted = inverted;
    }

    public static LinkedList<Integer> AndQuery(String Query) {
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        String terms[] = Query.split("AND");

        if (terms.length == 0) return A;

       
        boolean found = inverted.search_word_in_inverted(terms[0].trim().toLowerCase());
        if (found) {
            A = inverted.inverted_index.retrieve().doc_IDS;
        }
        for (int i = 1; i < terms.length; i++) {
             found = inverted.search_word_in_inverted(terms[i].trim().toLowerCase());
            if (found) {
                B = inverted.inverted_index.retrieve().doc_IDS;
            }
            A = AndQuery(A, B);
        }

        return A;
    }
    public static LinkedList<Integer> AndQuery(LinkedList<Integer> A, LinkedList<Integer> B) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if (A.empty() || B.empty()) 
            return result;
        A.findFirst();
        while (true) {
            boolean found = existsIn_result(result, A.retrieve());
            if (!found) { // not found in result
                B.findFirst();
                while (true) {
                    if (B.retrieve().equals(A.retrieve())) {
                        result.insert(A.retrieve());
                        break;
                    }
                    if (!B.last()) 
                        B.findNext();
                    else 
                        break;
                }
            }
            if (!A.last()) 
                A.findNext();
            else 
                break;
        }
        return result;
    }
    
    
}

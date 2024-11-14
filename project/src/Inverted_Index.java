
public class Inverted_Index {
 
	public LinkedList<Document> DocumentID = new LinkedList();;
	private String word;
	
	private void FindDocument(String word) {
		boolean Flag ;
		DocumentID.findFirst();
		while(!DocumentID.last()) {
			Flag = false;
			DocumentID.retrieve().words.findFirst();
			while(!DocumentID.retrieve().words.last()) {
				 if(DocumentID.retrieve().words.retrieve().equalsIgnoreCase(word)) {
					Flag = true;
					break;
				 }
				
				 DocumentID.retrieve().words.findNext();
			}
			 if(DocumentID.retrieve().words.retrieve().equalsIgnoreCase(word)) {
					Flag = true;
					}
			 if(Flag)
				 System.out.print(DocumentID.retrieve().id+",");	
			
			 DocumentID.findNext();
			
		}
		Flag = false;
		DocumentID.retrieve().words.findFirst();
		while(!DocumentID.retrieve().words.last()) {
			 if(DocumentID.retrieve().words.retrieve().equalsIgnoreCase(word)) {
				Flag = true;	
				break;
			 }
			 DocumentID.retrieve().words.findNext();
		}
		if(DocumentID.retrieve().words.retrieve().equalsIgnoreCase(word)) {
			Flag = true;
			}
		if(Flag)
			System.out.print(DocumentID.retrieve().id);
	}
}

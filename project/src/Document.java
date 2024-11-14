
import java.io.File;
import java.util.Scanner;

public class Document{
    LinkedList<String> words = new LinkedList();
    int id;
        public Document(int id,LinkedList<String> words){
            this.id=id;
            this.words=words;

        }

    public static void Read(String fileName){
        String line = null;
        try {
            
            File f=new File(fileName);
            Scanner s = new Scanner(f);

            s.nextLine();
            while(s.hasNextLine()){
                line = s.nextLine();
                  if (line.trim().length()<3) {
                  System.out.println("Empty line found, skipping this line");
                    break;
                    
                 }
                System.out.println(line);
                String x = line.substring(0,line.indexOf(','));
                int id = Integer.parseInt(x.trim());
                 String content = line.substring(line.indexOf(',')+1);
            }



        } catch (Exception e) {
                System.out.println("EOF");
        }
    }
    public static void main(String[] args) {
       Read("project\\src\\dataset.csv");
       
    }
}
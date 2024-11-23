public class Doc_Rank {
    int id;
    int rank;
    public Doc_Rank(int i,int r){
        this.id=i;
        this.rank=r;
    }
    public void display(){
        if(id>=10)
        System.out.println(id + " \t" + rank);
        else
        System.out.println(id + "  \t" + rank);
    }
}


package other;

public class StringHashCode {
    
    public static void main(String[] args) {
        
        String userId = "f8882369892d4cb7b73bd42200f1865c";
        String date = "2019-10-19";
        
        System.out.println(userId.hashCode() ^ date.hashCode());
    }
}

package bytecode;

public class FinalFieldExhibit {
    
    private final String s;
    
    /**
     * category 1
     */
    public FinalFieldExhibit() {
        s = "Hello,World";
    }
    
    public FinalFieldExhibit(int i) {
         s = "Hello,World";
    }
    
    /**
     * category 2
     */
//    {
//        s = "Hello,World";
//    }
}

package data;

public class ShowFactory {
    
    public static ShowFactory getFactory(String type){
        if(type.equals("Puntual")){
            return new PuntualShow(); 
        }else if(type.equals("Multiple")){
            return new MultipleShow();
        }else{
            return new TempShow();
        }
    }
}

package warmup5;

import java.util.Iterator;

public class Professors implements Iterable<String>{
    
    private class ProfessorIterator implements Iterator<String> {

        private Professors profs;
        private int index;
        
        ProfessorIterator(Professors profs){
            this.profs = profs;
            this.index = 0;
        }
       
        
        @Override
        public boolean hasNext() {
            if (this.index < this.profs.names.length) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String next() {
            String prof = "Professor " + this.profs.names[index];
            index++;
            return prof;
        }
  
    }
   
    String[] names;
    
    Professors(String[] names) {
        this.names = names.clone();
    }

    @Override
    public Iterator<String> iterator() {
        return new ProfessorIterator(this);
    }
    
    public static void main(String[] args) {
        String[] names = {
                "Eck",
                "Feldman",
                "Hoyle",
                "Taggart",
            };
        
        Professors profs = new Professors(names);
        
        for (String prof : profs) {
            System.out.println(prof);
        }

    }
    
    

}

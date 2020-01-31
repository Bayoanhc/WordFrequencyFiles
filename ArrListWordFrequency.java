
public 	class ArrListWordFrequency {
	String path;  
    int count;
    
    public ArrListWordFrequency(String path, int count){
    	this.path = path;
    	this.count = count;  
    }
    
    @Override
    public String toString() {
    	return "(filepath=" + this.path + ", counter=" + this.count + ")";
    }

} 

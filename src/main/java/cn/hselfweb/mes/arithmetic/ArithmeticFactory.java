package cn.hselfweb.mes.arithmetic;





public class ArithmeticFactory {
	private static ArithmeticFactory factoryInstance = null;
	
	public static synchronized ArithmeticFactory getInstance() {

	    if (factoryInstance == null)
	    	factoryInstance = new ArithmeticFactory();
	    return factoryInstance;
	}
	
	public  Arithmetic creatArithmetic(String sortName){  
	    	Arithmetic arithmetic = null;  
	        char[] sortname = sortName.toCharArray(); 
	        // jdk 1.7过后switch就支持String类型了。
	        switch (sortname[0]) { 
	        case 'B': // 基于效率函数的排序算法
	        	arithmetic = FirstArithmeticObjectByExtendCraft.getInstance();
	            break;
	        case 'S':
	        	  
	        	//sort = new SelectSort(arrays, range); 
	        	            break;  
	       case 'I':
	        	  
	        	//sort = new InsertSort(arrays, range); 
	        	            break;  
	       case 'M': 
	        	  
	        	//sort = new MergeSort(arrays, range); 
	        	            break;  
	       case 'D': 
//	        	   sort = new SystemSort(arrays, range); 
        	            break;  
	       case 'H': 
	        	  
	        	  //sort = new HeapSort(arrays, range); 
	        	   break; 
	        }  
	        return arithmetic; 
	    } 
	
	//释放实例的方法
    public void releaseFactoryInstance(){
    	if(factoryInstance == null){
    		
    	}else{
    		factoryInstance = null;
    	}
    }
}

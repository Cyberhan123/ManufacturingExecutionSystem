package cn.hselfweb.mes.arithmetic.pojo;



public class GAArithmeticParams extends ArithmeticParams{
   //继承了ArithmeticArgumentInterface接口中公有的参数和方法，在本类中实现一些私有的参数和方法
	//打算做成singleton模式
	
	private static ArithmeticParams instance = null;

    private GAArithmeticParams() {
		super();
	}

    public static synchronized ArithmeticParams getInstance() {

    if (instance == null)
    instance = new GAArithmeticParams();
    return instance;
    }
    
    //释放实例的方法
    public void releaseInstance(){
    	if(instance == null){
    		
    	}else{
    	   instance = null;
    	}
    }
    
    
}




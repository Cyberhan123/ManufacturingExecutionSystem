package cn.hselfweb.mes.arithmetic.pojo;


public class FirstArithmeticParams extends ArithmeticParams{
   //继承了ArithmeticArgumentInterface接口中公有的参数和方法，在本类中实现一些私有的参数和方法
	//打算做成singleton模式
	
	private static ArithmeticParams instance = null;

    private FirstArithmeticParams() {
		super();
	}

	//	public  List<List<ExtendCraft>> list_sum = new ArrayList<List<ExtendCraft>>();// 生成特定工序包含的加工序列集合的集合
//	public  int DeviceNumber = 0;
//	public  List<List<ExtendCraft>> listM_base_sum = new ArrayList<List<ExtendCraft>>();// 生成设备加工序列排序集合的集合
	//获取实例的方法
    public static synchronized ArithmeticParams getInstance() {

    if (instance == null)
    instance = new FirstArithmeticParams();
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




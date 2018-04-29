package zerotop.top.test.parse;

public class ParseString {
	
	public static void main(String [] args){
		String str = "A:程序计数器是一个比较小的内存区域，用于指示当前线程所执行的字节码执行到了第几行,是线程隔离的"
        +"B:虚拟机栈描述的是Java方法执行的内存模型，用于存储局部变量，操作数栈,"
        + "动态链接，方法出口等信息，是线程隔离的"
        +"C:方法区用于存储JVM加载的类信息、常量、静态变量、以及编译器编译后的代码等数据，是线程隔离的"
        +"D:原则上讲，所有的对象都在堆区上分配内存，是线程之间共享的";
		String []strs = str.split(",");
		for(String s:strs){
			System.out.println(s);
		}
	}
}

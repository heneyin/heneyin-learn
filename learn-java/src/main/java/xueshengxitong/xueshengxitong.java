//package xueshengxitong;
//import java.util.Scanner;
//public class xueshengxitong extends main{
//static xueshengxitong[] Stu=new xueshengxitong[100];
//static int number=0;
//static Scanner scanner=new Scanner(System.in);
//private static int setScore;
//	public xueshengxitong(String name, String sex, int english, int javacourse, int math, int num) {
//}
//	public xueshengxitong() {
//	}
//	public static void main(String[] args){
//		System.out.println("*********************************");
//		System.out.println("山东财经大学欢迎你");
//		System.out.println("*********************************");
//		System.out.println("欢迎使用JAVA学生成绩管理系统！！！");
//		System.out.println("*********************************");
//		System.out.println("温馨提示：请用学号查找，修改删除数据！！！ ");
//		System.out.println("*********************************");
//		Scanner reader= new Scanner(System.in);
//		String a;
//		System.out.println("请登陆帐号，按ENTER结束：");
//		a =  (String)reader.next();
//		String z;
//		System.out.println("请输入密码：");
//		z =  (String)reader.next();
//		if( a.equals("java")&&z.equals("java")){
//		System.out.println("以管理员身份登陆，请选择您的操作");
//		System.out.println("增加一个学生信息请按‘1’");
//		System.out.println("增加多个学生信息请按‘2’");
//		System.out.println("删除数据请按‘3’");
//		System.out.println("查找数据请按‘4’");
//		System.out.println("修改数据请按‘5’");
//		System.out.println("统计数据请按‘6’");
//		System.out.println("清除所有数据请按‘7’");
//		System.out.println("把数据全部打印到屏幕请按‘8’");
//		System.out.println("关于作者请按‘9’");
//		System.out.println("查询当前学生总数请按‘#’");
//		System.out.println("查询当前id号码请按‘*’");
//		System.out.println("根据总分排名请按‘@’");
//		System.out.println("修改密码请按‘&’");
//		System.out.println("退出程序请按‘0’");}
//		while(true) {
//		String c =  reader.next();
//		switch(c) {
//		case "1":setlove1();break;
//		case "2":getlove2();break;
//		case "3":love3();break;
//		case "4":love4();break;
//		case "5":love5();break;
//		case "6":ame6();break;
//		case "7":love7();break;
//		case "8":love8();break;
//		case "9":love9();break;
//		case "#":loveq();break;
//		case "*":lovew();break;
//		case "@":lovee();break;
//		case "&":lover();break;
//		case "0":System.exit(0);break;
//			default:System.out.println("错误");
//			}System.out.println("以管理员身份继续");
//	}}
//
//
//
//
//	private static void setlove1(){
//		Scanner reader= new Scanner(System.in);
//
//		String name;
//		System.out.println("请输入姓名：");
//		name =reader.next();
//		String sex;
//		System.out.println("请输入性别：");
//		sex =reader.next();
//		int english;
//		System.out.println("请输入英语成绩：");
//		english =reader.nextInt();
//		int javacourse;
//		System.out.println("请输入java成绩：");
//		javacourse =reader.nextInt();
//		int math;
//		System.out.println("请输入数学成绩：");
//		math =reader.nextInt();
//		int num;
//		System.out.println("请输入学号：");
//		num =reader.nextInt();
//		Stu[number++]=new xueshengxitong(name,sex,english,javacourse,math,num);
//
//
//	}
//	private static void getlove2(){
//		Scanner reader= new Scanner(System.in);
//		System.out.println("你想增加几名学生信息？？");
//		 int b =  reader.nextInt();
//		for(int x=1;x<=b;x++) {
//			String name;
//			System.out.println("请输入姓名：");
//			name =reader.next();
//			String sex;
//			System.out.println("请输入性别：");
//			sex =reader.next();
//			int english;
//			System.out.println("请输入英语成绩：");
//			english =reader.nextInt();
//			int javacourse;
//			System.out.println("请输入java成绩：");
//			javacourse =reader.nextInt();
//			int math;
//			System.out.println("请输入数学成绩：");
//			math =reader.nextInt();
//			int num;
//			System.out.println("请输入学号：");
//			num =reader.nextInt();
//			Stu[number++]=new xueshengxitong(name,sex,english,javacourse,math,num);
//
//		System.out.println("添加成功");
//	}}
//	private static void love3(){
//		System.out.println("请输入学号：");
//		Scanner input=new Scanner(System.in) ;
//		int a=input.nextInt()-1;
//		if(number==0||a>=number)
//			System.out.println("查无此人");
//		else {
//			Stu[a].setName(null);
//			Stu[a].setNum(null);
//			Stu[a].setSex(null);
//			Stu[a].setJavacourse(0);
//			Stu[a].setMath(0);
//			Stu[a].setEnglish(0);
//			Stu[a].setScore=0;
//			System.out.println("删除成功");
//		}
//	}
//    private static void love4(){
//    	System.out.println("请输入学号：");
//    	Scanner reader=new Scanner(System.in);
//    	int i = reader.nextInt()-1;
//    	if(i<0||i>=number)
//    		System.out.print("无此数据");
//    	else {
//    		String a=Stu[i].getNum();
//    		String b=Stu[i].getName();
//    		String c=Stu[i].getSex();
//    		int d =Stu[i].getMath();
//    		int e=Stu[i].getEnglish();
//    		int f=Stu[i].getJavacourse();
//System.out.println("学号："+a+"姓名："+b+"性别："+c+"数学成绩："+d+"英语成绩："+e+"Java成绩："+f);
//	}}
//
//    private static void love5(){
//    	System.out.println("请输入学号：");
//		String xuehao =scanner.next();
//		for(int i=0;i<number;i++){
//			if (xuehao.equals( Stu[i].getNum())){
//    			System.out.println("请输入要修改的学生信息");
//    			System.out.println("请输入姓名：");
//    			Stu[i].setName(scanner.next());
//    			System.out.println("请输入性别：");
//    			Stu[i].setSex(scanner.next());
//    			System.out.println("请输入Java成绩：");
//    			Stu[i].setJavacourse(scanner.nextInt());
//    			System.out.println("请输入数学成绩：");
//    			Stu[i].setMath(scanner.nextInt());
//    			System.out.println("请输入英语成绩：");
//    			Stu[i].setEnglish(scanner.nextInt());
//    			Stu[i].setScore=Stu[number].getJavacourse()+Stu[number].getMath()+Stu[number].getEnglish();
//    			break;
//    		}
//			}}
//
//	private static void ame6(){
//		Scanner reader= new Scanner(System.in);
//		System.out.println("你想统计：1：英语2：数学3：JAVA4:总分5：退出");
//		String qw;
//		qw =  reader.next();
//		switch(qw) {
//		case "1":
//			for(int i=0;i<=number;i++){
//			int sum=0;
//			sum+=Stu[i].getEnglish();
//		System.out.println("英语成绩总分"+sum);
//		int pingjun;
//		pingjun=sum/i;
//		System.out.println("英语成绩总分"+pingjun);
//		}	break;
//		case "2":
//			for(int i=0;i<=number;i++){
//			int sum=0;
//			sum+=Stu[i].getMath();
//		System.out.println("数学成绩总分"+sum);
//		int pingjun;
//		pingjun=sum/i;
//		System.out.println("数学成绩总分"+pingjun);
//		}break;
//		case"3":
//			for(int i=0;i<=number;i++){
//			int sum=0;
//			sum+=Stu[i].getJavacourse();
//		System.out.println("java成绩总分"+sum);
//		int pingjun;
//		pingjun=sum/i;
//		System.out.println("java成绩总分"+pingjun);
//		}break;
//		case"4":
//			for(int i=0;i<=number;i++){
//			int sum=0;
//			int sum1=0;
//			int sum2=0;
//			sum1+=Stu[i].getEnglish();
//			sum2+=Stu[i].getJavacourse();
//			sum+=Stu[i].getMath();
//		System.out.println("总分"+sum+sum1+sum2);
//		}break;
//		default:break;
//		}
//
//	}
//    private static void love7(){
//    	number = 0;
//		System.out.println("数据已清除");
//
//    }
//    private static void love8(){
//		for( int i=0;i<number;i++){
//				System.out.println("学号："+Stu[i].getNum()+"姓名："+Stu[i].getName()+"性别："+Stu[i].getSex()+"数学成绩："+Stu[i].getMath()+"英语成绩："+Stu[i].getEnglish()+"Java成绩："+Stu[i].getJavacourse());
//			}
//	}
//    private static void love9(){
//		System.out.println("作者是 fairy");
//	}
//    private static void loveq(){
//		System.out.println("学生总数是"+number);
//	}
//    private static void lovew(){
//		xueshengxitong.main(null);
//		System.out.println("当前id号码java");
//	}
//    private static void lovee(){
//		int j;
//		xueshengxitong paiming =new xueshengxitong();
//		for(int i=0;i<number;i++){
//			for(j=i+1;j<number;j++)
//				if(Stu[i].getScore()>Stu[j].getScore()){
//					paiming=Stu[i];Stu[i]=Stu[j];Stu[j]=paiming;
//				}
//		}int i = 0;
//		for( i=0;i<number;i++){
//			System.out.println("学号："+Stu[i].getNum()+"姓名："+Stu[i].getName()+"性别："+Stu[i].getSex()+"数学成绩："+Stu[i].getMath()+"英语成绩："+Stu[i].getEnglish()+"Java成绩："+Stu[i].getJavacourse());
//		}
//	}
//
//	private static void lover(){
//		Scanner reader= new Scanner(System.in);
//		String name = null;
//		String inputpass;
//		String newpassword;
//		String repassword;
//		String inputname="";
//		String admin="";
//		String administator="";
//		String password = null;
//		System.out.println("请登陆帐号，按ENTER结束：");
//	Scanner input=new Scanner(System.in);
//	inputname=input.next();
//		System.out.println("请输入密码：");
//	inputpass=input.next();
//	if(inputname.equals(name)&inputpass.equals(password)){
//		System.out.println("请输入新密码");
//		newpassword= input.next();
//		System.out.println("请确认新密码");
//		repassword= input.next();
//		while (!(newpassword.equals(repassword))){
//			System.out.println("两次密码不一致");
//			newpassword= input.next();
//			System.out.println("请确认新密码");
//			repassword= input.next();
//		}System.out.println("修改密码成功，新密码是"+repassword);
//	}
//	}
//}
//
//

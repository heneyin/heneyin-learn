package xueshengxitong;

/**
 * 代表一个学生。
 * @author CCITTYY-
 *
 */
public class Student {
	
	private String id;
	private String name;
	private String sex;
	private int english;
	private int math;
	private int javacourse;
	private int score;
	
	
	/**
	 * 这是一个构造方法，用于构造一个对象
	 * @param id 学号
	 * @param name 姓名
	 * @param sex 性别
	 * @param english 英语成绩
	 * @param javacourse java 成绩
	 * @param math 数学成绩
	 */
	public Student(String id, String name, String sex, int english, int javacourse, int math) {
		// this 本对象自己的变量/字段
		this.name = name;
		this.sex = sex;
		this.english = english;
		this.javacourse = javacourse;
		this.math = math;
		this.id = id;
		this.score = english + math + javacourse;
	}
	
	public String getName() {
		return name;
	}
	
	 public void setName(String name) {
		 this.name=name;
	 }
	 
	 public String getId() {
		 return id;
	 }
	 
	 public void setId(String id) {
		 this.id = id;
	 }
	 
	 public String getSex() {
		 return sex;
	 }
	 public void setSex(String sex) {
		 this.sex=sex;
	 }
	 public int getEnglish() {
		 return english;
	 }
	 public void setEnglish(int english) {
		 this.english=english;
	 }
	 public int getMath() {
		 return math;
	 }
	 public void setMath(int math) {
		 this.math=math;
	 }
	 public int getJavacourse() {
		 return javacourse;
	 }
	 public void setJavacourse(int javacourse) {
		 this.javacourse=javacourse;
	 }
	 public int getScore() {
		 return score;
	 }
	 public void setScore(int score) {
		 this.score=score;
	 }

	@Override
	public String toString() {
		return String.format("%10s%10s%10s%10d%10d%8d%10d", id, name, sex, english, math, javacourse, score);
//		return id + "\t\t" + name + "\t\t" + sex + "\t\t" + english + "\t\t" + math + "\t\t" + javacourse + "\t\t" + score;
	}

}

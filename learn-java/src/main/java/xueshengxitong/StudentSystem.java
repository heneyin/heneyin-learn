package xueshengxitong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 学生系统
 * @author CCITTYY-
 *
 */
public class StudentSystem {
	
	// list， 一堆学生
	private ArrayList<Student> students = new ArrayList<>();
	private String adminName;
	private String adminPwd;
    private final Scanner reader= new Scanner(System.in);
	
	/**
	 * 学生系统构造方法，需要设置管理员姓名与密码。
	 * @param adminName
	 * @param adminPwd
	 */
	public StudentSystem(String adminName, String adminPwd) {
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		// 测试用数据
//		students.add(new Student("0001","qqq", "nan", 123,123,123));
//		students.add(new Student("0002","fff", "nanv", 34,66,77));
//		students.add(new Student("0003","rrr", "n", 88,22,99));
	}


	public void printNotice() {
        System.out.println("*********************************");
        System.out.println("山东财经大学欢迎你");
        System.out.println("*********************************");
        System.out.println("欢迎使用JAVA学生成绩管理系统！！！");
        System.out.println("*********************************");
        System.out.println("温馨提示：请用学号查找，修改删除数据！！！ ");
        System.out.println("*********************************");
    }

    public void printHelp() {
        System.out.println("------------- * -------------- * -------------");
        System.out.println(" ## " + adminName + "同学， 请进行你的表演 ## \n");
        System.out.println(" 增加一个学生信息请按‘1’");
        System.out.println(" 增加多个学生信息请按‘2’");
        System.out.println(" 删除数据请按‘3’");
        System.out.println(" 查找数据请按‘4’");
        System.out.println(" 修改数据请按‘5’");
        System.out.println(" 统计数据请按‘6’");
        System.out.println(" 清除所有数据请按‘7’");
        System.out.println(" 把数据全部打印到屏幕请按‘8’");
        System.out.println(" 关于作者请按‘9’");
        System.out.println(" 查询当前学生总数请按‘#’");
        System.out.println(" 查询当前登录用户请按‘*’");
        System.out.println(" 根据总分排名请按‘@’");
        System.out.println(" 修改密码请按‘&’");
        System.out.println(" 退出程序请按‘0’");
        System.out.println("---------------------------------------------");
    }

    public void printHeader() {
        System.out.printf("%9s%9s%8s%8s%9s%8s%8s\n", "学号", "姓名","性别", "英语", "数学", "Java", "总分");
    }

    public void login() {

        System.out.println("请登陆帐号，按ENTER结束：");
        String username =  (String)reader.next();

        System.out.println("请输入密码：");
        String pwd =  (String)reader.next();

        while ( !checkLoginUser(username, pwd) ){
            System.out.println("验证失败，请重新输入：");
            System.out.println("请登陆帐号，按ENTER结束：");
            username =  (String)reader.next();

            System.out.println("请输入密码：");
            pwd =  (String)reader.next();
        }
        System.out.println("------------------欢迎登陆！！-------------------");
    }

	/**
	 * 检查登录用户的账号密码。
	 * @param userName
	 * @param userPwd
	 * @return true 表示检查通过。
	 */
	public boolean checkLoginUser(String userName, String userPwd) {
		return userName != null && userName.equals(this.adminName) && userPwd != null && userPwd.equals(this.adminPwd);
	}


    public void process() {
        while(true) {

            String c =  reader.next();

            switch(c) {
                case "1" :
                    goAdd();
                    break;
                case "2":
                    goAddMutil();
                    break;
                case "3":
                    goDelete();
                    break;
                case "4":
                    goFind();
                    break;
                case "5":
                    goChange();
                    break;
                case "6":
                    goStaitc();
                    break;
                case "7":
                    clearData();
                    System.out.println("清除成功");
                    break;
                case "8":
                    printAllData();
                    break;
                case "9":
                    System.out.println("*******************************");
                    System.out.println("********* 作者是 fairy *********");
                    System.out.println("*******************************");
                    break;
                case "#":
                    System.out.println("学生总数为: " + students.size());
                    break;
                case "*":
                    System.out.println("当前登录用户为： " + this.adminName);
                    break;
                case "@":
                    printOrderByScore();
                    break;
                case "&":
                    System.out.println("请输入新密码");
                    String pwd = reader.next();

                    while (pwd == null || "".equals(pwd)) {
                        System.out.println("请重新输入！");
                    }
                    this.adminPwd = pwd;
                    System.out.println("修改成功！");
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.err.println("错误");
            }
            printHelp();
        }
    }



    private void goAdd() {
        Student student = buildStudentFromReader();
        addOne(student);
    }

    private void goAddMutil() {
        System.out.println("你想增加几名学生信息？？");
        int count = reader.nextInt();
        for (int i = 1; i <= count; i++) {
            System.out.println("开始增加第 " + i + " 位学生");
            goAdd();
        }
        System.out.println(count + " 位学生信息添加完毕！" );
    }

    private void goDelete() {
        System.out.println("请输入所删除学生的学号");
        String id = reader.next();
        removeOne(id);
    }


    private void goFind() {
        System.out.println("请输入所查找学生的学号");
        String id = reader.next();
        Student one = findOne(id);
        if (one != null) {
            System.out.println("找出的学生信息为：\n");
            printHeader();
            System.out.println(one);
        }
    }

    private void goChange() {
        System.out.println("请输入所修改学生的学号");
        String id = reader.next();

        if (!isExists(id)) {
            System.err.println("修改学生信息失败：该学生不存在！");
        }
        Student student = buildStudentFromReader(id);
        changeOne(student);
        System.out.println("修改成功！");
    }

    private void goStaitc() {
        System.out.println("请输入统计类型:  1:英语\t2:数学\t3:Java\t4:总分 ");
        int type = reader.nextInt();
        printStatistic(type);
    }






    /**
     * 使用输入构建一个学生。
     * @return
     */
    public Student buildStudentFromReader(String id) {
        System.out.println("请输入姓名：");
        String name = reader.next();

        System.out.println("请输入性别：");
        String sex = reader.next();

        System.out.println("请输入英语成绩：");

        int english = getGrade();


        System.out.println("请输入java成绩：");
        int javacourse = getGrade();


        System.out.println("请输入数学成绩：");
        int math = getGrade();

        if (id == null) {
            System.out.println("请输入学号：");
            id =reader.next();
        }

        return new Student(id, name, sex, english, javacourse, math);
    }

    private int getGrade() {
        int grade = -1;
        while (grade < 0) {
            try {
                String gradeStr =reader.next();
                grade = Integer.parseInt(gradeStr);
            } catch (Exception e) {
                grade = -1;
            }

            if (grade < 0) {
                System.err.println("成绩格式不符合要求，必须为自然数，请重新输入：");
            }
        }
        return grade;
    }

    public Student buildStudentFromReader() {
        return buildStudentFromReader(null);
    }

	/**
	 * 检查学生是否存在。
	 * @param id 学号。
	 * @return true 为存在。
	 */
	public boolean isExists(String id) {
		
		if (null == id || "".equals(id)) {
			return false;
		}
		
		// 检查学生在系统中是否存在。
		for(Student s : students) {
			String inId = s.getId();
			if( id.equals(inId) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isExists(Student student) {
		return null != student && isExists(student.getId());
	}

	/**
	 * 向系统中添加一个学生对象。
	 * @param student 
	 */
	public boolean addOne(Student student) {
		
		if (isExists(student)) {
			System.err.println("添加的学生失败：该学生已经存在！");
			return false;
		}
		
		// 这里就加到了 list 里。不用维护下标什么的。
		boolean result =  students.add(student);
		
		if (result) {
			System.out.println("添加学生成功！");
		} else {
			System.out.println("添加学生失败！");
		}
		return result;
		
	}
	
	/**
	 * 删除一个学生。 使用 id 删除。
	 * @param id
	 */
	public void removeOne(String id) {
		
		if (null == id) {
			System.err.println("删除学生失败：学生id不能为空");
			return;
		}
		
		for(Student s : students) {
			String inId = s.getId();
			if( id.equals(inId) ) {
				students.remove(s);
				System.out.println("删除学生成功！");
				return;
			}
		}
		
		System.err.println("删除学生失败：该学生不存在！");
	}
	
	
	/**
	 * 使用 学号 查找学生。
	 * @param id 学号
	 * @return null 表示没有找到。
	 */
	public Student findOne(String id) {
		
		if (null == id) {
			System.err.println("查询学生失败：学生id不能为空");
			return null;
		}
		
		for(Student s : students) {
			String inId = s.getId();
			if( id.equals(inId) ) {
				return s;
			}
		}
		
		System.err.println("查询学生失败：该学生不存在！");
		return null;
	}
	
	/**
	 * 修改学生信息。
	 * @param student
	 * @return true 为修改成功。
	 */
	public boolean changeOne(Student student) {

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            String inId = s.getId();
            if( student.getId().equals(inId) ) {
                students.set(i, student);
                System.out.println("修改学生信息成功。");
                return true;
            }
        }

		for(Student s : students) {

		}
		return false;
	}
	
	/**
	 * 打印成绩统计结果，
	 * @param type 统计类型，1：英语    2；数学   3：JAVA   4:总分 
	 */
	public void printStatistic(int type) {
		if (type < 1 || type > 4 ) {
			System.err.println("请输入正确类型  1:英语\t2:数学\t3:Java\t4:总分 ");
		}
		int total = 0;
		String course = "";
		// List 长度。
		int size = this.students.size();
		switch (type) {
			case 1:
				for(Student s : students) {
					total += s.getEnglish();
				}
				course = "英语";
				break;
			case 2:
				for(Student s : students) {
					total += s.getMath();
				}
				course = "数学";
				break;
			case 3:
				for(Student s : students) {
					total += s.getJavacourse();
				}
				course = "Java";
				break;
			case 4:
				for(Student s : students) {
					total += s.getEnglish();
					total += s.getMath();
					total += s.getJavacourse();
				}
				course = "总";
				break;
		}
		
		double avg = total / (double) size;
		System.out.println(course + "成绩总分: " + total);
		System.out.printf(course + "成绩平均分: %.2f\n", avg);  // 0.2 意思保留两位小数。
	}
	
	/**
	 * 清除数据。
	 */
	public void clearData() {
		students.clear();
	}
	
	public void printAllData() {
	    if (students.size() == 0) {
            System.err.println("没有发现任何数据。");
            return;
        }
        printHeader();
		for(Student s : students) {
			System.out.println(s);
		}
	}

	public void printOrderByScore() {

	    Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getScore() - o1.getScore();
            }
        });
	    printAllData();
    }

    public void close() {
	    if (null != reader) {
            reader.close();
        }
    }

}

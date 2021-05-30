package xueshengxitong;


public class StudentSystemMain {

    public static void main(String[] args) {

        StudentSystem system = new StudentSystem("java", "java");

        system.printNotice();

        system.login();

        system.printHelp();

        system.process();

        system.close();


    }


}

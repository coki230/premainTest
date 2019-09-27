import instrumentation.TransClass;

public class TestMainInJar {
    /**
     * VM 参数如下
     * -javaagent:D:/work/premainTest/out/artifacts/premainTest_jar/premainTest.jar
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(new TransClass().getNum());
    }
}

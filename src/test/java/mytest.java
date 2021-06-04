/**
 * @auther shkstart
 * @date 2021/5/29 - 21:34
 */
public class mytest {

    public static void main(String[] args) {
        String s = "";
        while(s.length()<6)
            s+=(int)(Math.random()*10);

        System.out.println(s);
    }
}

/**
 * @author 王俊杰
 * @date 2019/1/8 21:41
 */
public class StringReverse {
    /**
     * 翻转字符串（逆序遍历）
     * @param s
     * @return
     */
    private String reverseString(String s){

        char[] tos = new char[s.length()];
        int i = 0;
        for (int index = s.length() -1;index >= 0;index--){
            tos[i]  = s.charAt(index);
            i++;
        }
        return String.valueOf(tos);
    }

    public static void main(String[] args) {
        StringReverse stringReverse = new StringReverse();
        String s = "A man, a plan, a canal: Panama";
        System.out.println(stringReverse.reverseString(s));
    }
}

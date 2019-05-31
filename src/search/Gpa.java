package search;

import edu.princeton.cs.algs4.ST;

public class Gpa {
    public static void main(String[] args) {
        //创建符号表，使用官方API
        ST<String,Double> st = new ST<>();
        //手动添加内容，字典
        st.put("A",  4.00);
        st.put("B",  3.00);
        st.put("C",  2.00);
        st.put("D",  1.00);
        st.put("F",  0.00);
        st.put("A+", 4.33);
        st.put("B+", 3.33);
        st.put("C+", 2.33);
        st.put("A-", 3.67);
        st.put("B-", 2.67);

        //模拟输入
        String[] input = {"A","A+","C","D"};
        int length = input.length;
        double totalGrade = 0;
        for (String s : input){
            Double score = st.get(s);
            totalGrade += score;
        }
        double averageScore = totalGrade/length;

        System.out.println("平均分： " + averageScore);
    }




}

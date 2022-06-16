package com.xccaia;

public class FinalllyTest {


    public static void main(String[] args) {
        System.out.println(finallyTest1(null));
        System.out.println(finallyTest1("0"));
        System.out.println(finallyTest1(""));

        System.out.println(finallyTest2(null));
        System.out.println(finallyTest2("0"));
        System.out.println(finallyTest2(""));
    }

    public static int finallyTest1(String str) {

        try {
            return str.charAt(5);
        } catch (NullPointerException e) {
            System.out.println("执行NullPointerException ");
            return 1;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("执行IndexOutOfBoundsException ");
            return 2;
        } catch (Exception e) {
            System.out.println("执行IException ");
            return 3;
        } finally {
            System.out.println("执行finally");
            return 4;
        }
    }

    public static int finallyTest2(String str) {
        int i = 1;
        try {
            return str.charAt(0);
        } catch (NullPointerException e) {
            System.out.println("执行NullPointerException ");
            return i;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("执行IndexOutOfBoundsException ");
            i = 2;
            return i;
        } catch (Exception e) {
            System.out.println("执行IException ");
            i = 3;
            return i;
        } finally {
            System.out.println("执行finally    i=" + i);
            return 4;
        }
    }


}

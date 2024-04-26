package lab2;

public class ClassWithMethods {
    @MyAnnotation(1)
    public void method1() {
        System.out.println("public method1");
    }
    public void method2() {
        System.out.println("public method2");
    }
    @MyAnnotation(1)
    protected void method3(int a, String b, int c) {
        System.out.println("protected method3");
    }
    @MyAnnotation(-1)
    protected void method4() {
        System.out.println("protected method4");
    }
    @MyAnnotation(2)
    private void method5() {
        System.out.println("private method5");
    }
    @MyAnnotation(3)
    private void method6(byte a, short b, int c, long d, float e, double f, boolean g, char h,
                         byte[] i, short[] j, int[] k, long[] l, float[] m, double[] n, boolean[] o, char[] p,
                         Object q, Object[] r) {
        System.out.println("private method6");
    }
}

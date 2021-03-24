package page_202.designpatterns.lsp;

public class Lsp {

    static P p = new P();

    static class T {
        public void doSomething() {
            System.out.println("T doSomething ########");
        }
    }

    static class S extends T {
        public void doSomething() {
            System.out.println("S doSomething @@@@@@@@@@");
        }
    }

    static class P {
        public void doSomething(T p) {
            p.doSomething();
        }
    }

    public static void main(String[] args) {

        T t = new T();
        S s = new S();

        Lsp.p.doSomething(t);
        Lsp.p.doSomething(s);

    }
}

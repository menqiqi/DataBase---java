package www.nice.java;

interface ISubject{
    void buyComputer(); //核心功能是买电脑
}

class RealSubject implements ISubject{
    public void buyComputer(){
        System.out.println("买一台外星人电脑");
    }
}

class ProxSubject implements ISubject{
    private ISubject subject; //真正的操作业务
    public ProxSubject(ISubject subject){
        this.subject = subject;
    }
    public void produceComputer(){
        System.out.println("生产外星人电脑");
    }
    public void afterSale(){
        System.out.println("外星人电脑售后团队");
    }
    public void buyComputer(){
        this.produceComputer();
        this.subject.buyComputer();
        this.afterSale();
    }
}

class Factory{
    public static ISubject getInstance(){
        return new ProxSubject(new RealSubject());
    }
}

public class Test1 {
    public static void main(String[] args) {
        ISubject subject = Factory.getInstance();
        subject.buyComputer();
    }
}

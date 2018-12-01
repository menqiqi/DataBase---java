/**
 * 链表实现栈的基本操作
 */

interface IStack{
    //栈的初始化
    void init();
    //栈的销毁
    void destroy();
    //入栈
    void push(Object data);
    //出栈
    void pop();
    //获得栈顶元素
    Object getTop();
    //删除指定位置栈中的元素
    //void deleteLocateElem(int i);
    //获取指定位置栈中的元素
    Object getLocateElem(int i);
    //获取栈的大小
    int getSize();
    //判断是否为空栈
    boolean isEmpty();
    //遍历栈
    void print();
}

class IStackimpl implements IStack{
    //栈底指针
    private Node base;
    //栈顶指针
    private Node top;
    //栈的当前大小
    private int size;

    //-------------------------------------------------
    private class Stack{
        private Object data;
        private Node next;

        public Stack(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    //------------------------------------------------

    @Override
    public void init() {
        size = 0;
    }

    @Override
    public void destroy() {
        top = base = new Node(null,null);
        size = 0;
    }

    @Override
    public void push(Object data) {
        Node newNode = new Node(data,null);
        if(top == null){
            top = base = newNode;
            size++;
        }else{
            newNode.next = top;
            top = newNode;
            size++;
        }
    }

    @Override
    public void pop() {
        //只能从栈顶开始出栈
        top = top.next;
        size--;
    }

    @Override
    public Object getTop() {
        return top.data;
    }

    @Override
    public Object getLocateElem(int i) {
        if(i<=0 || i>size){
            return null;
        }else if(i == 1){
            //获得栈底元素
            return base.data;
        }else if(i == size){
            //获得栈顶元素
            return top.data;
        }else{
            //获得普通元素，位于栈底和栈顶之间的元素
            //因为栈底是尾节点，要使用快慢指针找到要返回的节点
            Node fast = top;
            Node slow = top;
            int count = i;
            while(count != 0){
                count--;
                fast = fast.next;
            }
            while(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
            return slow.data;
        }
    }

    //因为栈是先入后出的，所以不能删除任意下标的节点
   /* @Override
    public void deleteLocateElem(int i) {
        if(i<=0 || i>size){
            //无效下标
            return;
        }else if(i == 1){
            //删除栈底元素
            Node prev = null;
            for(Node tmp = top; tmp != base; tmp = tmp.next){
                prev = tmp;
            }
            prev.next = null;
            base = prev;
            size--;
        }else if(i == size){
            //删除栈底元素
            pop();
        }else{
            //删除中间的普通元素
            //因为栈底是尾节点，要使用快慢指针找到要删除的节点
            Node fast = top;
            Node slow = top;
            Node tmp = null;
            int count = i;
            while(count != 0){
                count--;
                fast = fast.next;
            }
            while(fast != null){
                fast = fast.next;
                tmp = slow;
                slow = slow.next;
            }
            //slow就是要删除的节点，tmp就是要删除节点的前一个
            tmp.next = slow.next;
            slow.next = null;
            size--;
        }
    }*/

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void print() {
        //从栈顶开始打印
        for(int i = size; i > 0; i--){
            System.out.println(this.getLocateElem(i));
        }
        /*for(Node tmp = top; tmp != null; tmp = tmp.next){
            System.out.println(tmp.data);
        }*/
    }
}

public class Test {
    public static void main(String[] args) {
        IStackimpl stack = new IStackimpl();
        stack.init();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.getTop());
        System.out.println(stack.getLocateElem(7));
        stack.print();
        stack.destroy();
    }
}

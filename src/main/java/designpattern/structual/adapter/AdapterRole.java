package designpattern.structual.adapter;

/*
https://www.runoob.com/design-pattern/adapter-pattern.html
https://blog.csdn.net/chenweiaiyanyan/article/details/78052251  类图
老接口的改造，在不影响旧版本使用的情况下，加入新的功能
源角色SrcRole：一般是一个实现类
目标角色Target：需要给用户用的新接口
适配器角色AdapterRole：

对于类适配器：
    AdapterRole继承SrcRole并实现Target
对于对象适配器：
    AdapterRole依赖SrcRole对象并实现Target
都是Target t=new AdapterRole()
 */
public class AdapterRole extends SrcRole implements TargetRole {
    @Override
    public void oldFunction() {
        super.oldFunction();
    }

    @Override
    public void newFunction() {
        System.out.println("新角色的newFunction");
    }

    public static void main(String[] args) {
        //----------互不影响-----------
        TargetRole targetRole=new AdapterRole();
        targetRole.oldFunction();
        targetRole.newFunction();
    }
}

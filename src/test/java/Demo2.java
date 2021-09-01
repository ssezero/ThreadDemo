import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName Demo2
 * @Description TODO
 * @Author Neal
 * @Date 2021/8/19 14:14
 * @Version 1.0
 **/

public class Demo2 {

    public static void main(String[] args) {
        ClassRoom room1=new ClassRoom("科学教室");
        ClassRoom room2=new ClassRoom("图书教室");
        Student sutdent=new Student(room1,"张三");
        System.out.println(sutdent);
        Student student2=sutdent;
        student2.setRoom(room2);
        System.out.println(sutdent);

    }
}

@Data
@AllArgsConstructor
class Student{
    private ClassRoom room;
    private String name;
}
@Data
@AllArgsConstructor
class  ClassRoom{
    private String rommNum;
}

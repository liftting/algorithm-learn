### jvm class
@(java后端server)[jvm, class, 字节码, JVM]

#### class文件结构
##### class 数据结构
1，下面是基础的java代码
``` java
package com.xm.ui.ndkso;
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("this is in main method HelloWorld~");
    }
}
```
2，class文件分析
class文件是一个以8位-1个字节为基础单位的二进制流文件，各个数据项目紧凑的排列在一起，数据项中间没有分隔符，class只包含两种数据结构
`无符号数` u1 1字节 ，u2  ，u4  u8
`表` 其实是由多个无符号数和其他表混合而成的数据结构，如 cp_info  field_info  method_info  attribute_info

###### magic version
2.1，头4个字节为文件的魔数，CA FE BA BE ,JVM用这个值来确定这个文件是否能被接收，很多存储都是利用魔数而不是文件后缀名来确定文件格式的，如图片，因后缀名可以被任意修改，
2.2，紧跟着后四个字节，为编译版本号，00 00 00 34- jdk1.8
###### 常量池
在版本号后边是常量池，因为其大小是不固定的，所以有 u2 大小的容量计数值， 00 1D = 29（因为是从1开始的，有28个常量，见下面的信息即可看到）1-28，常量池中存放着一般包括：
1，类和接口的全包名
2，字段名称和描述符（类型信息）
3，方法名称和描述符

在常量池中，每一个常量字段都是一个表 类型，如 NameAndType 其可能占用多个字节，下面是每个结构占用大小
见 JVM常量池结构图文章

##### javap class
3，当我们用javac 将java文件编译称为 JVM的class文件后，使用javap 可以将编译的class文件反编成指令信息
``` java
Classfile /Users/wm/git-code/git-osc-code/xmAndroidCore/android-mine/XmAndroidCore/ui/src/main/java/com/xm/ui/ndkso/HelloWorld.class
  Last modified 2017-5-18; size 464 bytes
  MD5 checksum 6f29a448c49a81fe4535fe05fca9dd16
  Compiled from "HelloWorld.java"
public class com.xm.ui.ndkso.HelloWorld
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #6.#15         // java/lang/Object."<init>":()V
   #2 = Fieldref           #16.#17        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = String             #18            // this is in main method HelloWorld~
   #4 = Methodref          #19.#20        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #5 = Class              #21            // com/xm/ui/ndkso/HelloWorld
   #6 = Class              #22            // java/lang/Object
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               main
  #12 = Utf8               ([Ljava/lang/String;)V
  #13 = Utf8               SourceFile
  #14 = Utf8               HelloWorld.java
  #15 = NameAndType        #7:#8          // "<init>":()V
  #16 = Class              #23            // java/lang/System
  #17 = NameAndType        #24:#25        // out:Ljava/io/PrintStream;
  #18 = Utf8               this is in main method HelloWorld~
  #19 = Class              #26            // java/io/PrintStream
  #20 = NameAndType        #27:#28        // println:(Ljava/lang/String;)V
  #21 = Utf8               com/xm/ui/ndkso/HelloWorld
  #22 = Utf8               java/lang/Object
  #23 = Utf8               java/lang/System
  #24 = Utf8               out
  #25 = Utf8               Ljava/io/PrintStream;
  #26 = Utf8               java/io/PrintStream
  #27 = Utf8               println
  #28 = Utf8               (Ljava/lang/String;)V
{
  public com.xm.ui.ndkso.HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 7: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String this is in main method HelloWorld~
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 10: 0
        line 11: 8
}
```
上面其实是javap工具将二进制信息编译成一个可视化的信息，而class文件真正是个二进制文件，

#### java操作指令
##### 变量
在函数中定义的局部变量，和 类中定义的字段变量

###### 局部变量
1，JVM是基于栈结构的，当方法的调用都会在栈中产生一个`帧`，而一个栈中包含一组局部变量表，这个局部变量表记录着方法在执行过程中所需要的所有变量(包含this引用，该方法所有参数，其他局部定义的变量)，
局部变量包含基础的类型变量(boolean byte char long short int float double) reference(引用)+returnAddress(返回地址)

2，当一个java文件被编译成class文件后，其函数操作指令其实已经被编译好了，通过javap可以查看函数的操作指令，
``` java
int i = 5;
javap 后指令就是
0: bipush      5
2: istore_0
```
`bipush`:用于把一个byte作为一个int整型值放入操作数栈中，5即被加入操作数栈中
`istore_0`:istore_<n>的一组操作码中的一个，这组操作码用于把int整型值存储到局部变量中。<n>指示了局部变量表中的存储位置，取值只能是0、1、2或者3，
这段代码执行时，首先 bipush 会将5这个数值放入到 操作数栈中，然后 istore_0 指令则是将操作数栈中的数字取出来，放到局部变量表中，

###### java 定义成员变量
下面是类的成员变量初始化时，简单的类代码：
``` java
public int simpleField = 100;
```
javap -verbose 字节码分析
``` java
Classfile /Users/wm/git-code/algorithm/algorithm-learn/jvm/SimpleClass.class
  Last modified 2017-5-22; size 242 bytes
  MD5 checksum 0f6bfdde3856316225c9f9b4a460d330
  Compiled from "SimpleClass.java"
public class SimpleClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#13         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#14         // SimpleClass.simpleField:I
   #3 = Class              #15            // SimpleClass
   #4 = Class              #16            // java/lang/Object
   #5 = Utf8               simpleField
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               SourceFile
  #12 = Utf8               SimpleClass.java
  #13 = NameAndType        #7:#8          // "<init>":()V
  #14 = NameAndType        #5:#6          // simpleField:I
  #15 = Utf8               SimpleClass
  #16 = Utf8               java/lang/Object
{
  public int simpleField;
    descriptor: I
    flags: ACC_PUBLIC

  public SimpleClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: bipush        100
         7: putfield      #2                  // Field simpleField:I
        10: return
      LineNumberTable:
        line 5: 0
        line 7: 4
}
SourceFile: "SimpleClass.java"
```
上面是 javap分析的字节码信息，分析下一些指令
`aload_0`:从局部变量表中的 指定位置 装载出来一个对象引用到操作数栈的栈顶位置，
上面的例子中虽然没写构造函数，但是成员变量初始化实际也是在编译器默认构造函数中执行的，所以第一个变量实际指向的是 this

`invokespecial`:这指令用于调用实例的初始化方法，包括私有方法以及当前类的父类方法。它同样属于一组以不同方式来调用方法的操作码，
这些操作码包括invokedynamic、invokeinterface、invokespecial、invokestatic和invokevirtual。invokespecial是用于调用父类构造方法的指令，例如java.lang.Object的构造方法。

`bipush`: 把一个int的值放入到操作数栈中，

`putfield`:首先，从常量池中获取到这个成员变量的引用， 然后，从操作数栈中弹出一个成员变量的值 和 其对应的是哪个对象，
例子中，首先得到一个引用 `#2 = Fieldref`，然后从操作数栈中弹出 100 和 this,最终这个字段就被赋值为100


###### java 类常量
使用了final修饰
``` java
public final int simpleField = 100;

Constant pool:
   ... 没变化 对上面的code
{
  public final int simpleField;
    descriptor: I
    flags: ACC_PUBLIC, ACC_FINAL
    ConstantValue: int 100

  public FinalSimpleClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: bipush        100
         7: putfield      #2                  // Field simpleField:I
        10: return
      LineNumberTable:
        line 5: 0
        line 6: 4
}
SourceFile: "FinalSimpleClass.java"

```
看上面编译的字节码，Code部分是没有变化，变化的是字段的描述属性
flags: ACC_PUBLIC, ACC_FINAL     ConstantValue: int 100

###### java 类静态变量 static
``` java
public class StaticFieldClass {
    public static int age = 10;
}
编译的字节码 ----
public class StaticFieldClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#14         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#15         // StaticFieldClass.age:I
   #3 = Class              #16            // StaticFieldClass
   #4 = Class              #17            // java/lang/Object
   #5 = Utf8               age
   #6 = Utf8               I
   #7 = Utf8               <init>
   #8 = Utf8               ()V
   #9 = Utf8               Code
  #10 = Utf8               LineNumberTable
  #11 = Utf8               <clinit>
  #12 = Utf8               SourceFile
  #13 = Utf8               StaticFieldClass.java
  #14 = NameAndType        #7:#8          // "<init>":()V
  #15 = NameAndType        #5:#6          // age:I
  #16 = Utf8               StaticFieldClass
  #17 = Utf8               java/lang/Object
{
  public static int age;
    descriptor: I
    flags: ACC_PUBLIC, ACC_STATIC

  public StaticFieldClass(); //类函数描述，下面是执行的描述和 代码
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0

  static {};   // 静态区间设置字段
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: bipush        10
         2: putstatic     #2                  // Field age:I
         5: return
      LineNumberTable:
        line 7: 0
}
SourceFile: "StaticFieldClass.java"

```
多了一个 static {}; flags: ACC_STATIC putstatic 指令原理类似类成员变量的赋值

##### 流控制
``` java
public class ContorlClass {
    public int getMore(float floatOne, float floatTwo) {
        int result;
        if (floatOne > floatTwo) {
            result = 1;
        } else {
            result = 2;
        }
        return result;
    }
    public int simpleSitch(int key) {
        switch (key) {
            case 0:
                return 3;
            case 1:
                return 2;
            case 4:
                return 1;
            default:
                return -1;
        }
    }
    public void whileLoop() {
        int i = 0;
        while (i < 2) {
            i++;
        }
    }
    public void forLoop() {
        for(int i = 0; i < 2; i++) {
            //do nothing
        }
    }
    public void doWhileLoop() {
        int i = 0;
        do {
            i++;
        } while (i < 2);
    }
}
==================
public class ContorlClass
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#18         // java/lang/Object."<init>":()V
   #2 = Class              #19            // ContorlClass
   #3 = Class              #20            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               getMore
   #9 = Utf8               (FF)I
  #10 = Utf8               StackMapTable
  #11 = Utf8               simpleSitch
  #12 = Utf8               (I)I
  #13 = Utf8               whileLoop
  #14 = Utf8               forLoop
  #15 = Utf8               doWhileLoop
  #16 = Utf8               SourceFile
  #17 = Utf8               ContorlClass.java
  #18 = NameAndType        #4:#5          // "<init>":()V
  #19 = Utf8               ContorlClass
  #20 = Utf8               java/lang/Object
{
  public ContorlClass();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0

```
###### 比较
javap 编译出来的 关于变量比较的字节码
``` java
public int getMore(float, float);
    descriptor: (FF)I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=3
         0: fload_1
         1: fload_2
         2: fcmpl
         3: ifle          11
         6: iconst_1
         7: istore_3
         8: goto          13
        11: iconst_2
        12: istore_3
        13: iload_3
        14: ireturn
      LineNumberTable:
        line 9: 0
        line 10: 6
        line 12: 11
        line 14: 13
      StackMapTable: number_of_entries = 2
        frame_type = 11 /* same */
        frame_type = 252 /* append */
          offset_delta = 1
          locals = [ int ]
```
fload_1  fload_2 这字节码将两个参数放入的操作数栈中，  fcmpl 进行比较， ifle 指令将比较的结果判定，如果小于等于0  跳转到 11 位置，否则，继续执行 6的位置，当执行到8后，goto 到13指令，
iload_3 将局部变量表中的地三个位置变量放入到操作数栈中，用return返回结果
istore_3 将操作数栈值存储到 局部变量表中第三位置
ireturn  pop操作数栈，栈顶

``` java
 public int simpleSitch(int);
    descriptor: (I)I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=2, args_size=2
         0: iload_1
         1: tableswitch   { // 0 to 4
                       0: 36
                       1: 38
                       2: 42
                       3: 42
                       4: 40
                 default: 42
            }
        36: iconst_3
        37: ireturn
        38: iconst_2
        39: ireturn
        40: iconst_1
        41: ireturn
        42: iconst_m1
        43: ireturn
      LineNumberTable:
        line 18: 0
        line 20: 36
        line 22: 38
        line 24: 40
        line 26: 42
      StackMapTable: number_of_entries = 4
        frame_type = 36 /* same */
        frame_type = 1 /* same */
        frame_type = 1 /* same */
        frame_type = 1 /* same */
```
iload_1 从局部变量表获取第一个位置数到操作数栈中
tableswitch 配置switch跳转指令表，不同逻辑执行不同指令
`iconst`:int取值0~5时JVM采用iconst_0、iconst_1、iconst_2、iconst_3、iconst_4、iconst_5指令将常量压入栈中
`iconst_m1`: int为-1时，将常量入栈
`bipush`:当int取值-128~127时，JVM采用bipush指令将常量压入栈中
`sipush`:当int取值-32768~32767时，JVM采用sipush指令将常量压入栈中。
`ldc`:当int取值-2147483648~2147483647时，JVM采用ldc指令将常量压入栈中，ldc指令是从常量池中获取值的，

###### 循环
``` java
 public void whileLoop();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0   0入到栈中
         1: istore_1    栈一位置入局部变量表 1位置
         2: iload_1      局部变量表1加载到栈中
         3: iconst_2      创建2常量值到栈中
         4: if_icmpge     13          比较栈 1 和 2 的值 ，1位置是否大于2 是跳转到13  (会将两个数都出栈操作)
         7: iinc          1, 1          直接修改1位置值而不用到栈中草种(局部变量表1 位置就是 i == i++)
        10: goto          2
        13: return
      LineNumberTable:
        line 31: 0
        line 32: 2
        line 33: 7
        line 35: 13
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 10 /* same */

  public void forLoop();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: iload_1
         3: iconst_2
         4: if_icmpge     13
         7: iinc          1, 1
        10: goto          2
        13: return
      LineNumberTable:
        line 38: 0
        line 41: 13
      StackMapTable: number_of_entries = 2
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 10

  public void doWhileLoop();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: iinc          1, 1
         5: iload_1
         6: iconst_2
         7: if_icmplt     2
        10: return
      LineNumberTable:
        line 44: 0
        line 46: 2
        line 47: 5
        line 48: 10
      StackMapTable: number_of_entries = 1
        frame_type = 252 /* append */
          offset_delta = 2
          locals = [ int ]

```
通过第一个循环可以分析对应的字节码指令操作，后面两种循环和第一个基本一致，
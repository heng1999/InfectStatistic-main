## 代码风格

- 缩进

  缩进的空格数为 4 个

- 变量命名

  + 命名尽量使用英文单词
    - 若不熟悉英文单词则用中文拼音代替，拼音字数不超过6个，简写拼音则使用首字母，例如耗油量hyl
    - 较短的单词可通过去掉“元音”形成缩写，较长的单词可取单词的头几个字母形成缩写
  + 命名基本都为小写
    - 若变量意为正反情况或者是布尔类型则使用大小写混合变量名，一般以is开头，只取两个值，例如控制开关的变量isOpen

  - 命名不使用下划线
  - 命名不含有数字
    - 若变量意为平行实验测试则在变量名后加上数字，不超过9，例如test1,test2

  + 循环中的控制变量使用单个小写字母，例如i,j,k

- 每行最多字符数

  每行最多字符数为80个，若要分行书写则将操作符放在新行之首，划分的新行进行适当缩进

- 函数最大行数

  函数的规模限制在100 行以内，不包括注释和空格行

- 函数、类命名

  + 函数名
    + 使用英语单词，多为动词，小写
    + 若用两个单词才能表示的函数功能则使用大小写混合函数名，多用get开头，例如getHeight
    + 不含有下划线和数字

  + 类名
    + 使用英语单词，多为名词，小写
    + 不含有下划线和数字
    + 若用两个单词才能表示的函数功能则使用大小写混合函数名，例如treeNode

- 常量

  + 全英文大写
  + 每个单词间用下划线分隔

- 空行规则

  + 函数之间应该用空行分开
  + 类声明之后加入空行同其他代码分开
  + 常量说明一般在头，之后加空行

- 注释规则

  + 只有多行注释才使用/\*...\*/，其余较短的注释使用//

  + 函数头部进行注释，列出：函数的目的/功能、输入参数、输出参数、返回值等，使用/\*...\*/
  + 对于变量或类的解释在本行之后加注释，使用//
    + 若加注释后该行字符数过多，则在本行前一行增加注释，使用//，与所描述内容进行同样的缩排
    + 对结构中的每个域的注释放在此域的右方（本行），使用//

  + 源程序中有效注释量控制在 20％~30%之间

- 操作符前后空格

  - 操作符前后不加空格

- 其他规则

  + 不把多个短语句写在一行中，一行只写一条语句

  + if、for、do、while、case、switch、default等语句自占一行，且 if、for、 do、while 等语句的执行语句部分无论多少都要加括号{}

  + 使用{}时，左括号接在前部分的句末，例如：
  <pre>if(i<4) {
  ...
  }</pre>
  

#!/usr/bin/env bash

username=xiezongze

#拼接字符串
echo "hellow ${username} !"
#获取字符串长度
echo ${#username}
#字符串剪切;剪切第八个字符开始到第九个字符结束(神龙见首不见尾)
echo ${username:7:9}
#定义数组：数组由下标0开始；没有限定数组大小
name_array=(张三 李四 王五)
#读取数组元素 //李四
echo "${name_array[1]}"
#获取数组的长度;与获取字符串长度相同；其中中括号里面*代表
# 所有；也可以是具体的下标获取到单个元素  、//3
echo "${#name_array[*]}"
#获取数组元素的长度  //名字的长度：6
echo "名字的长度：${#name_array[1]}"
#参数传递测试  //执行脚本时加入参数如：sh build.sh xiezongze is vereryGood下标是传递参数的顺序
echo "$1 $2 $3"
echo "$*"  #可以代替第一句shell也是输出所有的参数
echo "$#"  #取得所有输入参数的个数
#使用$((a+b))这个可以支持加减乘除基本运算
age1=12;age2=18;num=2
val=$((${age1}+${age2}))
#两个年龄之和为:30
echo "两个年龄之和为:${val}"
val=$((${age1}-${age2}))
#减法  //两个年龄的差：6
echo "两个年龄的差：${val}"
#除法  //相除结果为：9
val=$((${age2}/${num}))
echo "相除结果为：${val}"
#乘法  //相乘结果为:24
val=$((${age1}*${num}))
echo "相乘结果为:${val}"

#判断两个年龄是否相等
if test ${age2} -gt ${age1}
    then
        echo "年龄2大于或者等于年龄1！"
    else
        echo "年龄2小于年龄1"
fi

#比较符左右两边需要加空格  //两个字符串相等！
string1=hellow;string2=hellow
if test ${string1} = ${string2}
    then
        echo "两个字符串相等！"
    else
        echo "两个字符串不相等！"
fi

#文件存在！
if test -e /f/test/test.bash
    then
        echo "文件存在！"
    else
        echo "文件不存在！"
fi

xiaoming=120;
xiaowan=121;
#文件存在！
if test ${xiaoming} -eq ${xiaowan};
    then echo "钱相等！"
    elif test ${xiaoming} -gt ${xiaowan}
     then echo "小明钱比较多！"
    else
        echo "小王钱比较多！"
fi

#没有索引类型的循环
names=(老王,老张,老李)
for var in ${names}
    do
    echo "今天来报道的人有：${var}"
done
#有索引类型的循环
for (( index = 0; index < ${#names[*]}; ++index ));
    do echo "今天来报道的人有:${names[index]}"
done
#while循环;let命令可以执行计算;计算时不需要加上$来获取变量
loopCount=0;
while((${loopCount}<=5))
    do
    echo "循环次数：${loopCount}"
    let loopCount++
    done

#可以使用read命令来接受输入的参数
#函数的返回值只能接受数值类型
#调用函数只需要函数名就可以,可以通过$?来获取返回值结果
#函数测试结果：9
#function echoName(){
#        echo "请输入两个数字："
#    read numberone;
#        echo "请输入两个数字："
#    read numbertow;
#    return $((${numberone}+${numbertow}))
#}
#echoName
#echo "函数测试结果：$?"



#command > file	将输出重定向到 file
#command < file	将输入重定向到 file
#command >> file	将输出以追加的方式重定向到 file
#n > file	将文件描述符为 n 的文件重定向到 file
#n >> file	将文件描述符为 n 的文件以追加的方式重定向到 file
#n >& m	将输出文件 m 和 n 合并
#n <& m	将输入文件 m 和 n 合并
#<< tag	将开始标记 tag 和结束标记 tag 之间的内容作为输入
#文件描述符 0 通常是标准输入（STDIN），1 是标准输出（STDOUT），2 是标准错误输出（STDERR）

#cat 常用的参数
#    -n 将文件第一列加入编号输出
#    -b 将文件第一列加入编号输出,空行跳过不加
#    1、加上编号输出文件
#    cat -n file1
#    2、合并两个文件到一个文件(也可以加上编号合并)
#    cat file1 file2 > file3

#cp和mv命令
#    cp fileSource fileTarget 拷贝文件到指定目录下,如果目录不存在会自动创建
#    cp -r fileDir fileTarget 拷贝文件夹到指定目录下,如果文件不存在会自动创建
#    mv file1 file2 将file1改名成file2
#    mv file1 /user/local/test 将file1文件移动到指定目录下面

#cat、less、more
#cat命令查看是将文件中的内容全部输出
#more命令部分输出,如果需要显示更多可以向后查看
#less命令也是部分输出,它相对不more来说比较灵活可以向前或者向后查看

#which 在PATH变量指定的路径中,搜索某个可执行的系统命令的位置
#tar语法:tar 必要参数 可选参数 文件名
#必要参数有如下：
#    -A 新增压缩文件到已存在的压缩
#    -B 设置区块大小
#    -c 建立新的压缩文件
#    -d 记录文件的差别
#    -r 添加文件到已经压缩的文件
#    -u 添加改变了和现有的文件到已经存在的压缩文件
#    -x 从压缩的文件中提取文件
#    -t 显示压缩文件的内容
#    -z 支持gzip解压文件
#    -j 支持bzip2解压文件
#    -Z 支持compress解压文件
#    -v 显示操作过程
#    -l 文件系统边界设置
#    -k 保留原有文件不覆盖
#    -m 保留文件不被覆盖
#    -W 确认压缩文件的正确性
#可选参数如下：
#    -b 设置区块数目
#    -C 切换到指定目录
#    -f 指定压缩文件
#    --help 显示帮助信息
#    --version 显示版本信息
##压缩
#tar -zcvf test.tar.gz tar_test
##解压
#tar -zxvf test.tar.gz

#更换文件所属的用户
#chown 用户 文件
#更换文件所属的用户和用户组
#chown 用户:用户组 文件
#更换文件所属的用户组
#chgrp 用户组 文件

#创建用户
#useradd xiezz
#创建用户并添加组
#useradd -g test(组名) xiezz
#删除用户
#userdel xiezz
#userdel -r(删除主目录) -f(强制删除在线用户) xiezz
#设置密码
#passwd xiezz
#创建工作组
#groupadd newtest
#删除工作组
#groupdel newtest

#ssh root@192.168.0.213 "cd /app/qr/qr-manager/;touch filetest;vim filetest"

#cut 命令从文件的每一行剪切字节、字符和字段并将这些字节、字符和字段写至标准输出
#    -b ：以字节为单位进行分割,这些字节位置将忽略多字节字符边界，除非也指定了 -n 标志
#    -c ：以字符为单位进行分割
#    -d ：自定义分隔符，默认为制表符
#    -f  ：与-d一起使用，指定显示哪个区域
#    -n ：取消分割多字节字符。仅和 -b 标志一起使用。如果字符的最后一个字节落在由 -b 标志的 List 参数指示的<br />范围之内，
#    该字符将被写出；否则，该字符将被排除
#ps -ef|grep java|grep hellow|cut -c 7-15|xargs kill -s 9
#xargs 是给命令传递参数的过滤器;它会将前面命令的输入转换成后面命令的参数输出
#    -a file 从文件中读入作为sdtin
#    -e flag ，注意有的时候可能会是-E，flag必须是一个以空格分隔的标志，当xargs分析到含有flag这个标志的时候就停止
#    -p 当每次执行一个argument的时候询问一次用户
#    -n num 后面加次数，表示命令在执行的时候一次用的argument的个数，默认是用所有的
#    -t 表示先打印命令，然后再执行
#    -i 或者是-I，这得看linux支持了，将xargs的每项名称，一般是一行一行赋值给 {}，可以用 {} 代替
#    -r no-run-if-empty 当xargs的输入为空的时候则停止xargs，不用再去执行了
#    -s num 命令行的最大字符数，指的是 xargs 后面那个命令的最大命令行字符数
#    -L num 从标准输入一次读取 num 行送给 command 命令
#    -l 同 -L
#    -d delim 分隔符，默认的xargs分隔符是回车，argument的分隔符是空格，这里修改的是xargs的分隔符
#    -x exit的意思，主要是配合-s使用
#    -P 修改最大的进程数，默认是1，为0时候为as many as it can ，这个例子我没有想到，应该平时都用不到的吧



































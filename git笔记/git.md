# git基本操作
## git的工作区和文件状态
git分为三个区域
工作区 暂存区 本地仓库
![Alt text](image.png)
修改完工作区的文件需要添加到暂存区，然后再从暂存区提交到本地仓库
<!-- 形象的比喻 -->
![Alt text](image-1.png)

git的文件状态分为四种
未跟踪 未修改 已修改 已提交
![Alt text](image-2.png) 
未跟踪（没有被git管理的文件）
未修改（被git管理但是没有修改的文件）
已修改（以修改但是没被提交到暂存区的文件）
已暂存（添加到暂存区的文件）
## git基本命令
![Alt text](image-3.png)
git添加和提交文件
git add
git commit -m(只会提交暂存区的文件不会提交工作区的文件)
![Alt text](image-4.png)
```
可以看到textcopy文件在工作区内没有被提交到暂存区所以不可以被提交到本地仓库
```
git commit 会进入一个页面使用vim来编辑提交信息
git log 查看历史提交记录
![Alt text](image-5.png)
git log --oneline（简洁的提交信息）
![Alt text](image-6.png)

## git reset回退版本
![Alt text](image-7.png)
git reset --soft 保存工作区和暂存区的所有内容
git reset --hard  丢弃工作区和暂存区的所有内容
git reset --hard  保留工作区，丢弃暂存区的内容

我这里创建一个git仓库然后分三次提交三种文件
![
](image-8.png)
接下来分别演示三种不同参数
**git reset --soft 版本号**
![Alt text](image-9.png)
从之前的三次提交变成两次,head也指向了commit2
验证是否工作区和暂存区里的file3.txtg还在
![Alt text](image-10.png)
git status查看仓库状态
![
](image-11.png)
file3.txt此时是new files这是因为我们回退到了上一个版本号，也就是commit2里面，这个版本里是没有file3.txt文件的，所以file3在这里是额新的文件
**git reset --hard**
![Alt text](image-12.png)
发现工作区和暂存区里的file3文件都没了
**git reset --mixed**
![
](image-13.png)
工作区的file3文件还在而暂存区里的没有了

如果真的不小心删完了可以使用git reflog
查看之前提交的版本号
再使用git reset 加版本号回退就可以
git hard 会将当前工作目录和暂存区的所有文件丢弃（谨慎使用）

## git diff
第一种是比较**工作区**和**暂存区**之间的差异
![Alt text](image-14.png)
如图我在工作求将file3.txt文件里的内容改成了其他的内容
第一行提示了变更文件
第二行index 那里是Git将文件内容用哈希算法生成了40位的哈希值
100644是文件的权限
往下是添加的内容和删除的内容

![Alt text](image-15.png)
这里将工作区的内容添加到暂存区git diff就不会提示了

第二种比较**工作区** 和**版本库**之间的差异
git diff HEAD
![Alt text](image-16.png)
将暂存区的文件提交后便不会显示

比较暂存区和版本库之间的差异
git diff --cached
![
](image-17.png)
将文件提交便不会有显示了
![Alt text](image-18.png)

还可以比较不同版本之间的差异
![                   ](image-19.png)
git diff id1 id2 
GIT HEAD~ HEAD
GIT HEAD~2 HEAD
HEAD表示当前版本，head~2就是当前版本回退俩版本也就死commit2那里

## git rm删除文件
git rm删除文件是删除工作区和暂存区里的文件
![Alt text](image-20.png)

## 本地仓库关联远程仓库
![Alt text](image-22.png)

 create a new repository on the command line
echo "first-repo">>README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin url
git push -u origin main

...or push an existing repository from the command line
git remote add origin url
git branch -M main
git push -u origin main

git remote shortname url
连接远程仓库
git branch -M main
指定分支名称位main
git push -u origin main 
将本地的main分支与远程分支的main关联起来
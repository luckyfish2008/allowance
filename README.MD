## IDEA提交项目到github

1. 依次选择 File ---> Settings ---> Version Control ---> Git 
在 Path to Git executable 中选择自己安装的git的路径，然后可以点击右边的Test测试，出现git的版本表示成功，如下图1所示。
2. 在Version Control中选择Github 点击右上方的加号，在弹出的界面中添加自己的github账号和密码，如下图2所示，显示出自己的github账号表示成功。
3. 在idea上方依次选择 VCS ---> Import into Version Control ---> Create Git Repository , 如图3所示，点击 Create Git Repository,出现如下图3所示
4. 在第3步后弹出的界面中选择本地要提交的项目的路径。如下图4所示。
5. 在弹出的 Git init 界面中点击 Yes 。
6. 右击项目选择 Git ---> Add
7. 右击项目选择 Git ---> Commit Directory ，将该项目提交到本地仓库，如下图5所示。
8. 在弹出的 Commit Changes 界面中输入自己对提交内容的描述，如下图6所示，点击commit,将代码提交到本地仓库完成。
9. 将该项目 push (上传)到新建的远程仓库中，(创建远程仓库步骤如上述所示)，如下图7所示。
10. 在弹出的界面中点击 Define remote，URL选择远程仓库的URL即可，选择OK，如果出现自己对提交内容的描述，点击对提交内容的描述，(如下图8所示)，选择push，稍等片刻，idea会提交项目 push successful ,项目上传成功,在github中可以看到提交内容，如下图9所示。
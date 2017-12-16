<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 17-12-16
  Time: 下午3:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <span>单个文件上传</span>
        <form action="${pageContext.request.contextPath}/file/fileUpload" method="post" enctype="multipart/form-data">
            <p>选择文件: <input type="file" name="file"/></p>
            <p><input type="submit" value="提交"/></p>
        </form>
    </div>
    <hr/>
    <div>
        <span>单个文件下载</span>
        <form action="${pageContext.request.contextPath}/file/fileDownload" method="post"  target="_top" class="form form-horizontal" >
            <input type="text" class="input-text"   id="filename" name="filename" style="width:250px"  required="required">
            <input class="btn btn-primary radius" type="submit" id="submit" value="  下载  ">
        </form>
    </div>
    <hr/>
    <div>
        <span>多文件上传</span>
        <form method="POST" enctype="multipart/form-data"
              action="${pageContext.request.contextPath}/file/fileBatchUpload">
            File to upload: <input type="file" name="file"><br />
            File to upload: <input type="file" name="file"><br />
            <input type="submit" value="Upload"> Press here to upload the file!
        </form>

    </div>


</body>
</html>

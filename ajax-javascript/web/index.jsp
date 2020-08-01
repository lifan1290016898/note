<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax-Script</title>
    <script type="text/javascript">
        var xmlHttp;

        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");// Microsoft.XMLHTTP
            } else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
        }

        function send() {
            createXMLHttpRequest();
            var url = "ajax";
            var method = "post";
            var param = "name=zhangsan&value=one";
            var isASync = true;
            /**
             * opend
             *      参数1: 请求方式
             *      参数2: 请求的url
             *      请求3: 是否为异步
             */
            xmlHttp.open(method, url, isASync);
            // 指定回调方法
            xmlHttp.onreadystatechange = callback;
            // post方式需要设置http的请求头, get方式此步可以省略
            xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            // 携带参数.如果是get请求这里可以写null,但是参数需要携带在url上
            xmlHttp.send(param);

        }

        // 如果请求已完成 则处理结果
        function callback() {
            /**
             * 状态(readyState):
             *      0:  请求未初始化(在调用open之前)
             *      1:  请求已提出(在调用send之前)
             *      2:  请求已发送(通常可以从响应得到内容头部)
             *      3:  请求处理中(响应中通常有部分数据可用, 但是服务器还没有完成响应)
             *      4:  请求已完成(可以访问服务器响应并使用它)
             */
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) { // 返回的状态码
                    var str = xmlHttp.responseText;
                    var strpin = "<p>" + str + "</p>";
                    var strall = document.getElementById("result");
                    strall.innerHTML = strpin;
                    // alert(xmlHttp.responseText);
                }
            }
        }
    </script>
</head>
<body>
<input type="button" onclick="send()" value="发送"/>
<div id="result"></div>
</body>
</html>

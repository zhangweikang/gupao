<!doctype html>
<html>
<input name="name"/>
<input name="password"/>
<button id="login" onclick="login();">登陆</button>
<button id="getMessage" onclick="getMessage();">获取</button>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="application/javascript">
    var accessToken;
    /*$(function () {
        $('login').click(login);
        $('getMessage').click(getMessage);
    });*/
    var login = function(){
        setupAjax();
        var name = $('input[name="name"]').val();
        var password = $('input[name="password"]').val();

        $.post('/login',{name:name,password:password},function(data,status,xhr){

            console.log("......xhr:" + xhr);
            console.log("......data:" + data);
            if (data){
                var code = data.code;
                if(code && code == 1){
                    setupAjax(data.accessToken);
                }
            }
        },function(error){
            console.log("......"+error);
        },"json");
    };

    var setupAjax = function(accessToken){
        $.ajaxSetup({
            headers:{
                "access_token" : accessToken
            },
            //请求成功后触发
            success: function (data) { console.log("------请求成功"); },
            //请求失败遇到异常触发
            error: function (XMLHttpRequest,status, e) {
                console.log("------请求失败,"+e);
                console.log("------XMLHttpRequest,"+JSON.stringify(XMLHttpRequest));
            },
            //完成请求后触发。即在success或error触发后触发
            complete: function (xhr, status) {
                console.log("------请求后");
                accessToken = xhr.getResponseHeader("access_token");
                console.log("....."+accessToken);
            },
            //发送请求前触发
            beforeSend: function (XMLHttpRequest) {
                //可以设置自定义标头
                //xhr.setRequestHeader('Content-Type', 'application/xml;charset=utf-8');
                console.log("------请求前");
            }
        })
    };

    var getMessage = function(){
        $.post('/check',{},function(data){
            console.log("......"+data);
        });
    }
</script>
</html>
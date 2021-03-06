//单独验证某一个input  class="checkpass"
var pageii = null;
var rootPath = getPath();
$(function() {
    $("#form").validate({
        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
            //if(checkSubmit()){return;}
            var options = {
                success: function (data) {
                    if (data == "success") {
                        layer.confirm('添加成功!是否关闭窗口?', function(index) {
                            parent.layer.closeAll();
                            window.location.href="/demo/login/login";
                            return false;
                        });
                    } else {
                        layer.alert('添加失败！', 3);
                    }
                }
            };
            $("#form").ajaxSubmit(options);
        },
        rules : {
            "passWord" : {
                required : true,
                maxlength : 50,
            },
            "userName" : {
                required : true,
                maxlength : 50,
                remote : { // 异步验证是否存在
                    type : "POST",
                    url : rootPath + '/login/isExist',
                    data : {
                        'userName' : function() {
                            return $("input[name='userName']").val();
                        },
                    }
                }
            },
        },
        messages : {
            "passWord" : {
                required : "请输入密码"
            },
            "userName" : {
                required : "请输入用户名",
                remote : "用户名已被使用"
            }
        },
    });
    // 绑定取消按钮事件
    $(".cancel").click(function() {
        parent.layer.close(parent.pageii);
    });
    //修改页面角色名输入框获取焦点
    $("#roleName").focus();
});
function checkSubmit(){
    if(checkRoleName()){
        return true;
    }
    if(checkRoleKey()){
        return true;
    }
    return false;
}

function checkRoleName(){
    var b = false;
    debugger;
    var roleName =  $("input[name='userName']").val();
    if(typeof(roleName)=='undefined'||roleName==null||roleName==''){
        layer.msg("请输入用户名");
        b = true;
        return b;
    }
    if(roleName.length>16){
        layer.msg("角色名超过50个字符");
        b = true;
        return b;
    }
   /* var url = rootPath + '/role/isExistRoleName';
    var da = CommnUtil.ajax(url,{roleName: roleName},"json");
    switch (da) {
        case "1":
            layer.msg("角色名称本系统已经存在");
            b = true;
            break;
        case "2":
            layer.msg("角色名称统一认证服务已经存在");
            b = true;
            break;
        default:
            break;
    }*/
    return b;
}

function checkRoleKey(){
    var b = false;
    var roleKey =  $("input[name='passWord']").val();
    if(typeof(roleKey)=='undefined'||roleKey==null||roleKey==''){
        layer.msg("请输入密码");
        b = true;
        return b;
    }
    if(roleKey.length>16){
        layer.msg("密码超过限定长度");
        b = true;
        return b;
    }
    /*var url = rootPath + '/role/isExist';
    switch (CommnUtil.ajax(url,{roleKey: roleKey},"json")) {
        case "1":
            layer.msg("角色key本系统已经存在");
            b = true;
            break;
        case "2":
            layer.msg("角色key统一认证服务已经存在");
            b = true;
            break;
        default:
            break;
    }*/
    return b;
}

function getPath(){
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
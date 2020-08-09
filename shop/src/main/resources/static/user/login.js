$(function () {
    $('#submit').click(function () {
        var name = $('[name=name]').val();
        if (null == name || '' == name) {
            alert('用户名不能为空');
            return;
        }
        var password = $('[name=password]').val();
        if(null == password  || '' == password){
            alert('密码不能为空');
            return;
        }
        var role = $('[name=role]').val();
        if(null == role || '' == role){
            alert('请选择登录角色');
            return;
        }
        $.ajax({
            url: '/user/login',
            data: $('#form').serialize(),
            dataType: 'json',
            type: 'post',
            success: function (result) {

            }
        });
    });

});
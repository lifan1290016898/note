$(function () {
    $('#isBuyers').click(function () {
        $('[name=role]').val('buyers');
        $('#isSellers').removeClass('loginRole');
        $(this).addClass('loginRole');
    });
    $('#isSellers').click(function () {
        $('[name=role]').val('sellers');
        $('#isBuyers').removeClass('loginRole');
        $(this).addClass('loginRole');
    });

    $('#submit').click(function () {
        var name = $('[name=name]').val();
        var password = $('[name=password]').val();
        var phone = $('[name=phone]').val();
        var phoneReg = (/^1[3456789]\d{9}$/.test(phone));
        if(name == null || name == ''){
            alert('用户名不能为空');
            return;
        }
        if(password == null || password == ''){
            alert('密码不能为空');
            return;
        }
        if(phone == null || phone == ''){
            alert('电话不能为空');
            return;
        }
        if(!phoneReg){
            alert('手机号码格式不正确');
            return;
        }
        $.ajax({
            url: '/user/register',
            data: $('#form').serialize(),
            type: 'post',
            dataType: "json",
            success: function (result) {
                alert(result.msg);
                if(result.code == 0){
                    $('#from')[0].reset();
                }
            }
        });
    });

});
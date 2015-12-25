function changemenu(id, link) {
    $.ajax({
        url: link,
        type: "GET",
        success: function(data) {
            $('#mainDiv').html(data);
        }
    });
    if (id != '') {
        html = '<li> <i class="icon-home home-icon"></i> <a href="#">首页</a> </li> <li> <a href="#">'+$('#' + id).parent().parent()[0].id+'</a> </li> <li>'+$('#' + id)[0].textContent+'</li>';
        $('#bc').html(html);
        $('#projectSideMenu').find('li').removeClass('active');
        $('#' + id).parent().parent().attr('class', 'active open');
        $('#' + id).attr('class', 'active');
    }
}
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
    return unescape(arr[2]);
    else
    return null;
}
function changetab(id, link) {
    $.ajax({
        url: link,
        type: "GET",
        success: function(data) {
            $('#subDiv').html(data);
        }
    });
    if (id != '') {
        $('#np').find('li').removeClass('active');
        $('#' + id).attr('class', 'active');
    }
}

function submit_project(name) {
    $.ajax({
        cache: true,
        type: "POST",
        url: name,
        data: $('#form_' + name).serialize(),
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            BootstrapDialog.alert(data);
            if (name == "newproject" || name == "serverapply" || name == "purviewapply" || name == "changeoutip") {
                if (data.indexOf('审核记录') > 0) {
                    if (name == "serverapply") {
                        changemenu('checkloglist', 'checkserverlist');
                    } else {
                        changemenu('checkloglist', 'checkloglist');
                    }
                }
            } else {
                changemenu('', name);
            }
        }
    });
}

function changepasswd(n) {
    buttons = [{
            label: '提交',
            cssClass: 'btn-success',
            action: function(dialogRef){
                if ($('#oldpasswd').val() == "" || $('#newpasswd').val() == "" || $('#repeatnewpasswd').val() == "") {
                    BootstrapDialog.alert("请输入当前密码以及新密码！");
                } else if ($('#newpasswd').val() != $('#repeatnewpasswd').val()) {
                    BootstrapDialog.alert("两次输入的新密码不一致，请重新输入！");
                } else {
                    if (!checkpasswd()) {
                        BootstrapDialog.alert("新密码必须由字母、数字、符号组成，且长度大于8位！");
                    } else {
                        $.ajax({
                            cache: true,
                            type: "POST",
                            url: 'changepasswd',
                            data: $('#form_changepasswd').serialize(),
                            async: false,
                            error: function(request) {
                                alert("Connection error");
                            },
                            success: function(data) {
                                BootstrapDialog.alert(data);
                                if (data == '密码修改成功') {
                                    dialogRef.close();
                                }
                            }
                        });
                    }
                }
            }
        },]
    if (n == 1) {
        title = '首次登录，请修改密码';
    } else {
        title = '修改密码';
        buttons.push({
            label: '放弃',
            action: function(dialogRef){
                dialogRef.close();
            }
        })
    }
    BootstrapDialog.show({
        title: title,
        message: '<form class="form-horizontal" role="form" id="form_changepasswd"> <div class="form-group"> <label for="oldpasswd" class="col-sm-3 control-label">当前密码</label> <div class="col-sm-8"> <input type="password" class="form-control" name="oldpasswd" id="oldpasswd" placeholder="当前密码"> </div> </div> <div class="form-group"> <label for="newpasswd" class="col-sm-3 control-label">新密码</label> <div class="col-sm-8"> <input type="password" class="form-control" name="newpasswd" id="newpasswd" placeholder="新密码"></div> </div> <div class="form-group"> <label for="repeatnewpasswd" class="col-sm-3 control-label">重复新密码</label> <div class="col-sm-8"> <input type="password" class="form-control" name="repeatnewpasswd" id="repeatnewpasswd" placeholder="重复新密码"></div> </div> <div class="form-group"> <label for="passwdtip" class="col-sm-3 control-label"></label> <div class="col-sm-8" id="passwdtip" style="color: red">新密码至少8位，由字母（区分大小写）、数字、符号组成</div> </div> </form>',
        closable: false,
        buttons: buttons
    });
}

function checkpasswd() {
    var str = $('#newpasswd').val();
    var m1 = /\d/;
    var m2 = /[A-za-z]/;
    var m3 = /[^A-Za-z0-9]/;
    if (!str.match(m1) || !str.match(m2) || !str.match(m3) || str.length < 8) {
        return false;
    } else {
        return true;
    }
}

function checkipaddr(ips) {
    var re =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    arr = ips.split('\n');
    for (i=0; i<arr.length; i++) {
        if (!re.test($.trim(arr[i]))) {
            return false;
        }
    }
    return true;
}

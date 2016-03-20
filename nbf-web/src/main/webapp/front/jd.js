var _need_ck = false;
var str_rsaString = 'DA6A7EAA790172FD01F25288C2488375DD0808A1100B1ED416ADC7A2A482BD8E1406C6D3C1709E06C90C76BBA85B85350E5F5F8C0784346CE557646D9E6348819A0FBC98A06C80E14E11B33F19FD0C9450D53F0EE08BD2CBF0694024986ADC5420544F3CB11144381527B7061BDD488A8604AF532F12D4D49ABEAC1BB25BF241';
var str_kenString = 'lpxthpna';;
(function() {
    var _login_free = true,
        _len_user = 0,
        _len_passwd = 0,
        _len_ckcode = 0;
    $(document).ready(function() {
        hasCookie();
        bindEvents()
    });

    function hasCookie() {
        if (!navigator.cookieEnabled) {
            $('.item-tips').show().children('.err-msg').html('您的手机浏览器不支持或已经禁止使用cookie，无法正常登录，请开启或更换其他浏览器')
        }
    }

    function bindEvents() {
        $('.new-a-back').on('click', function() {
            window.history.back()
        });
        $('.login-free').on('click', function() {
            $(this).toggleClass('login-free-selected');
            _login_free = !_login_free
        });
        $('.btn-off').on('click', function() {
            if ($(this).hasClass('btn-on')) {
                $(this).removeClass('btn-on');
                $(this).prev().attr('type', 'password')
            } else {
                $(this).addClass('btn-on');
                $(this).prev().attr('type', 'text')
            }
        });
        $('#captcha-img').on('click', function() {
            refreshAuth()
        });
        $('.btn-cancel').on('click', function() {
            $(this).parents('#pop-choose').css('display', 'none')
        });
        $('.btn-confirm').on('click', function() {
            $(this).parents('#pop-confirm').css('display', 'none')
        });
        _len_user = $('.txt-username').on('input', function() {
            $(this).removeClass('txt-err');
            _len_user = this.value.length;
            enableLogin()
        }).val().length;
        _len_passwd = $('.txt-password').on('input', function() {
            $(this).removeClass('txt-err');
            _len_passwd = this.value.length;
            enableLogin()
        }).val().length;
        _len_ckcode = $('.txt-captcha').on('input', function() {
            $(this).removeClass('txt-err');
            _len_ckcode = this.value.length;
            enableLogin()
        }).val().length;
        $('.btn-login').on('click', function() {
            if (!$(this).hasClass('btn-disabled')) {
                login()
            }
        })
    }

    function enableLogin() {
        if (_len_user && _len_passwd && (!_need_ck || _need_ck && _len_ckcode)) {
            $('.btn-login').removeClass('btn-disabled')
        } else {
            $('.btn-login').addClass('btn-disabled')
        }
    }

    function refreshAuth() {
        var url = '/cgi-bin/m/authcode?mod=login&v=' + Math.random();
        $('#captcha-img').children('img').attr('src', url)
    }

    function makeParams() {
        var params = {};
        params.username = $('.txt-username').val();
        params.pwd = $('.txt-password').val();
        var rsa_n = str_rsaString;
        setMaxDigits(131);
        var key = new RSAKeyPair("3", "10001", rsa_n, 1024);
        var c = window.btoa(encryptedString(key, params.pwd, RSAAPP.PKCS1Padding, RSAAPP.RawEncoding));
        params.pwd = c;
        params.remember = _login_free;
        params.s_token = str_kenString;
        if (_need_ck) {
            params.authcode = $('.txt-captcha').val()
        }
        return params
    }

    function pop(info) {
        var $total = $('#pop-choose');
        $('.pop-msg', $total).html(info.msg);
        $('.btn-continue', $total).html(info.btn).attr('href', info.url);
        $total.css('display', 'block')
    }

    function login() {
        $('.btn-login').addClass('btn-disabled').html('登录中');
        $('.item-tips').hide();
        var params = makeParams();
        var start = (new Date()).getTime();
        $.ajax({
            url: '/cgi-bin/m/domlogin',
            type: 'POST',
            data: params,
            dataType: 'json',
            success: function(rdata) {
                $('.btn-login').removeClass('btn-disabled').html('登录');
                var time = (new Date()).getTime() - start;
                window.pl_report({
                    interfaceID: 393217,
                    loginName: params.username,
                    callTime: time,
                    status: rdata.errcode
                });
                if (rdata.errcode == 0) {
                    location.href = rdata.succcb
                } else {
                    if (rdata.needauth) {
                        $('.input-info').show();
                        _need_ck = true;
                        refreshAuth();
                        enableLogin()
                    } else {
                        $('.input-info').hide();
                        _need_ck = false
                    }
                    switch (rdata.errcode) {
                        case 6:
                            $('.txt-password').focus().addClass('txt-err');
                            $('.item-tips').show().children('.err-msg').html(rdata.message);
                            break;
                        case 7:
                            $('.txt-username').focus().addClass('txt-err');
                            $('.item-tips').show().children('.err-msg').html(rdata.message);
                            break;
                        case 257:
                            $('.txt-captcha').focus().addClass('txt-err');
                            $('.item-tips').show().children('.err-msg').html(rdata.message);
                            break;
                        case 128:
                            pop({
                                msg: '您的账号存在安全风险，请进行短信登录验证',
                                btn: '确定',
                                url: rdata.succcb
                            });
                            break;
                        case 100:
                            $('#pop-confirm').css('display', 'block');
                            break;
                        case 103:
                            pop({
                                msg: rdata.message,
                                btn: '找回密码',
                                url: 'https://passport.m.jd.com/findloginpassword/fillAccountName.action'
                            });
                            break;
                        case 105:
                            location.href = rdata.succcb;
                            break;
                        default:
                            $('.item-tips').show().children('.err-msg').html(rdata.message);
                            break
                    }
                }
            },
            error: function() {
                $('.item-tips').show().children('.err-msg').html('服务器开小差，请稍后重试');
                $('.btn-login').removeClass('btn-disabled').html('登录');
                var time = (new Date()).getTime() - start;
                window.pl_report({
                    interfaceID: 393217,
                    loginName: params.username,
                    callTime: time,
                    status: '255'
                })
            }
        })
    }
}());
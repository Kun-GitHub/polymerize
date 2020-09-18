<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">展开菜单</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx!''}/index">
            <#--<img class="img-responsive" src="${ctx!''}/resources/images/logo1.png" width="140" height="34">-->
                <span style="font-size: 28px;color: #fff;line-height: 36px;">${appName!''}</span>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <#--<ul class="nav navbar-nav" style="margin-left:6%;">
                <li><a href="${ctx!''}/index">首页</a></li>
                <li><a href="${ctx!''}/monitor/realtime">实时监控</a></li>
                <li><a href="${ctx!''}/alarm/result-list">预警记录</a></li>
                <li><a href="${ctx!''}/alarm/data-report-list?dataType=1">预警报告</a></li>
                <li><a href="${ctx!''}/monitor/playback">视频回放</a></li>
            &lt;#&ndash;<li><a href="${ctx!''}/smsConf/smsConf-list">号码配置</a></li>&ndash;&gt;
                <li><a href="${ctx!''}/dev/terminal-list">终端管理</a></li>
                <li><a href="${ctx!''}/bas/bridge-list">桥梁管理</a></li>
                <li><a href="${ctx!''}/bas/user-list">用户管理</a></li>
            </ul>-->
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class="dropdown-toggle" style="padding: 0px 15px;line-height: 50px;"
                   data-toggle="dropdown" role="button" aria-expanded="false">
                        <span style="vertical-align: middle;">操作</span> <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <#--<li><a href="#" data-toggle="modal" data-target="#myInfoModal" onclick="loadMyInfo()">个人信息</a>
                        </li>-->
                        <li><a href="#" data-toggle="modal" data-target="#myPwdModal">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="${ctx!''}/logout">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
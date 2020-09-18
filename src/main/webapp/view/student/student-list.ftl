<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>课程登记系统 - 学员信息列表</title>
    <#include "/common/header.ftl">
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!''}/resources/js/my97/skin/WdatePicker.css"></script>
</head>
<body>
<#include "/common/topnav.ftl">

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar" role="tablist">
                <li role="presentation" class="active">
                    <a href="${ctx!''}/student/student-list">学员信息
                    </a>
                </li>
                <li role="presentation">
                    <a href="${ctx!''}/feedback/feedback-list">Feedback</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main tab-content">

            <div class="tab-pane active" role="tabpanel" id="roletab">
                <div class="row placeholders">
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-blue">
                            <a href="#" class="fa-links" data-toggle="modal" data-target="#addPriceConf">
                                <i class="icon-plus-sign-alt icon-2x"></i>
                                <h5>新增</h5>
                            </a>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-1 placeholder">
                        <div class="thumbnail tile tile-medium tile-danger">
                            <a href="#" class="fa-links" data-confirm="确定要删除选中项吗？" onclick="delRows()">
                                <i class="icon-trash icon-2x"></i>
                                <h5>删除</h5>
                            </a>
                        </div>
                    </div>

                </div>
                <form method="get" id="searchForm">
                    <h4 class="sub-header" style="clear:both;">
                        <input class="form-control" type="text" name="name" value="${vo.name!''}" placeholder="姓名"
                               style="display: inline-block; width: auto;"/>
                        <button type="submit" class="btn btn-primary">查询</button>
                        <button type="button" class="btn btn-default" onclick="resetForm('searchForm')"> 重置</button>
                    </h4>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th class="th-checkbox"><input type="checkbox" onclick="selectAll('itemCb')"></th>
                            <th>姓名</th>
                            <th>编号</th>
                            <th>联系方式</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>班级</th>
                            <th>入学时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list page.list as item>
                        <tr>
                            <td><input type="checkbox" name="itemCb" value=${item.id!''}></td>
                            <td>${item.name!''}</td>
                            <td>${item.num!''}</td>
                            <td>${item.phone!''}</td>
                            <td><#if item.sex??&&item.sex == 1>女<#else>男</#if></td>
                            <td>${item.age!''}岁</td>
                            <td>${item.classes!''}</td>
                            <td>${(item.createTime?datetime)!''}</td>
                            <td>
                                <a href="#" data-toggle="modal" data-target="#editPriceConf"
                                   onclick="initEdit(${item.id!''})">编辑</a>&nbsp;&nbsp;
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            <#include "/common/pager.ftl">
            <@pager pager=page baseUrl=uri parameter=parameter />
            </div>

            <div class="modal fade" id="addPriceConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增学员信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="addForm">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 姓名：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="name"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 编号：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="num"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 联系方式：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="phone"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 性别：</label>
                                    <div class="col-sm-7">
                                        <select name="sex" class="form-control">
                                            <option value="0">男孩</option>
                                            <option value="1">女孩</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 年龄：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="age"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 班级：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="classes"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 入园时间：</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="createTime" class="form-control time_input" style="width: auto;"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 修改电表Modal -->
            <div class="modal fade" id="editPriceConf" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">修改学员信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" id="editForm">
                                <input type="hidden" name="id"/>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 姓名：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="name"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 联系方式：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="phone"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 年龄：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="age"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 班级：</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" placeholder="" name="classes"
                                               data-rule-required="true">
                                    </div>
                                </div>
                                <#--<div class="form-group">
                                    <label class="col-sm-3 control-label"><span class="red">*</span> 入园时间：</label>
                                    <div class="col-sm-7">
                                        <input type="text" name="createTime" class="form-control time_input" style="width: auto;"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                                    </div>
                                </div>-->
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="button" class="btn btn-primary" onclick="update()">保存</button>
                        </div>
                    </div>
                </div>
            </div>

        <#include "/common/footer.ftl">

            <script type="text/javascript">
                function initEdit(dataId) {
                    $.get(ctx + "/student/student-load",
                            {id: dataId},
                            function (data) {
                                if (data.message) {
                                    notice(data.message, "red");
                                } else {
                                    $("#editForm").setForm(data);
                                }
                            }, "json");
                }

                function save() {
                    if (!$("#addForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/student/student-save",
                            $("#addForm").serialize(),
                            function (data) {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function update() {
                    if (!$("#editForm").valid()) {
                        return;
                    }
                    $.post(ctx + "/student/student-update",
                            $("#editForm").serialize(),
                            function (data) {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }

                function delRows() {
                    var ids = getIds("itemCb");
                    if (!ids) {
                        notice("请至少选择一条记录删除", "red");
                        return;
                    }
                    $.post(ctx + "/student/student-delete",
                            {ids: ids},
                            function (data) {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    notice(data.message, "red");
                                }
                            }, "json");
                }
            </script>
</body>
</html>
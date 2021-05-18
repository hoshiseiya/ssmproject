<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +
            "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript"
            src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

    <script type="text/javascript">

        $(function () {

            $("#addBtn").click(function () {
                //时间控件
                $(".time").datetimepicker({
                    minView: "month",
                    language: 'zh-CN',
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    todayBtn: true,
                    pickerPosition: "bottom-left"
                });

                $.ajax({
                    url: "activity/findAll.do",
                    dataType: "json",
                    type: "get",
                    success: function (data) {
                        var html = "";
                        $.each(data, function (i, n) {
                            html += "<option value='" + n.id + "'>" + n.name + "</option>"
                        })

                        $("#create-owner").html(html);
                        //将当前登录的用户设置为默认选项
                        var id = "${user.id}";
                        $("#create-owner").val(id);
                        $("#createActivityModal").modal("show");
                    }
                })
            })
            //    为保存按钮绑定事件，执行添加操作
            $("#saveBtn").click(function () {
                $.ajax({
                    url: "activity/save.do",
                    data: {
                        "owner": $.trim($("#create-owner").val()),
                        "name": $.trim($("#create-name").val()),
                        "startDate": $.trim($("#create-startDate").val()),
                        "endDate": $.trim($("#create-endDate").val()),
                        "cost": $.trim($("#create-cost").val()),
                        "description": $.trim($("#create-description").val())
                    },
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        //    返回成功或者失败就可以
                        if (data) {
                            //    添加成功后
                            //    刷新市场信息活动列表
                            // 	pageList(1,2);
                            //	($("#activityPage").bs_pagination('getOption', 'currentPage')
                            //	表示操作后停留在当前页
                            //	$("#activityPage").bs_pagination('getOption', 'rowsPerPage')
                            //	操作后维持已经设置好的每页展现的记录数
                            //	做完添加操作后，应该回到第一页，维持每页展现的记录数
                            pageList(1);

                            //	清空模态窗口中的数据
                            //	reset()方法不能重置表单 原生js提供了reset方法
                            //	我们将jquery对象转换为原生的dom对象
                            //	jquery转化为dom jquery对象[下标]
                            <%--	dom对象转化为jquery对象 ${dom}--%>
                            $("#activityAddForm")[0].reset();
                            //    关闭模态窗口
                            $("#createActivityModal").modal("hide");
                        } else {
                            alert("添加市场活动失败")
                        }
                    }
                })
            })
            //	页面加载完毕后 触发一个方法
            //	默认展开列表的第一页
            pageList(1);
            //	为查询按钮绑定事件触发查新方法
            $("#searchBtn").click(function () {
                /*点击查询按钮的时候，应该把搜索框中的信息保存起来
                * 保存到隐藏域之中*/
                $("#hidden-name").val($.trim($("#search-name").val()))
                $("#hidden-owner").val($.trim($("#search-owner").val()))
                $("#hidden-startDate").val($.trim($("#search-startDate").val()))
                $("#hidden-endDate").val($.trim($("#search-endDate").val()))
                pageList(1);
            })

            //	为全选的复选框绑定事件，触发全选操作
            $("#qx").click(function () {
                $("input[name=xz]").prop("checked", this.checked);
            })
            //以下这种做法是不行的  因为动态生成的元素是不能以普通绑定事件的元素进行操作的
            /*$("input[name=xz]").click(function (){
                alert("123");
            })
            动态生成的元素 我们要以on的形式来触发事件 $(需要绑定的元素有效的外层元素).on(绑定事件的方式，需要绑定的元素的jQuery对象，回调函数)*/
            $("#emps_table tbody").on("click", $("input[name=xz]"), function () {
                // alert("123");
                $("#qx").prop("checked", $("input[name=xz]").length == $("input[name=xz]:checked").length);
            })

            //	为删除事件绑定事件 执行市场活动删除操作
            $("#deleteBtn").click(function () {
                //	找到复选框中所有打勾的复选框的jquery 对象
                var $xz = $("input[name=xz]:checked");
                if ($xz.length == 0) {
                    alert("请选择需要删除的记录");
                } else {
                    if (confirm("您确定要删除所选择的数据嘛")) {
                        //肯定是选了 而且有可能是1条，有可能是多条
                        // 	alert("123");
                        //	拼接参数
                        var param = "";
                        //将$xz中的每一个dom对象遍历出来，取其value值，就相当于取得了需要删除的记录的id
                        for (var i = 0; i < $xz.length; i++) {
                            param += "id=" + $($xz[i]).val();
                            //	如果不是最后一个元素，需要在后面追加一个&符
                            if (i < $xz.length - 1) {
                                param += "&";
                            }
                        }
                    }
                    alert(param);
                    $.ajax({
                        url: "activity/delete.do",
                        data: param,
                        dataType: "json",
                        type: "post",
                        success: function (data) {
                            //	返回成功或者失败都可以
                            if (data) {
                                //回到第一页，维持每页展现的记录数
                                pageList(1);

                            } else {
                                alert("删除市场活动失败")
                            }
                        }
                    })
                }
            })
            //	为修改按钮绑定事件，打开修改操作的模态窗口
            $("#editBtn").click(function () {

                var $xz = $("input[name=xz]:checked");
                if ($xz.length == 0) {
                    alert("请选择需要修改的记录")
                } else if ($xz.length > 1) {
                    alert("您只能选择一条记录修改");
                } else {
                    var id = $xz.val();
                    $.ajax({
                        url: "activity/getUserListAndActivity.do",
                        data: {
                            "id": id
                        },
                        dataType: "json",
                        type: "get",
                        success: function (data) {
                            //	要市场活动对象和用户列表
                            //	处理下拉框中的用户
                            var html = "";
                            $.each(data.userList, function (i, n) {
                                html += "<option value='" + n.id + "'>" + n.name + "</option>"
                            })
                            $("#edit-owner").html(html);

                            //	处理单条activity
                            $("#edit-id").val(data.a.id);
                            $("#edit-name").val(data.a.name);
                            $("#edit-owner").val(data.a.owner);
                            $("#edit-startDate").val(data.a.startDate);
                            $("#edit-endDate").val(data.a.endDate);
                            $("#edit-cost").val(data.a.cost);
                            $("#edit-description").val(data.a.description);

                            //	所有的值都填写好之后，打开修改操作的模态窗口
                            $("#editActivityModal").modal("show");

                        }
                    })
                }
            })
            //	为更新事件绑定事件，执行市场活动的更新操作
            // 	一般更新操作都copy添加操作
            $("#updateBtn").click(function () {
                $.ajax({
                    url: "activity/update.do",
                    data: {
                        "id": $.trim($("#edit-id").val()),
                        "owner": $.trim($("#edit-owner").val()),
                        "name": $.trim($("#edit-name").val()),
                        "startDate": $.trim($("#edit-startDate").val()),
                        "endDate": $.trim($("#edit-endDate").val()),
                        "cost": $.trim($("#edit-cost").val()),
                        "description": $.trim($("#edit-description").val())
                    },
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        //    返回成功或者失败就可以
                        if (data) {
                            //    修改成功后
                            //    刷新市场信息活动列表
                            // pageList(1);
                            //修改操作后，应该回到当前页，维持每页展现的记录数
                            pageList(currentPage);

                            //    关闭模态窗口
                            $("#editActivityModal").modal("hide");
                        } else {
                            alert("修改市场活动失败")
                        }
                    }
                })
            })
        });

        function pageList(pageNum) {
            //查询前，将隐藏域中的信息保存的信息取出来，重新赋予到搜索框之中
            $("#search-name").val($.trim($("#hidden-name").val()))
            $("#search-owner").val($.trim($("#hidden-owner").val()))
            $("#search-startDate").val($.trim($("#hidden-startDate").val()))
            $("#search-endDate").val($.trim($("#hidden-endDate").val()))
            // alert("展现市场活动列表");
            $.ajax({
                url: "activity/pageList.do",
                data: {
                    "pageNum": pageNum,
                    "name": $.trim($("#search-name").val()),
                    "owner": $.trim($("#search-owner").val()),
                    "startDate": $.trim($("#search-startDate").val()),
                    "endDate": $.trim($("#search-endDate").val())
                },
                contentType: "application/json",
                dataType: "json",
                type: "get",
                success: function (data) {
                    var html = "";
                    $.each(data.extend.pageInfo.list, function (i, n) {
                        html += '<tr class="active">';
                        html += '<td><input name="xz" type="checkbox" value="' + n.id + '"/></td>';
                        html += '<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'activity/detail.do?id=' + n.id + '\';">' + n.name + '</a></td>';
                        html += '<td>' + n.owner + '</td>';
                        html += '<td>' + n.startDate + '</td>';
                        html += '<td>' + n.endDate + '</td>';
                        html += '</tr>';
                    })
                    //1、解析并显示数据
                    $("#emps_table tbody").html(html);
                    //2、解析并显示分页信息
                    build_page_info(data);
                    //3、解析显示分页条数据
                    build_page_nav(data);
                }
            })
            $("#qx").prop("checked", false);
        }

        //解析显示分页信息
        function build_page_info(data) {
            $("#page_info_area").empty();
            $("#page_info_area").append("当前" + data.extend.pageInfo.pageNum + "页,总" +
                data.extend.pageInfo.pages + "页,总" +
                data.extend.pageInfo.total + "条记录");
            totalRecord = data.extend.pageInfo.total;
            currentPage = data.extend.pageInfo.pageNum;
        }

        //解析显示分页条，点击分页要能去下一页....
        function build_page_nav(data) {
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");

            //构建元素
            var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "javascript:void(0)"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
            if (data.extend.pageInfo.hasPreviousPage == false) {
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            } else {
                //为元素添加点击翻页的事件
                firstPageLi.click(function () {
                    pageList(1);
                });
                prePageLi.click(function () {
                    pageList(data.extend.pageInfo.pageNum - 1);
                });
            }

            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
            var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "javascript:void(0)"));
            if (data.extend.pageInfo.hasNextPage == false) {
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            } else {
                nextPageLi.click(function () {
                    pageList(data.extend.pageInfo.pageNum + 1);
                });
                lastPageLi.click(function () {
                    pageList(data.extend.pageInfo.pages);
                });
            }

            //添加首页和前一页 的提示
            ul.append(firstPageLi).append(prePageLi);
            //1,2，3遍历给ul中添加页码提示
            $.each(data.extend.pageInfo.navigatepageNums, function (index, item) {

                var numLi = $("<li></li>").append($("<a></a>").append(item));
                if (data.extend.pageInfo.pageNum == item) {
                    numLi.addClass("active");
                }
                numLi.click(function () {
                    pageList(item);
                });
                ul.append(numLi);
            });
            //添加下一页和末页 的提示
            ul.append(nextPageLi).append(lastPageLi);

            //把ul加入到nav
            var navEle = $("<nav></nav>").append(ul);
            navEle.appendTo("#page_nav_area");
        }
    </script>
</head>
<body>
<%--隐藏域--%>
<input type="hidden" id="hidden-name">
<input type="hidden" id="hidden-owner">
<input type="hidden" id="hidden-startDate">
<input type="hidden" id="hidden-endDate">
<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
            </div>
            <div class="modal-body">

                <form class="form-horizontal" role="form">

                    <input type="hidden" id="edit-id">

                    <div class="form-group">
                        <label for="edit-owner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="edit-owner">

                            </select>
                        </div>
                        <label for="edit-name" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-startDate">
                        </div>
                        <label for="edit-endDate" class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="edit-endDate">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <%--文本域textarea
                            1.一定要以标签对形式来呈现，正常状态下要紧紧挨着
                            2.textarea虽然是以标签对的形式来呈现的 但是它也是属于表单形式的范畴
                              使用val()--%>
                            <textarea class="form-control" rows="3" id="edit-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="updateBtn" type="button" class="btn btn-primary">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 85%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
            </div>
            <div class="modal-body">

                <form id="activityAddForm" class="form-horizontal" role="form">

                    <div class="form-group">
                        <label for="create-owner" class="col-sm-2 control-label">所有者<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control" id="create-owner">
                            </select>
                        </div>
                        <label for="create-name" class="col-sm-2 control-label">名称<span
                                style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-startDate" readonly>
                        </div>
                        <label for="create-endDate" class="col-sm-2 control-label ">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control time" id="create-endDate" readonly>
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="create-cost" class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="create-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="create-description" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="create-description"></textarea>
                        </div>
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>


<div>
    <div style="position: relative; left: 10px; top: -10px;">
        <div class="page-header">
            <h3>市场活动列表</h3>
        </div>
    </div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
    <div style="width: 100%; position: absolute;top: 5px; left: 10px;">

        <div class="btn-toolbar" role="toolbar" style="height: 80px;">
            <form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">名称</div>
                        <input id="search-name" class="form-control" type="text">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">所有者</div>
                        <input id="search-owner" class="form-control" type="text">
                    </div>
                </div>


                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">开始日期</div>
                        <input class="form-control time" type="text" id="search-startDate"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">结束日期</div>
                        <input class="form-control time" type="text" id="search-endDate">
                    </div>
                </div>

                <button id="searchBtn" type="button" class="btn btn-default">查询</button>

            </form>
        </div>
        <div class="btn-toolbar" role="toolbar"
             style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
            <div class="btn-group" style="position: relative; top: 18%;">
                <!--
                           点击创建按钮，观察两个属性和属性值
                           data-toggle="modal"：
                               表示触发该按钮，将要打开一个模态窗口
                           data-target="#createActivityModal"：
                               表示要打开哪个模态窗口，通过#id的形式找到该窗口
                           现在我们是以属性和属性值的方式写在了button元素中，用来打开模态窗口
                           但是这样做是有问题的：
                               问题在于没有办法对按钮的功能进行扩充

                           所以未来的实际项目开发，对于触发模态窗口的操作，一定不要写死在元素当中，
                           应该由我们自己写js代码来操作
                       -->
                <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span>
                    创建
                </button>
                <button type="button" class="btn btn-default" id="editBtn"><span
                        class="glyphicon glyphicon-pencil"></span> 修改
                </button>
                <button type="button" class="btn btn-danger" id="deleteBtn"><span
                        class="glyphicon glyphicon-minus"></span> 删除
                </button>
            </div>

        </div>

        <!-- 搭建显示页面 -->
        <div class="container">
        </div>
        <!-- 显示表格数据 -->
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover" id="emps_table">
                    <thead>
                    <tr style="color: #B3B3B3;">
                        <td><input type="checkbox" id="qx"/></td>
                        <td>名称</td>
                        <td>所有者</td>
                        <td>开始日期</td>
                        <td>结束日期</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 显示分页信息 -->
        <div class="row">
            <!--分页文字信息  -->
            <div class="col-md-6" id="page_info_area"></div>
            <!-- 分页条信息 -->
            <div class="col-md-6" id="page_nav_area">

            </div>
        </div>
    </div>

</div>
</body>
</html>
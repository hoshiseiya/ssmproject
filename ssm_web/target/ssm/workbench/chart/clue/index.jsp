<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() +
"://"
+ request.getServerName() + ":"
+ 	request.getServerPort()
+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <script src="ECharts/echarts.min.js"></script>
    <script src="jquery/jquery-1.11.1-min.js"></script>
    <script>
        $(function () {
            getCharts();
        })
        function getCharts() {
            $.ajax({
                url : "activity/getCharts.do",
                type:"get",
                dataType:"json",
                success:function (data) {
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '市场活动统计'
                        },
                        tooltip: {},
                        legend: {
                            data:['开销']
                        },
                        xAxis: {
                            data: data.nameList
                        },
                        yAxis: {},
                        series: [{
                            name: '开销',
                            type: 'bar',
                            data: data.costList
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })

        }
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>
--%>

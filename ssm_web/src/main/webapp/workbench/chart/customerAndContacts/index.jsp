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
    <script src="ECharts/echarts.min.js"></script>
    <script src="jquery/jquery-1.11.1-min.js"></script>
    <script>
        $(function () {
            getCharts();
        })

        function getCharts() {
            $.ajax({
                url: "customer/getCharts.do",
                type: "get",
                dataType: "json",
                success: function (data) {
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));

                    var option = {
                        title: {
                            text: '客户与联系人统计图',
                            // subtext: '纯属虚构',
                            left: 'center',
                            top: 120,
                            textStyle: {
                                fontSize: 40
                            }
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            top: 40,
                            itemWidth: 70,
                            itemHeight: 30
                        },
                        textStyle: {
                            fontSize: 15
                        },
                        series: [
                            {
                                name: '客户联系人关系',
                                type: 'pie',
                                radius: '50%',
                                label: {            //饼图图形上的文本标签
                                    normal: {
                                        position:'inner',
                                        margin:10,
                                        show: true,
                                        formatter: '{b} : {c}个 \n ({d}%)'
                                    }
                                },

                                data:
                                /* [
                                 {value: 1048, name: '搜索引擎'},
                                 {value: 735, name: '直接访问'},
                                 {value: 580, name: '邮件营销'},
                                 {value: 484, name: '联盟广告'},
                                 {value: 300, name: '视频广告'}
                             ],*/
                                data.dataList,

                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
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
<div id="main" style="width: 1200px;height:800px;margin: auto"></div>

</body>
</html>

//后台datetime格式转化
/* 获取日期格式 */
function getDate(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1 < 10)? '0' + (date.getMonth() + 1): date.getMonth() + 1;
    var day = (date.getDate() < 10)? '0' + date.getDate(): date.getDate();
    return year + "-" + month + "-" + day ;
}

/* 获取日期时间格式*/
function getDateTime(date) {
    var year = date.getFullYear();
    var month = (date.getMonth() + 1 < 10)? ('0' + (date.getMonth() + 1)) : (date.getMonth() + 1);
    var day = (date.getDate() < 10)? '0' + date.getDate() : date.getDate();
    var hh = (date.getHours() < 10)? '0' + date.getHours() : date.getHours();
    var mm = (date.getMinutes() < 10)? '0' + date.getMinutes(): date.getMinutes();
    var ss = (date.getSeconds() < 10)? '0' + date.getSeconds(): date.getSeconds();
    return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss; 
}

// datetimepicker中文化说明
$.fn.datetimepicker.dates['zh-CN'] = {
    days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],
    daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],
    daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],
    months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],
    monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
    meridiem:    ["上午", "下午"],
    today:       "今天"
};

$.fn.dataTable.defaults.oLanguage = {
    "sProcessing": "处理中...",
    "sLengthMenu": "显示 _MENU_ 项结果",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
    "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索：",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
};
function init_usageReportDougnut() {

    if (typeof (Chart) === 'undefined') { return; }

    if ($('.canvasDoughnut_usage_userGroup').length) {

        var dateRange = {
            "from_date": "2019-01-01", "to_date": "2019-12-31"
        }

        $.ajax({
            type: "POST",
            url: "../reporting/usage/byagegroup",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(dateRange),
            success: function (data) {
                getDataFromUsageByAgeGroup(data);
            },
            error: function (err) {
                console.log(err.responseText);
            }
        });

        function getDataFromUsageByAgeGroup(data) {

            var userGroupNames = []
            var userGroupCounts = [];
            var colors = ["#3498DB", "#26B99A", "#9B59B6", "#E74C3C", "#BDC3C7", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
            var colorsHover = ["#49A9EA", "#36CAAB", "#B370CF", "#E95E4F", "#CFD4D8", "#34495E", "#B8660B", "#FF80FF", "#FF8000", "#008000"];
            var colorNames = ["blue", "green", "purple", "red", "aero", "dark", "darkgoldenrod", "pink", "orange", "darkgreen"];
            var userGroupColors = [];
            var userGroupColorsHover = [];

            for (var i = 0; i < data.length; i++) {
                if (data[i].counts != "0") {
                    userGroupNames.push(data[i].label);
                    userGroupCounts.push(data[i].counts);
                }
            }

            for (var i = 0; i < userGroupNames.length; i++) {
                userGroupColors.push(colors[i]);
                userGroupColorsHover.push(colorsHover[i]);
            }

            //for(var i=0; i<userGroupNames.length; i++){
            //    $('.usage_userGroup_label').append(
            //        ' <div class="labelDiv"><div class="labelDiv_i"><i class="fa fa-square '+colorNames[i]+'"></i></div><div class="labelDiv_div">'+userGroupNames[i]+'</div></div> '
            //    );
            //}

            var userGroupNamesData = JSON.stringify(userGroupNames);
            //bpoExpNamesData = bpoExpNamesData.replace(/\"/g,'');
            //bpoExpNamesData = bpoExpNamesData.replace(/\'/g,'"'); 

            var userGroupCountsData = JSON.stringify(userGroupCounts);
            //bpoExpCountsData = bpoExpCountsData.replace(/\"/g,'');
            //bpoExpCountsData = bpoExpCountsData.replace(/\'/g,'"'); 

            var countList = [];
            for (var i = 0; i < userGroupCounts.length; i++) {
                countList.push(parseInt(userGroupCounts[i]));
            }

            var total = 0;
            for (var i = 0; i < countList.length; i++) {
                total = total + countList[i];
            }

            var percentageList = [];
            for (var i = 0; i < userGroupCounts.length; i++) {
                percentageList.push(((countList[i] / total) * 100).toFixed(2));
            }

            var percentageData = JSON.stringify(percentageList);
            var userGroupColorsData = JSON.stringify(userGroupColors);
            var userGroupColorsHoverData = JSON.stringify(userGroupColorsHover);

            var chart_doughnut_settings = {
                type: 'doughnut',
                tooltipFillColor: "rgba(51, 51, 51, 0.55)",
                data: {
                    labels: jQuery.parseJSON(userGroupNamesData),
                    datasets: [{
                        data: jQuery.parseJSON(userGroupCountsData),
                        backgroundColor: jQuery.parseJSON(userGroupColorsData),
                        hoverBackgroundColor: jQuery.parseJSON(userGroupColorsHoverData),
                        percentage: jQuery.parseJSON(percentageData)
                    }]
                },
                options: {
                    legend: false,
                    responsive: false,
                    tooltips: {
                        titleFontSize: 11,
                        bodyFontSize: 11,
                        callbacks: {
                            label: function (tooltipItem, data) {
                                return [
                                    data['datasets'][0]['data'][tooltipItem['index']] + ' (' + data['datasets'][0]['percentage'][tooltipItem['index']] + '%)'
                                ];
                            }
                        }
                    }
                }
            }

            $('.canvasDoughnut_usage_userGroup').each(function () {
                var chart_element = $(this);
                var chart_doughnut = new Chart(chart_element, chart_doughnut_settings);
            });

        } //getDataFromUsageByAgeGroup

    } //canvasDoughnut_usage_userGroup.length

} //init_usageReportDougnut

$(document).ready(function () {
    init_usageReportDougnut();
});
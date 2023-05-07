$(document).ready(function () {
    // Initialize admin account table for DataTable
    $('#admin_account_table').DataTable();
    // Initialize DataTable for preferred type of work
    //$('.pref_type_of_work_table').DataTable( {
    //    "emptyTable": "No data available in table",
    //    "paging": true,
    //    "searching": false,
    //    "bLengthChange": false,
    //    "pageLength": 6,
    //    language: {
    //        paginate: {
    //            next: '&#62;', // or '>'
    //            previous: '&#60;' // or '<' 
    //        }
    //    },
    //    "infoCallback": function( settings, start, end, max, total, pre ) {    
    //        var api = this.api();
    //        var pageInfo = api.page.info();
    //        return 'Page '+ (pageInfo.page+1) +' of '+ pageInfo.pages;
    //    }
    //});

    $(".dropdown").click(function () {
        $(this).find(".dropdown-menu").slide();
    });

    var currentPath = $(location).attr('pathname');
    if (currentPath == '/users/superuser-role') {
        $('.superUserRoleLink').css({
            'color': 'black',
            'background-color': 'darkgray'
        });
    }
    if (currentPath == '/users/user-role') {
        $('.userRoleLink').css({
            'color': 'black',
            'background-color': 'darkgray'
        });
    }

});
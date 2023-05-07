// For RESTful
/*
$(document).ready(function () {

    var table = $('#example').DataTable({
        "sAjaxSource": "/accounts",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            { "mData": "account_ID" },
            { "mData": "account_NAME" },
            { "mData": "location" },
            { "mData": "lob" },
            { "mData": "business_VERTICAL" },
            { "mData": "work_TYPE" }
            { "mData": null }
        ],
        columnDefs: [{
            targets: [-1], render: function (a, b, data, d) {
                return "<a style='color: #248700; text-decoration: underline; cursor:pointer;'>Edit</a>";
            }
        }],
    });

    table.on("click", "a", function () {
        var id = table.rows($(this).closest("tr")).data([0].account_ID;
        window.location.href = "/edit" + id;
    });

});
*/

function readUrl(input){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
            $('.image-hero').attr('src',e.target.result).width(auto).height(100);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

// Datatable JS
$(document).ready(function() {
    $('#accountTable').DataTable();
});

$(document).ready(function() {
    $('#userspage').DataTable();
});

// TABLE EDITOR
// account edit
$(document).ready(function(){
    $('#fileFetcher_accounts').change(function(){
        $('#image_path_accountpage').val($(this).val().replace(/C:\\fakepath\\/,"https://35.185.222.6/ImageRepo/"));
    });
});

$(document).ready(function() {

    $('#accountTable').on('click', '#editdetails1', function() {
        var currentRow = $(this).closest('tr');

        var col1 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value
        var col2 = currentRow.find('td:eq(1)').text();
        var col3 = currentRow.find('td:eq(2)').text();
        var col4 = currentRow.find('td:eq(3)').text();
        var col5 = currentRow.find('td:eq(4)').text();
        var col6 = currentRow.find('td:eq(5)').text();
        var col7 = currentRow.find('td:eq(6)').text();
        // hidden columns
        var col8 = currentRow.find('td:eq(7)').text();
        var col9 = currentRow.find('td:eq(8)').text();
        var col10 = currentRow.find('td:eq(9)').text();
        var col11 = currentRow.find('td:eq(10)').text();
        var col12 = currentRow.find('td:eq(11)').text();
        var col13 = currentRow.find('td:eq(12)').text();
        var col14 = currentRow.find('td:eq(13)').text();
        var col15 = currentRow.find('td:eq(14)').text();
        var col16 = currentRow.find('td:eq(15)').text();
        var col17 = currentRow.find('td:eq(16)').text();

        //tester
        //var data=col1+'\n'+col2+'\n'+col3+'\n'+col4+'\n'+col5;
        //alert(data);

        $('#account_id').val(col1);
        $('#account_name').val(col2);
        $('#location').val(col3);
        $('#lob').val(col4);
        $('#business_vertical').val(col5);
        $('#work_type').val(col6);
        $('#job_req').val(col7);
        $('#image_path').val(col8);
        $('#accountImage').attr('src',col8);
        // hidden columns
        $('#description').val(col9);
        $('#tm_task').val(col10);
        $('#education').val(col11);
        $('#bpo_exp').val(col12);
        $('#skills').val(col13);
        $('#operating_hours').val(col14);
        $('#skills_premium').val(col15);
        $('#category').val(col16);
        $('#trainings').val(col17);
        
        $('#fileFetcher').change(function(){
            $('#image_path').val($(this).val().replace(/C:\\fakepath\\/,"https://35.185.222.6/ImageRepo/"));
        });
        
    });

});

// Account mapping
$(document).ready(function() {

    $('#tableaccountmaps').on('click', '#editmapping1', function() {

        var currentRow = $(this).closest('tr');

        var col1 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value
        var col2 = currentRow.find('td:eq(1)').text();
        var col3 = currentRow.find('td:eq(2)').text();
        var col4 = currentRow.find('td:eq(3)').text();
        //tester
        //var data=col1+'\n'+col2+'\n'+col3+'\n'+col4+'\n'+col5;
        //alert(data);
        //alert(col1);
        $('#accountmapquestionid').val(col1);
        $('#acountmapquestion').val(col2);
        $('#accountmapanswerid').val(col3);
        $('#accountmapanswer').val(col4);

    });

});

$(document).ready(function() {

    $('#tableaccountmaps').on('click', '#addmapbtn', function() {

        var currentRow = $(this).closest('tr');

        var col1 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value

        $('#accountmappingid').val(col1);

    });

});

// Position mapping
$(document).ready(function() {

    $('#tablepositionmaps').on('click', '#editmapping1', function() {

        var currentRow = $(this).closest('tr');

        var col1 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value
        var col2 = currentRow.find('td:eq(1)').text();
        var col3 = currentRow.find('td:eq(2)').text();
        var col4 = currentRow.find('td:eq(3)').text();
        //tester
        //var data=col1+'\n'+col2+'\n'+col3+'\n'+col4+'\n'+col5;
        //alert(data);
        //alert(col1);
        $('#positionmapquestionid').val(col1);
        $('#acountmapquestion').val(col2);
        $('#accountmapanswerid').val(col3);
        $('#accountmapanswer').val(col4);
        
    });

});

$(document).ready(

    function() {

        $("select[name='questions'").change(

            function() {

                var selectedValue = $(this).children("option:selected").val();

                var DSData = {
                    10 : "Application Support",
                    11 : "Change Management",
                    12 : "Database Management",
                    13 : "Development / Application Support",
                    14 : "Business Continuity Planning",
                    15 : "IT Audit",
                    16 : "JAVA Development",
                    17 : "Production Server Support",
                    18 : "Project Management"
                };

                var STData = {
                    19 : "Facilities",
                    20 : "Customer Experience",
                    21 : "Human Resources",
                    22 : "Learning Services/Learning & Dev't",
                    23 : "Workforce Management"
                };

                if (selectedValue == 3) {
                    $("select[name='answer'").empty();
                    $.each(DSData, function(key, str) {
                        $("select[name='answer'").append(
                                $("<option></option>").attr("value",key).text(str));
                    });
                }
                else {
                    $("select[name='answer'").empty();
                    $.each(STData, function(key, str) {
                        $("select[name='answer'").append(
                            $("<option></option>").attr("value",key).text(str));
                    });
                }

            }

        );
    }
);

/*
function viewmapping(accountID) {
    $.ajax({
        url: "/accountmap/" + accountID,
        data: { ACCOUNT_ID: accountID },
        type: "GET",
        beforeSend: function () {
            $("#tableaccountmaps tbody").empty();
        }
    }).done(function (data) {

        console.log(data);

        $.each(data.accountAnswers, function (i, accountAnswer) {

            $("#tableaccountmaps tbody").append("<tr>" +
                "<td>" + data.account_ID + "</td>" +
                "<td>" + accountAnswer.question.question_ID + "</td>" +
                "<td>" + accountAnswer.question.question + "</td>" +
                "<td>" + accountAnswer.answer.answer_ID + "</td>" +
                "<td>" + accountAnswer.answer.answer + "</td>" +
                "<td>" + "<button class='btn btn-danger'>Delete</button>" + "<td>" +
                "</tr>");

        })
    });


};
*/

// TABLE EDITOR END

// POSITION NON AGENT SECTION
$(document).ready(function() {
    $('#positiontable').DataTable();
});

$(document).ready(function(){
    $('#fileFetcher_positions').change(function(){
       $('#image_path_positionpage').val($(this).val().replace(/C:\\fakepath\\/,"https://35.185.222.6/ImageRepo/"));
    });
});

$(document).ready(function() {

    $('#positiontable').on('click', '#editpositiondetails', function() {
        var currentRow = $(this).closest('tr');

        var col0 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value
        var col1 = currentRow.find('td:eq(1)').text();
        var col2 = currentRow.find('td:eq(2)').text();
        var col3 = currentRow.find('td:eq(3)').text();
        var col4 = currentRow.find('td:eq(4)').text();
        var col5 = currentRow.find('td:eq(5)').text();
        // hidden columns
        var col6 = currentRow.find('td:eq(6)').text();
        var col7 = currentRow.find('td:eq(7)').text();
        var col8 = currentRow.find('td:eq(8)').text();
        var col9 = currentRow.find('td:eq(9)').text();
        var col10 = currentRow.find('td:eq(10)').text();
        var col11 = currentRow.find('td:eq(11)').text();
        var col12 = currentRow.find('td:eq(12)').text();
        var col13 = currentRow.find('td:eq(13)').text();
        var col14 = currentRow.find('td:eq(14)').text();

        $('#positionid').val(col0);        
        $('#jobprofile').val(col1);
        $('#department').val(col2);
        $('#imagepath').val(col3);
        $('#positionImagePath').attr('src',col3);
        $('#supporttype').val(col4);    
        $('#jobreq').val(col5);
        $('#description').val(col6);
        $('#certs').val(col7);
        $('#educationalback').val(col8);
        $('#expreq').val(col9);
        $('#skillsreq').val(col10);    
        $('#otherskills').val(col11);
        $('#category').val(col12);
        $('#trainings').val(col13);        
        $('#mod').val(col14);
    
        $('#uploadImageFile').change(function(){
            $('#imagepath').val($(this).val().replace(/C:\\fakepath\\/,"https://35.185.222.6/ImageRepo/"));
        });

    });

});

// Image View
/*
$(document).ready(function () {
    $(window).on('load', function () {
        var imagelink = $('.imageText').text();
        console.log(imagelink);
        $('.imagePic').attr('src', imagelink);
    })
});
*/

// Users edit table
$(document).ready(function(){

    $('#userspage').on('click', '#editUsers', function(){

        var currentRow = $(this).closest('tr');
        var col0 = currentRow.find('td:eq(0)').text(); // get current row 1st TD value
        var col1 = currentRow.find('td:eq(1)').text();
        var col2 = currentRow.find('td:eq(2)').text();
        var col3 = currentRow.find('td:eq(3)').text();
        var col4 = currentRow.find('td:eq(4)').text();
        var col5 = currentRow.find('td:eq(5)').text();

        // Model data catch
        $('#user_id').val(col0);
        $('#firstname').val(col1);
        $('#lastname').val(col2);
        $('#username1').val(col3);
        $('#USER_GROUP').val(col4);
        $('#USER_STATUS').val(col5);

    });

});

// Sort positions table (view all and view perId) in descending order
$(document).ready(function() {

    $('#logs_datatables_positions_all').DataTable({
        "order" : [2, 'desc']
    });

    $('#logs_datatables_positions_perId').DataTable({
        "order" : [2, 'desc']
    });
 
});

// Image upload modal
/*
$(document).ready(function () {

    $('#accountTable').on('click', '#uploadImageBtn', function () {

        var currentRow = $(this).closest('tr');
        var col0 = currentRow.find('td:eq(0)').text();
        var col1 = currentRow.find('td:eq(1)').text();
        var col7 = currentRow.find('td:eq(7)').text();

        $('#imageaccount_id').val(col0);
        $('#imageaccount_name').val(col1);
        $('#imagepath_container').val(col7);

    });

});

$(document).ready(function () {

    $('#fileFetcher').change(function () {
        $('#imagepath_container').val($(this).val().replace(/C:\\fakepath\\/, "https://35.185.222.6/ImageRepo/"));
    });

});
*/
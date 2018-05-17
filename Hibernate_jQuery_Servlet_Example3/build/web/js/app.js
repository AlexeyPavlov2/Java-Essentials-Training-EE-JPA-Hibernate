$(document).ready(function (e) {
    //Stops the submit request
    $("#myAjaxRequestForm").submit(function (e) {
        e.preventDefault();
    });

    //checks for the button click event
    $("#myButton").click(function (e) {
        //get the form data and then serialize that
        dataString = $("#myAjaxRequestForm").serialize();
        //get the form data using another method 
        var countryCode = $("input#countryCode").val();
        dataString = "countryCode=" + countryCode;

        $.ajax({
            type: "POST",
            url: "GetInfo",
            data: dataString,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.error != true) {
                    $("#errorResponse").hide();
                    $("#normalResponse").show();
                    
                    $("#capital").text(data.capital);
                    $("#code").text(data.code);
                    $("#name").text(data.name);
                    $("#area").text(data.area);
                    $("#population").text(data.population);
                    $("#lexpect").text(data.lexpect);
                    $("#gdp").text(data.gdp);
                }
                //display error message
                else {
                    $("#normalResponse").hide();
                    $("#errorResponse").show();
                    $("#errorMsg").text("Country code is invalid!");
                    
                }
            }, //success     

            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                $("#normalResponse").hide();
                $("#errorResponse").show();
                alert(jqXHR.responseText);
                $("#errorMsg").text(jqXHR.responseText);
                
            }, //error
            beforeSend: function (jqXHR, settings) {
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            }, //beforesend
            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function (jqXHR, textStatus) {
                //enable the button 
                $('#myButton').attr("disabled", false);
            }

        }); //ajax        


    }); //click myButton

});


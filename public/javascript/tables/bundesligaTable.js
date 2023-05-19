function fetchdata(){  


    $.ajax({
        url: 'http://localhost:8080/blt',
        dataType: 'json',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr><td>' + data[i].position + '</td><td>' + data[i].name + '</td><td>' + data[i].gamesPlayed + '</td><td>' + data[i].wins + '</td><td>' + data[i].draws + '</td><td>' + data[i].losses + '</td><td>' + data[i].gd + '</td><td>' + data[i].points + '</td></tr>');
                $('#myTable').append(row);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Error: ' + textStatus + ' - ' + errorThrown);
        }
    });



}

$(document).ready(function(){ 
    fetchdata();
    setInterval(fetchdata,1800000); // half an hour in milliseconds
});
function fetchdata(){

    //getting the DOM elements
    var matchTable = document.querySelector("#matchTable");


    //the functions to create an element
    function addMatchTile(data){
        //createing the tile div
        var matchtile = document.createElement('div');
        matchtile.classList.add("match-tile");

        //creating the home match box
        var homeTeam = document.createElement('div');
        homeTeam.classList.add("team");
        //creating the image and the text
        var homeTileTeamName = document.createElement('p');
        homeTileTeamName.innerHTML = data['teams']['home']['name'];
        var homeTileTeamLogo = document.createElement('img');
        homeTileTeamLogo.src=data['teams']['home']['logo'];
        homeTeam.appendChild(homeTileTeamLogo);
        homeTeam.appendChild(homeTileTeamName);

        var awayTeam = document.createElement('div');
        awayTeam.classList.add("team");
        //creating the image and the text
        var awayTileTeamName = document.createElement('p');
        awayTileTeamName.innerHTML = data['teams']['away']['name'];
        var awayTileTeamLogo = document.createElement('img');
        awayTileTeamLogo.src=data['teams']['away']['logo'];
        awayTeam.appendChild(awayTileTeamLogo);
        awayTeam.appendChild(awayTileTeamName);


        var date = document.createElement('p');
        date.innerHTML = data['fixture']['date'];

        //append all the element to the parent
        matchtile.appendChild(homeTeam);
        matchtile.appendChild(date);
        matchtile.appendChild(awayTeam);

        matchTable.appendChild(matchtile);
    }
    //fetching the data
    const options = {
        method: 'GET',
        headers: {
            'X-RapidAPI-Key': 'fe5fb0c93fmsha9f50466d188c00p1497c3jsn318270340c25',
            'X-RapidAPI-Host': 'api-football-v1.p.rapidapi.com'
        }
    };

    fetch('https://api-football-v1.p.rapidapi.com/v3/fixtures?league=78&season=2022&next=10', options)
        .then(response => response.json().then(data => {
            console.log(data);
            var matchesList = data['response'];
            var fixture = matchesList[0]['fixture'];
            var teams = matchesList[0]['teams'];


            for(var i = 0; i<matchesList.length;i++){
                addMatchTile(matchesList[i]);
            }

        }))
        .catch(err => {
            console.log(err);
        });

}


    $(document).ready(function(){ 
        fetchdata();
        // 1 hour in milliseconds
        // The reason why its only once an 
        // hour is because i have a free supcription
        // to the soccer api which means i can only call it
        // once an hour
        setInterval(fetchdata,3600000); 
    });
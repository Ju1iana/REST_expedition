/*
const Buttonget1 = document.getElementById('fetchButton');

Buttonget1.addEventListener('click', () => {


    async function getResponse() {
        var response = await fetch('http://localhost:8080/getAllPeople', {
            mode: 'no-cors'
        })

        var data = await response.json()
        console.log(data)
        console.log(response.status)


        var head = $("<tr />")
        $("#DataTable").append(head);
        for (var j = 0; j < Object.keys(data[0]).length; j++) {
            head.append($("<th>" + Object.keys(data[0])[j] + "</th>"));
        }


        for (var i = 0; i < data.length; i++) {
            drawRow(data[i]);
        }

    }
    setTimeout(getResponse,1000);


    function drawRow(rowData) {
        var row = $("<tr />")
        $("#DataTable").append(row);
        row.append($("<td>" + rowData["fio"] + "</td>"));
        row.append($("<td>" + rowData["personGender"] + "</td>"));
        row.append($("<td>" + rowData["age"] + "</td>"));
        row.append($("<td>" + rowData["h"] + "</td>"));
        row.append($("<td>" + rowData["weight"] + "</td>"));
    }





})

*/

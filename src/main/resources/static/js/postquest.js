const ButtonPoste2 = document.getElementById('fetchButtonPost');

ButtonPoste2.addEventListener('click', () => {


    async function postResponse() {
        var inputElement1 = document.getElementById("fios");
        var inputElement2 = document.getElementById("genders");
        var inputElement3 = document.getElementById("ages");
        var inputElement4 = document.getElementById("heightes");
        var inputElement5 = document.getElementById("weightes");
        var value1 = inputElement1.value;
        var value2 = inputElement2.value;
        var value3 = inputElement3.value;
        var value4 = inputElement4.value;
        var value5 = inputElement5.value;

        console.log(value1, value2, value3, value4, value5);

        var user = {
            personGender: value2, age: value3, h: value4, weight: value5 , fio: value1
        };

        let response = await fetch('http://localhost:8080/addPerson', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user)
        });

        let result = await response.json();
        console.log(result)

    }
    postResponse()



    async function getResponse() {
        let response = await fetch('http://localhost:8080/getAllPeople', {
            mode: 'no-cors'
        })

        var data = await response.json()
        console.log(content)
        console.log(response.status)
        document.getElementById('getContent').innerText = JSON.stringify(data, null, 3);

    }
    setTimeout(getResponse,1000);



    /*var data = [{
        "Total": 34,
        "Version": "1.0.4",
        "Office": "New York"
    }, {
        "Total": 67,
        "Version": "1.1.0",
        "Office": "Paris"
    }];

    drawTable(data);

    function drawTable(data) {

        // Get Table headers and print
        var head = $("<tr />")
        $("#DataTable").append(head);
        for (var j = 0; j < Object.keys(data[0]).length; j++) {
            head.append($("<th>" + Object.keys(data[0])[j] + "</th>"));
        }

        // Print the content of rows in DataTable
        for (var i = 0; i < data.length; i++) {
            drawRow(data[i]);
        }

    }

    function drawRow(rowData) {
        var row = $("<tr />")
        $("#DataTable").append(row);
        row.append($("<td>" + rowData["Total"] + "</td>"));
        row.append($("<td>" + rowData["Version"] + "</td>"));
        row.append($("<td>" + rowData["Office"] + "</td>"));
    }*/


})



const ButtonPoste3 = document.getElementById('fetchButtonPost');

ButtonPoste3.addEventListener('click', () => {


    async function postResponse() {
        var inputElement1 = document.getElementById("fios");
        var inputElement2 = document.getElementById("genders");
        var inputElement3 = document.getElementById("ages");
        var inputElement4 = document.getElementById("heightes");
        var inputElement5 = document.getElementById("weightes");
        let value1 = inputElement1.value;
        let value2 = inputElement2.value;
        let value3 = inputElement3.value;
        let value4 = inputElement4.value;
        let value5 = inputElement5.value;
        const one = 1


        console.log(value1, value2, value3, value4, value5);

        var user = {
            personGender: value2, age: value3, h: value4, weight: value5 , fio: value1
        };

        if (value3 < one) {
            console.log('Ошибка');
            alert('Неверный возраст')
        } else {
            if (value4 < one) {
                console.log('Ошибка');
                alert('Неверный рост')
            } else {
                if (value5 < one) {
                    console.log('Ошибка');
                    alert('Неверный вес')
                } else {

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
            }
        }

    }
    postResponse()

    function clearPlaceholders() {
        document.getElementById('putin').reset();
    }
    clearPlaceholders()

    async function getResponse() {
        var response = await fetch('http://localhost:8080/getAllPeople', {
            mode: 'no-cors'
        })

        var data = await response.json()
        console.log(data)
        console.log(response.status)
/*        document.getElementById('getContent').innerText = JSON.stringify(content, null, 3);*/

        var tableContainer = document.getElementById("DataTable");
        tableContainer.innerHTML = "";

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
    row.append($("<td>" + rowData["id"] + "</td>"));
    row.append($("<td>" + rowData["fio"] + "</td>"));
    row.append($("<td>" + rowData["personGender"] + "</td>"));
    row.append($("<td>" + rowData["age"] + "</td>"));
    row.append($("<td>" + rowData["h"] + "</td>"));
    row.append($("<td>" + rowData["weight"] + "</td>"));
    row.append($("<td>" + rowData["calories"] + "</td>"));
  }

})

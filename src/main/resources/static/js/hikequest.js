const ButtonPostehike3 = document.getElementById('ButtonPostHike');

ButtonPostehike3.addEventListener('click', () => {

    async function postHikeResponse() {
        var inputElement1 = document.getElementById("hikeduration");
        var inputElement2 = document.getElementById("typeofhike");
        var inputElement3 = document.getElementById("categoryofhike");
        let value1 = inputElement1.value;
        let value2 = inputElement2.value;
        let value3 = inputElement3.value;
        const one = 1

        console.log(value1, value2, value3);


        let formData = new FormData();        // Формирование body для POST запроса
        formData.append('duration', value1);
        formData.append('type', value2);
        formData.append('difficulty', value3)

        if (value1 < one) {
            console.log('Ошибка');
            alert('Неверная длительность похода')
        } else {

            let response = await fetch('http://localhost:8080/addParameters', {
                method: 'POST',
                body: formData
            });

            let result = await response.json();
            console.log(result)

            async function getRation() {

                let DataRation = await fetch('http://localhost:8080/getRation',{
                    method:'GET'
                });

                let data = await DataRation.json();
                console.log(data)


                var head = $("<tr />")
                $("#DataTable").append(head);
                for (var j = 0; j < Object.keys(data[0]).length; j++) {
                    head.append($("<th>" + Object.keys(data[0])[j] + "</th>"));
                }
                for (var i = 0; i < data.length; i++) {
                    drawRow(data[i]);
                }


                function drawRow(data) {
                    var row = $("<tr />")
                    $("#DataTable").append(row);
                    row.append($("<td>" + data["day"] + "</td>"));
                    row.append($("<td>" + data["nameOfMeal"] + "</td>"));
                    row.append($("<td>" + data["products"].map(it => it.name + " " + it.daily_norm_per_person + "гр " + it.calories_per_100g + "ккал").join(' &#9668; ') + "</td>"));
                }
            }

            setTimeout(getRation, 300);

        }
    }
    postHikeResponse()



    function clearduration() {       // Очистка полей ввода после отправки данных о походе
        document.getElementById('putin2').reset();
    }
    setTimeout(clearduration, 500);



    // var tableContainer = document.getElementById("DataTable");
    // tableContainer.innerHTML = "";




})

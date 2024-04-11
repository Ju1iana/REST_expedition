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

        var tableContainer = document.getElementById("DataTable");
        tableContainer.innerHTML = "";


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

                let DataRation = await fetch('http://localhost:8080/getRation', {
                    method: 'GET'
                });
                let data = await DataRation.json();
                console.log(data);


                function drawDay() {
                    var t = data.length;
                    var k = t/4;
                    console.log(t);
                    console.log(k);

                    var row = $("<tr class='headers-left' id='headers-left'/>")
                    $("#DataTable").append(row);
                    row.append($("<th>" + "Тип/День" + "</th>"));
                    row.append($("<th>" + "Завтрак" + "</th>"));
                    row.append($("<th>" + "Перекус" + "</th>"));
                    row.append($("<th>" + "Обед" + "</th>"));
                    row.append($("<th>" + "Ужин" + "</th>"));



                    var headays = $("<tr class='staks' id='staks'/>")
                    $("#DataTable").append(headays);
                    for (var j = 1; j <= k; j++) {
                        headays.append($("<th>" + "День " + j + "</th>"));
                    }

                }
                drawDay()

                var t = data.length;
                for (var i = 0; i < t; i++) {
                    drawRow(data[i]);
                }
                function drawRow(data) {
                    $("#staks").append("<td>" + data["products"].map(it => it.name).join(', ') + "</td>");
                }

                // + " " + it.daily_norm_per_person + "гр " + it.calories_per_100g + "ккал"


                // for(var x = 0; x < data.length; x++) {
                //     for(var y = 0; y < data[x].products.length; y++) {
                //         $('#test').append('<p>' + data[x].day + ', ' + data[x].nameOfMeal + ': ' + data[x].products[y].name + '</span></p>');
                //     }
                // }

                }
                setTimeout(getRation, 300);
            }
        }
        postHikeResponse()


    // Очистка полей ввода после отправки данных о походе
    function clearduration() {
        document.getElementById('putin2').reset();
    }
    setTimeout(clearduration, 500);








})

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

        let formData = new FormData();
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

        }
    }

    postHikeResponse()

    function clearduration() {
        document.getElementById('putin2').reset();
    }

    setTimeout(clearduration, 1000);


})
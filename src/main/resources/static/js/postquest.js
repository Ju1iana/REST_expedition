
const ButtonPoste = document.getElementById('fetchButtonPost');

ButtonPoste.addEventListener('click', () => {

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
})
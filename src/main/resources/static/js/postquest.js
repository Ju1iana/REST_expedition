const ButtonPost = document.getElementById('fetchButtonPost');

var gender = document.getAttribute('genders')
var ages = document.getAttribute('ages')
var heightes = document.getAttribute('heightes')
var weightes = document.getAttribute('weightes')
var fios = document.getAttribute('fios')



ButtonPost.addEventListener('click', () => {

    async function postResponse() {
        var user = {
            personGender: gender,
            age: ages,
            h: heightes,
            weight: weightes,
            fio: fios
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
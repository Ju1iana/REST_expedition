const ButtonPost = document.getElementById('fetchButtonPost');
let gender = document.getElementById('genders')
let ages = document.getElementById('ages')
let heightes = document.getElementById('heightes')
let weightes = document.getElementById('weightes')
let fios = document.getElementById('fios')


ButtonPost.addEventListener('click', () => {

    async function postResponse() {
        let user = {
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
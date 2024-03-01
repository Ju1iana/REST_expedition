const Buttonget = document.getElementById('fetchButton');

Buttonget.addEventListener('click', () => {

    async function getResponse() {
        let response = await fetch('http://localhost:8080/getAllPeople',{
            mode: 'no-cors',
        })

        let content = await response.json()
        console.log(content)
        console.log(response.status)
        document.getElementById('getContent').innerText = JSON.stringify(content, null, 3);

    }
    getResponse()

})


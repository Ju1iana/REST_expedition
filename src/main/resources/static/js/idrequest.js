const Buttongetid = document.getElementById('fetchButtonID');

Buttongetid.addEventListener('click', () => {

    async function getResponse() {
        const userID = 1;
        let response = await fetch('http://localhost:8080/getPersonById?id='+userID,{
            mode: 'no-cors',
        })

        let content = await response.json()
        console.log(content)
        console.log(response.status)
        document.getElementById('getContent').innerText = JSON.stringify(content, null, 2);


    }
    getResponse()
})

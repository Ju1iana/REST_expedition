const Buttonget = document.getElementById('fetchButton');

Buttonget.addEventListener('click', () => {

    async function getResponse() {
        let response = await fetch('http://localhost:8080/getAllPeople',{
            mode: 'no-cors',
        })

        let content = await response.json()
        console.log(content)
        console.log(response.status)
        document.getElementById('getContent').innerText = JSON.stringify(content, null, 2);



    }
    getResponse()
})





//     let response = await fetch('http://localhost:8080/getAllPeople', {
//          method: 'GET',
//         withCredentials: true,
//          crossorigin: true,
//          mode: 'no-cors',             // ПРОБЛЕМА В NO-CORS - НАДО МЕНЯТЬ ЗАГОЛОВОК НА СЕРВЕРЕ ЛИБО ЧЁТ ДУМАТЬ!
//      })
//     let content = await response.json()
//     console.log(content)


//     let content = await response.text()
//     console.log(content)


//async function getResponse() {
//      let response = await fetch('http://localhost:8080/getAllPeople', {
//          method: 'GET',
//         withCredentials: true,
//          crossorigin: true,
//          mode: 'no-cors',
//      })
//     console.log(JSON.parse(response))
//
//  }
//  getResponse()
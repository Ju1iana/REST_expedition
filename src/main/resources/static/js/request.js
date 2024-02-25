const Buttonget = document.getElementById('fetchButton');

Buttonget.addEventListener('click', () => {           // Тут мейби вместо ивент ()

async function getResponse() {
     let response = await fetch('http://localhost:8080/getAllPeople')
    console.log(response)
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
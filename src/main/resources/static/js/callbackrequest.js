const Buttonpostcall = document.getElementById('buttoncallback');

Buttonpostcall.addEventListener('click', () => {


    async function postback() {
        var inputElem1 = document.getElementById("user");
        var inputElem2 = document.getElementById("mail");
        var inputElem3 = document.getElementById("report");
        let val1 = inputElem1.value;
        let val2 = inputElem2.value;
        let val3 = inputElem3.value;


        console.log(val1, val2, val3);

        var call = {
            user: val1, mail: val2, report: val3
        };


        let response = await fetch('http://77.222.53.207:8080/sendMessage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(call)
        });

        let result = await response.json();
        console.log(result)

    }
    postback()
    })


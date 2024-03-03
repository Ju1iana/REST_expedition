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


        let response = await fetch('http://localhost:8080/XXXXX', {
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


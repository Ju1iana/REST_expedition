
const ButtonPost = document.getElementById('fetchButtonPost');

ButtonPost.addEventListener('click', () => {


    async function postResponse() {
        var inputElement1 = document.getElementById("fios");
        var inputElement2 = document.getElementById("genders");
        var inputElement3 = document.getElementById("ages");
        var inputElement4 = document.getElementById("heightes");
        var inputElement5 = document.getElementById("weightes");
        let value1 = inputElement1.value;
        let value2 = inputElement2.value;
        let value3 = inputElement3.value;
        let value4 = inputElement4.value;
        let value5 = inputElement5.value;
        const one = 1


        console.log(value1, value2, value3, value4, value5);

        // Создание тела body, которое будет отправлено на сервер
        var user = {
            personGender: value2, age: value3, h: value4, weight: value5, fio: value1
        };

        //Проверка введённых данных
        if (value3 < one) {
            console.log('Ошибка');
            alert('Неверный возраст')
        } else {
            if (value4 < one) {
                console.log('Ошибка');
                alert('Неверный рост')
            } else {
                if (value5 < one) {
                    console.log('Ошибка');
                    alert('Неверный вес')
                } else {
                    // POST запрос в формате JSON
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
            }
        }

    }
    postResponse()



    function clearPlaceholders() {          // Очистка полей ввода после отправки пользователя
        document.getElementById('putin').reset();
    }
    clearPlaceholders()

    // Очистка аккордеонов перед получением новых
    var accordiondelete = document.getElementById("accordion");
    accordiondelete.innerHTML = "";

    // GET запрос на сервер на получение пользователей
    async function getResponse() {
        var response = await fetch('http://localhost:8080/getAllPeople', {
            mode: 'no-cors'
        })

        var data = await response.json()        // Цикл создания аккордеона для каждого users
        data.forEach(

            function addAccordion(data) {

                    var accordion = $('#accordion');
                    var space = "   "
                    var outname0 = "Имя: "
                    var outname1 = "Пол: "
                    var outname2 = "Возраст: "
                    var outname3 = "Рост: "
                    var outname4 = "Вес: "
                    var outname5 = "Калории: "

                    var accordionItem = $('<div class="accordion-item"></div>');
                    var accordionHeader = $('<div class="accordion-header"></div>').text(outname0 + data.fio + space + space + outname1 + data.personGender + space + space + outname2 + data.age + space + space + outname3 + data.h + space + space + outname4 + data.weight + space + space + outname5 + data.calories);
                    var accordionContent = $('<div class="accordion-content"></div>').text("ПАНЕЛЬ ИЗМЕНЕНИЯ ПОЛЬЗОВАТЕЛЯ");
                    let PLSaccordion = $(' <div class="inputchangeblock"> <input type="text" placeholder="Name:" id="pricol" > <input type="text" placeholder="Sex:" id="pricol1" > <input type="number" placeholder="Age:" id="pricol2" > <input type="number" placeholder="Height:" id="pricol3" > <input type="number" placeholder="Weight:" id="pricol4" > </div>');


                // Генерация уникальных id для <Input> на основе data.id
                function IdDataGen(){
                    var tyt = document.getElementById('pricol')
                    tyt.id = "AccInputName" + data.id
                }
                setTimeout(IdDataGen,100);

                function IdDataGen1(){
                    var tyt1 = document.getElementById('pricol1')
                    tyt1.id = "AccInputSex" + data.id
                }
                setTimeout(IdDataGen1,100);

                function IdDataGen2(){
                    var tyt2 = document.getElementById('pricol2')
                    tyt2.id = "AccInputAge" + data.id
                }
                setTimeout(IdDataGen2,100);

                function IdDataGen3(){
                    var tyt3 = document.getElementById('pricol3')
                    tyt3.id = "AccInputHeight" + data.id
                }
                setTimeout(IdDataGen3,100);

                function IdDataGen4(){
                    var tyt4 = document.getElementById('pricol4')
                    tyt4.id = "AccInputWeight" + data.id
                }
                setTimeout(IdDataGen4,100);



                // Заполнение <Input> текущими значениями data
                function change1(){
                    var chg = document.getElementById('AccInputName' + data.id)
                    chg.value = data.fio
                }
                setTimeout(change1,200);

                function change2(){
                    var chg1 = document.getElementById('AccInputSex'+ data.id)
                    chg1.value = data.personGender
                }
                setTimeout(change2,200);

                function change3(){
                    var chg2 = document.getElementById('AccInputAge'+ data.id)
                    chg2.value = data.age
                }
                setTimeout(change3,200);

                function change4(){
                    var chg3 = document.getElementById('AccInputHeight'+ data.id)
                    chg3.value = data.h
                }
                setTimeout(change4,200);

                function change5(){
                    var chg4 = document.getElementById('AccInputWeight'+ data.id)
                    chg4.value = data.weight
                }
                setTimeout(change5,200);



                // Кнопка изменения пользователя
                var changebutton = $('<button class="change-button" > Изменить человека </button>').click(function() {

                    //UPDATE запрос на сервер
                    async function ChangePerson() {
                        var inputElement12 = document.getElementById('AccInputName' + data.id);
                        var inputElement22 = document.getElementById('AccInputSex'+ data.id);
                        var inputElement32 = document.getElementById('AccInputAge'+ data.id);
                        var inputElement42 = document.getElementById('AccInputHeight'+ data.id);
                        var inputElement52 = document.getElementById('AccInputWeight'+ data.id);
                        let value12 = inputElement12.value;
                        let value22 = inputElement22.value;
                        let value32 = inputElement32.value;
                        let value42 = inputElement42.value;
                        let value52 = inputElement52.value;
                        const one = 1


                        console.log(value12, value22, value32, value42, value52);

                        // Создание тела body, которое будет отправлено на сервер
                        var upuser = {
                            fio: value12, personGender: value22, age: value32, h: value42, weight: value52
                        };

                        //Проверка введённых данных
                        if (value32 < one) {
                            console.log('Ошибка');
                            alert('Неверный возраст')
                        } else {
                            if (value42 < one) {
                                console.log('Ошибка');
                                alert('Неверный рост')
                            } else {
                                if (value52 < one) {
                                    console.log('Ошибка');
                                    alert('Неверный вес')
                                } else {

                                    var updateout = await fetch('http://localhost:8080/update?id=' + data.id, {
                                        method: 'PATCH',
                                        headers: {
                                            'Content-Type': 'application/json;charset=utf-8'
                                        },
                                        body: JSON.stringify(upuser)
                                    })

                                    var upanswer = await updateout.JSON
                                    console.log(upanswer)

                                    var accordiondelete = document.getElementById("accordion");
                                    accordiondelete.innerHTML = "";

                                    getResponse()
                                }

                            }
                        }
                    }
                    ChangePerson()              // Отправка запроса на сервер
                });






                    // Кнопка удаления пользователя
                    var deleteButton = $('<button class="delete-button"> Удалить человека</button>').click(function() {

                        //DELETE запрос на удаление пользователя
                        async function deletePerson(){

                            var deleteout = await fetch('http://localhost:8080/deletePersonById?id=' +data.id, {
                                method: 'DELETE',
                            })
                            var deanswer = await deleteout.JSON
                            console.log(deanswer)
                        }
                        deletePerson()              // Отправка запроса на сервер
                        accordionItem.remove();     // Удаление в html
                    });

                    accordionHeader.append(deleteButton);                               // Вставляем в  accordionHeader, узел deleteButton
                    accordionItem.append(accordionHeader).append(accordionContent)      // Вставляем в  accordionItem, узел accordionHeader, а в него узел accordionContent
                    accordionContent.append(PLSaccordion);                              // Вставляем в  accordionContent, узел PLSaccordion
                    PLSaccordion.append(changebutton);                                  // Вставляем в PLSaccordion, узел changebutton
                    accordion.append(accordionItem);                                    // Вставляем в  accordion html, узел accordionItem
                                                                                        // item - обьект, header - заголовок id + fio, content - users data

                    accordionHeader.click(function() {        // Скрытие и Раскрытие аккордеонов
                        accordionContent.slideToggle();

                    });

            }
        );


        console.log(data)
        console.log(response.status)

    }
    setTimeout(getResponse,500);
})









function setSelectedInstitute(institute){
    console.log(institute)
    fetch('http://localhost:8080/EntrenamosuyWeb/RegisterToClass?choosenInstitute=' + encodeURIComponent(institute),
        {method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }}
    ).then((response) => {
        if(!response.ok) {
            throw new Error('El dato ingresado es invalido')
        }
        return response.json()
    }).then((data) => {
        console.log(data)
    }).catch((error) => {
        alert('Hubo un error procesando su solicitud, por favor intente nuevamente mas tarde.\nError: ' + error)
    })
}

function setSelectedActivity(activity) {

}

function setSelectedClass(aClass){
    // Este seria el confirm
    // fetch(url, {
    //     method: 'POST',
    //     headers: {
    //         'chosenClass': aClass,
    //     },
    // })
}
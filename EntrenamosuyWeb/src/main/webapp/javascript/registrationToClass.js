import { localBackendUrl } from "./constants"

function setSelectedInstitute(institute){
    console.log(institute)
    fetch(`${localBackendUrl}/RegisterToClass?chosenInstitute=${institute}`,
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
		let htmlToAppend = '';
        document.getElementById(activitySelect).innerHTML = "\n<option selected disabled>Sin seleccionar</option>\n";
        for(let activity of data){
			htmlToAppend += `<option>${activity.name}<option>`
		}
		document.getElementById(activitySelect).innerHTML += htmlToAppend;
    }).catch((error) => {
        alert('Hubo un error procesando su solicitud, por favor intente nuevamente mas tarde.\n' + error)
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
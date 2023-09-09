import { localBackendUrl } from "./constants.js"

function getData(){
	
}

document.getElementById('instituteSelect').addEventListener('change', (event) => {
	fetch(`${localBackendUrl}/RegisterToClass?chosenInstitute=${event.target.value}`,
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
		if(data === null || Object.keys(data).length === 0) {
			document.getElementById('activitySelect').innerHTML = "\n<option selected disabled>El instituto indicado no tiene actividades</option>\n";
		}
		let htmlToAppend = '';
        document.getElementById('activitySelect').innerHTML = "\n<option selected disabled>Sin seleccionar</option>";
        for(const activity in data){
			htmlToAppend += `\n<option>${activity}</option>`
		}
		document.getElementById('activitySelect').innerHTML += htmlToAppend;
    }).catch((error) => {
        alert('Hubo un error procesando su solicitud, por favor intente nuevamente mas tarde.\n' + error)
    })
})


document.getElementById('activitySelect').addEventListener('change', (event) => {
	fetch(`${localBackendUrl}/RegisterToClass?chosenActivity=${event.target.value}`,
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
		if(data === null || Object.keys(data).length === 0) {
			document.getElementById('classSelect').innerHTML = "\n<option selected disabled>La actividad indicada no tiene clases</option>\n";
		}
		let htmlToAppend = '';
        document.getElementById('classSelect').innerHTML = "\n<option selected disabled>Sin seleccionar</option>";
        for(const aClass in data){
			htmlToAppend += `\n<option>${aClass}</option>`
		}
		document.getElementById('classSelect').innerHTML += htmlToAppend;
    }).catch((error) => {
        alert('Hubo un error procesando su solicitud, por favor intente nuevamente mas tarde.\n' + error)
    })
})

document.getElementById('classSelect').addEventListener('change', (event) => {
	fetch(`${localBackendUrl}/RegisterToClass?chosenClass=${event.target.value}`,
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
		document.getElementById('className').innerHTML = data.name
		document.getElementById('classUrl').innerHTML = data.url
		document.getElementById('classPrice').innerHTML = data.price
		document.getElementById('classDate').innerHTML = data.date
		document.getElementById('confirmButon').disabled = false
    }).catch((error) => {
        alert('Hubo un error procesando su solicitud, por favor intente nuevamente mas tarde.\n' + error)
    })
})

document.getElementById('confirmButon').addEventListener('click', (event) => {
	event.stopPropagation();
	fetch(url, {
         method: 'POST',
    })
})

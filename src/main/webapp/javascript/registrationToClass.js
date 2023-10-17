import { localBackendUrl } from "./constants.js"


function displayAlert(message, alertClass){
	const alert = document.getElementById('feedbackAlert')
	alert.innerHTML = `${message}\n<button id="alertDismiss" type="button" class="btn-close" aria-label="Close"></button>`
	alert.classList = 'alert alert-dismissible fade show ' + alertClass
	
	document.getElementById('alertDismiss').addEventListener('click', e => {
		alert.classList = 'alert alert-dismissible fade hide '
	})
}


function getInstituteValue(){
	return document.getElementById("instituteSelect").value
}

function getActivityValue(){
	return document.getElementById("activitySelect").value
}

function getClassValue(){
	return document.getElementById("classSelect").value
}

function cleanTable(){
	document.getElementById('className').innerHTML = ''
	document.getElementById('classUrl').innerHTML = ''
	document.getElementById('classPrice').innerHTML = ''
	document.getElementById('classDate').innerHTML = ''
}

function restartUseCase(){
	document.getElementById("instituteSelect").value = 'Sin seleccionar'
	document.getElementById("activitySelect").innerHTML = '<option selected disabled>Sin seleccionar</option>'
	document.getElementById("classSelect").innerHTML = '<option selected disabled>Sin seleccionar</option>'
	document.getElementById('confirmButon').innerHTML = 'Confirmar registro'
	cleanTable()
}

// Institute selected event
document.getElementById('instituteSelect').addEventListener('change', (event) => {
	document.getElementById("activitySelect").value = 'Sin seleccionar'
	document.getElementById("classSelect").value = 'Sin seleccionar'
	cleanTable()
	
	fetch(`${localBackendUrl}/RegisterToClass?chosenInstitute=${event.target.value}`,
        {method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                action: 'INSTITUTE'
            }}
    ).then((response) => {
        if(!response.ok) {
			restartUseCase()
            throw new Error(response.headers.get('error'))
        }
        return response.json()
    }).then((data) => {
		if(data === null || Object.keys(data).length === 0) {
			displayAlert('El instituto indicado no tiene actividades asociadas', 'alert-info')
		}
		let htmlToAppend = '';
        document.getElementById('activitySelect').innerHTML = "\n<option selected disabled value=\"Sin seleccionar\">Sin seleccionar</option>"
        for(const activity in data){
			htmlToAppend += `\n<option>${activity}</option>`
		}
		document.getElementById('activitySelect').innerHTML += htmlToAppend;
    }).catch((error) => {
		displayAlert(error, 'alert-danger')
    })
})

// Activity selected event
document.getElementById('activitySelect').addEventListener('change', (event) => {
	document.getElementById("classSelect").value = 'Sin seleccionar'
	cleanTable()
	
	fetch(`${localBackendUrl}/RegisterToClass?chosenActivity=${event.target.value}&chosenInstitute=${getInstituteValue()}`,
        {method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                action: 'ACTIVITY'
            }}
    ).then((response) => {
        if(!response.ok) {
			restartUseCase()
            throw new Error(response.headers.get('error'))
        }
        return response.json()
    }).then((data) => {
		if(data === null || Object.keys(data).length === 0) {
			displayAlert("La actividad indicada no tiene clases", 'alert-info')
		}
		let htmlToAppend = '';
        document.getElementById('classSelect').innerHTML = "\n<option selected disabled value=\"Sin seleccionar\">Sin seleccionar</option>";
        for(const aClass in data){
			htmlToAppend += `\n<option>${aClass}</option>`
		}
		document.getElementById('classSelect').innerHTML += htmlToAppend;
    }).catch((error) => {
        displayAlert(error, 'alert-danger')
    })
})

// Class selected event
document.getElementById('classSelect').addEventListener('change', (event) => {
	cleanTable()
	
	fetch(`${localBackendUrl}/RegisterToClass?chosenClass=${event.target.value}&chosenActivity=${getActivityValue()}&chosenInstitute=${getInstituteValue()}`,
        {method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                action: 'CLASS'
            }}
    ).then((response) => {
        if(!response.ok) {
			restartUseCase()
            throw new Error(response.headers.get('error'))
        }
        return response.json()
    }).then((data) => {
		document.getElementById('className').innerHTML = data.name
		document.getElementById('classUrl').innerHTML = data.url
		document.getElementById('classPrice').innerHTML = data.price
		document.getElementById('classDate').innerHTML = data.date
		document.getElementById('confirmButon').disabled = false
    }).catch((error) => {
        displayAlert(error, 'alert-danger')
    })
})

// Confirmation button event
document.getElementById('confirmButon').addEventListener('click', (event) => {
	event.stopPropagation()
	event.preventDefault()
	event.target.disabled = true
	document.getElementById('confirmButon').innerHTML = `<span class="spinner-border spinner-border-sm" aria-hidden="true"></span>
  								<span role="status">Cargando...</span>`
	
	fetch(`${localBackendUrl}/RegisterToClass`, {
         method: 'POST',
         headers: {
			 institute: getInstituteValue(),
			 activity: getActivityValue(),
			 chosenClass: getClassValue()
		 }
    }).then((response) => {
        if(!response.ok) {
			restartUseCase()
            throw new Error(response.headers.get('error'))
        }
        displayAlert("Se completó la insctipción satisfactoriamente!", "alert-success")
		restartUseCase()
	}).catch((error) => {
        displayAlert(error, 'alert-danger')
    })
})



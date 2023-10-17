/**
 * 
 */

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
	document.getElementById('className').value = ''
	document.getElementById('classUrl').value = ''
	document.getElementById('classPrice').value = ''
	document.getElementById('classDate').value = ''
	document.getElementById('bodyMembersTable').innerHTML = ''
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
	
	fetch(`${localBackendUrl}/ClassDictationConsultation?chosenInstitute=${event.target.value}`,
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
	
	fetch(`${localBackendUrl}/ClassDictationConsultation?chosenActivity=${event.target.value}&chosenInstitute=${getInstituteValue()}`,
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
	
	fetch(`${localBackendUrl}/ClassDictationConsultation?chosenClass=${event.target.value}&chosenActivity=${getActivityValue()}&chosenInstitute=${getInstituteValue()}`,
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
		document.getElementById('className').value = data.name
		document.getElementById('classUrl').value = data.url
		document.getElementById('classPrice').value = data.price
		document.getElementById('classDate').value = data.date
		
		
		if(data.members){
			const members = JSON.parse(data.members)
			let tbody = document.querySelector('#members tbody')
			members.forEach(function(member) {
			    var row = document.createElement('tr')
			    var cellName = document.createElement('td')
			    var cellLast = document.createElement('td')
			    var cellEmail = document.createElement('td')
			
			    cellName.textContent = member.name
			    cellLast.textContent = member.lastName
			    cellEmail.textContent = member.email
			
			    row.appendChild(cellName)
			    row.appendChild(cellLast)
			    row.appendChild(cellEmail)
			    tbody.appendChild(row)
			})
		}
    }).catch((error) => {
        displayAlert(error, 'alert-danger')
    })
})

